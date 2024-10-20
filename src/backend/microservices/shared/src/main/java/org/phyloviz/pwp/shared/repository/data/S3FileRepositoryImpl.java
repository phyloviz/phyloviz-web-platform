package org.phyloviz.pwp.shared.repository.data;

import org.phyloviz.pwp.shared.service.exceptions.FileCorruptedException;
import org.phyloviz.pwp.shared.service.exceptions.MultipartFileReadException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.BytesWrapper;
import software.amazon.awssdk.core.async.AsyncRequestBody;
import software.amazon.awssdk.core.async.AsyncResponseTransformer;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.NoSuchKeyException;
import software.amazon.awssdk.transfer.s3.S3TransferManager;
import software.amazon.awssdk.transfer.s3.model.Upload;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Repository
public class S3FileRepositoryImpl implements S3FileRepository {

    public static final Region REGION = Region.of("eu-west-3");
    private final String bucketName;
    private final String objectStorageEndpoint;
    private final S3AsyncClient s3Client;
    private final S3TransferManager transferManager;
    private final ExecutorService executorService = Executors.newCachedThreadPool();

    public S3FileRepositoryImpl(
            @Value("${s3.endpoint}")
            String objectStorageEndpoint,
            @Value("${s3.access-key-id}")
            String accessKeyId,
            @Value("${s3.secret-access-key}")
            String secretAccessKey,
            @Value("${s3.bucket}")
            String bucketName
    ) {
        AwsBasicCredentials awsCredentials = AwsBasicCredentials.create(accessKeyId, secretAccessKey);

        S3AsyncClient newS3Client = S3AsyncClient.builder()
                .credentialsProvider(StaticCredentialsProvider.create(awsCredentials))
                .endpointOverride(URI.create(objectStorageEndpoint))
                .region(REGION)
                .build();

        newS3Client.createBucket(r -> r.bucket(bucketName));

        this.s3Client = newS3Client;
        this.bucketName = bucketName;
        this.objectStorageEndpoint = objectStorageEndpoint;
        this.transferManager = S3TransferManager.builder().s3Client(newS3Client).build();
    }

    @Override
    public boolean upload(String url, MultipartFile multipartFile) {
        AsyncRequestBody requestBody;
        try {
            requestBody = AsyncRequestBody.fromInputStream(
                    multipartFile.getInputStream(),
                    multipartFile.getSize(),
                    executorService
            );
        } catch (IOException e) {
            throw new MultipartFileReadException("Error while obtaining multipart file's input stream", e);
        }

        Upload upload = transferManager.upload(p -> {
            p.requestBody(requestBody);
            p.putObjectRequest(o -> o.bucket(bucketName).key(url));
        });

        upload.completionFuture().join();

        return true;
    }

    @Override
    public String download(String url) {
        if (!url.startsWith(getLocation()))
            throw new IllegalArgumentException("URL does not start with the object storage endpoint");

        String key = url.substring(getLocation().length());

        /*s3Client.listObjectsV2(ListObjectsV2Request.builder().bucket(bucketName).build())
                .thenAccept(response -> {
                    // TODO: Consume `response` the way you need
                    response.contents().forEach(object -> {
                        if(object.key().equals(key))
                            System.out.println("Found");
                    });
                })
                .exceptionally(error -> {
                    // TODO: Handle `error` the way you need
                    error.printStackTrace();
                    return null;
                }).join();*/

        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();
        /*GetObjectResponse getResponse = s3Client.getObject(getObjectRequest, Path.of(
                "C:\\Users\\nyckb\\Downloads\\")).join();*/

        CompletableFuture<String> completableFuture = s3Client.getObject(getObjectRequest, AsyncResponseTransformer.toBytes())
                .thenApply(BytesWrapper::asUtf8String);

        try {
            String contents = completableFuture.join();
            if (contents == null)
                throw new FileCorruptedException("File corrupted? File contents are null. Perhaps error downloading from S3");

            return contents;
        } catch (CompletionException e) {
            if (e.getCause() instanceof NoSuchKeyException)
                throw new FileCorruptedException("File corrupted? File not found in S3");
            e.printStackTrace();
            throw new CompletionException("Error while downloading file from S3", e);
        }
    }

    @Override
    public boolean delete(String url) {
        if (!url.startsWith(getLocation()))
            throw new IllegalArgumentException("URL does not start with the object storage endpoint");

        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(url.substring(getLocation().length()))
                .build();

        s3Client.deleteObject(deleteObjectRequest); // TODO throw exception if not successful

        return true;
    }

    @Override
    public String getLocation() {
        return objectStorageEndpoint + "/" + bucketName.substring(0, bucketName.length() - 1);
    }
}
