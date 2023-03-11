package uploader.repository.metadata;

import com.google.gson.Gson;
import org.springframework.stereotype.Repository;
import uploader.repository.metadata.objects.Metadata;
import uploader.repository.project.Project;
import uploader.utils.UploaderLogger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

/**
 * Implementation of the {@link UploadMetadataRepository} interface that stores the metadata in the disk.
 */
@Repository
public class UploadMetadataRepositoryDisk implements UploadMetadataRepository {

    private final String metadataPath = new File("").getAbsolutePath() + "\\diskMetadataFiles";

    @Override
    public Metadata store(Metadata metadata) {
        String id = UUID.randomUUID().toString();

        File file = new File(metadataPath + "\\metadata\\" + id + ".json");

        try (FileWriter fileWriter = new FileWriter(file)) {
            new Gson().toJson(metadata, fileWriter);
        } catch (IOException e) {
            UploaderLogger.warn("Error while storing metadata in disk" + e.getMessage());
            return null;
        }

        return metadata;
    }

    @Override
    public void storeProject(Project project) {
        String id = UUID.randomUUID().toString();

        File file = new File(metadataPath + "\\project\\" + id + ".json");

        try (FileWriter fileWriter = new FileWriter(file)) {
            new Gson().toJson(project, fileWriter);
        } catch (IOException e) {
            UploaderLogger.warn("Error while storing project in disk" + e.getMessage());
        }
    }
}
