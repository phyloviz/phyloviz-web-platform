package org.phyloviz.pwp.uploader.repository.data;

import org.openstack4j.api.OSClient;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.common.Identifier;
import org.openstack4j.model.common.Payload;
import org.openstack4j.model.common.Payloads;
import org.openstack4j.model.identity.v3.Token;
import org.openstack4j.model.storage.object.options.ObjectPutOptions;
import org.openstack4j.openstack.OSFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


/**
 * Implementation of the {@link UploadRepository} interface for the Cloud environment.
 */
@Repository
@Primary
public class UploadRepositoryCloud implements UploadRepository {

    private final String containerName;
    private final String objectStorageEndpoint;

    private final Token token;

    public UploadRepositoryCloud(
            @Value("${openstack.identity.url}")
            String identityEndpoint,
            @Value("${openstack.identity.username}")
            String username,
            @Value("${openstack.identity.password}")
            String password,
            @Value("${openstack.identity.project.name}")
            String projectName,
            @Value("${openstack.identity.domain.name}")
            String projectDomainName,
            @Value("${openstack.object-storage.container}")
            String containerName,
            @Value("${openstack.object-storage.url}")
            String objectStorageEndpoint
    ) {

        this.containerName = containerName;
        this.objectStorageEndpoint = objectStorageEndpoint;

        Identifier domainIdentifier = Identifier.byName(projectDomainName);
        OSClient.OSClientV3 os = OSFactory.builderV3()
                .endpoint(identityEndpoint)
                .credentials(username, password, domainIdentifier)
                .scopeToProject(Identifier.byName(projectName), domainIdentifier)
                .authenticate();

        this.token = os.getToken();

        ActionResponse res = os.objectStorage().containers().create(this.containerName);
        if (!res.isSuccess())
            throw new RuntimeException("Could not create container: " + res.getFault());
    }

    @Override
    public boolean storeProfile(String location, MultipartFile multipartFile) {
        int lastSlash = location.lastIndexOf("/");
        String path = location.substring(0, lastSlash);

        String name = location.substring(lastSlash + 1);

        OSClient.OSClientV3 os = OSFactory.clientFromToken(token);

        Payload payload;
        try {
            payload = Payloads.create(multipartFile.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        os.objectStorage().objects().put(containerName, name, payload, ObjectPutOptions.create().path(path));

        return true;
    }

    @Override
    public String getLocation() {
        return objectStorageEndpoint + "/" + containerName;
    }

    @Override
    public String getAdapterId() {
        return "s3";
    }

}
