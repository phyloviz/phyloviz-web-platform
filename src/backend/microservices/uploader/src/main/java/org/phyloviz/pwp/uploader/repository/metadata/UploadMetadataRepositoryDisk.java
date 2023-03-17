package org.phyloviz.pwp.uploader.repository.metadata;

import com.google.gson.Gson;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;
import org.phyloviz.pwp.uploader.repository.metadata.documents.ProfileMetadata;
import org.phyloviz.pwp.uploader.utils.UploaderLogger;
import org.springframework.stereotype.Repository;

/**
 * Implementation of the {@link UploadMetadataRepository} interface that stores the metadata in the disk.
 */
@Repository
public class UploadMetadataRepositoryDisk implements UploadMetadataRepository {

    private final String metadataPath = new File("").getAbsolutePath() + "\\diskMetadataFiles";

    @Override
    public ProfileMetadata store(ProfileMetadata profileMetadata) {
        String id = UUID.randomUUID().toString();

        File file = new File(metadataPath + "\\metadata\\" + id + ".json");

        try (FileWriter fileWriter = new FileWriter(file)) {
            new Gson().toJson(profileMetadata, fileWriter);
        } catch (IOException e) {
            UploaderLogger.warn("Error while storing metadata in disk" + e.getMessage());
            return null;
        }

        return profileMetadata;
    }

}
