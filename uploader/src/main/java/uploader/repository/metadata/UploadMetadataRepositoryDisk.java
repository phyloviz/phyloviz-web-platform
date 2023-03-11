package uploader.repository.metadata;

import com.google.gson.Gson;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import uploader.repository.metadata.objects.Metadata;
import uploader.repository.project.Project;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

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
            // TODO LOG
            e.printStackTrace();
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
            // TODO LOG
            e.printStackTrace();
        }
    }
}
