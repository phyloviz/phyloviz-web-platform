package org.phyloviz.pwp.uploader.repository.data;

import java.io.IOException;
import org.openstack4j.api.OSClient;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.common.Identifier;
import org.openstack4j.model.common.Payload;
import org.openstack4j.model.common.Payloads;
import org.openstack4j.model.identity.v3.Token;
import org.openstack4j.model.storage.object.options.ObjectPutOptions;
import org.openstack4j.openstack.OSFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;


/**
 * Implementation of the {@link UploadRepository} interface for the Cloud environment.
 */
@Repository
@Primary
public class UploadRepositoryCloud implements UploadRepository {
    private final String containerName = "phyloviz";
    private final Token token;

    public UploadRepositoryCloud() {
        Identifier domainIdentifier = Identifier.byName("Default");
        OSClient.OSClientV3 os = OSFactory.builderV3()
                .endpoint("http://192.168.1.93/identity/v3")
                .credentials("admin", "secret", domainIdentifier)
                .scopeToProject(Identifier.byName("admin"), domainIdentifier)
                .authenticate();

        this.token = os.getToken();

        ActionResponse res = os.objectStorage().containers().create(containerName);
        if (!res.isSuccess())
            throw new RuntimeException("Could not create container: " + res.getFault());
    }

    @Override
    public boolean storeProfile(String location, MultipartFile multipartFile) {
        // Extract path from location
        String path = location.substring(0, location.lastIndexOf("/"));
        // Extract name from location
        String name = location.substring(location.lastIndexOf("/") + 1);

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
        return "http://192.168.1.93:8080/v1/AUTH_1fd7ab23dada42b38e0a5f7b700ac439/" + containerName;
    }

    @Override
    public String getAdapterId() {
        return "s3";
    }

}
