package org.phyloviz.pwp.shared.repository.metadata.typing_dataset;

import com.nimbusds.jose.shaded.gson.Gson;
import org.phyloviz.pwp.shared.repository.metadata.typing_dataset.documents.TypingDatasetMetadata;
import org.phyloviz.pwp.shared.utils.Logger;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

@Repository
public class TypingDatasetMetadataRepositoryDisk implements TypingDatasetMetadataRepository {

    private final String metadataPath = new File("").getAbsolutePath() + "\\diskMetadataFiles";

    @Override
    public TypingDatasetMetadata save(TypingDatasetMetadata typingDatasetMetadata) {
        String id = UUID.randomUUID().toString();

        File file = new File(metadataPath + "\\typing-datasets\\" + id + ".json");

        try (FileWriter fileWriter = new FileWriter(file)) {
            new Gson().toJson(typingDatasetMetadata, fileWriter);
        } catch (IOException e) {
            Logger.warn("Error while storing metadata in disk" + e.getMessage());
            return null;
        }

        return typingDatasetMetadata;
    }

    @Override
    public void delete(TypingDatasetMetadata typingDatasetMetadata) {
        File file = new File(metadataPath + "\\typing-datasets\\" + typingDatasetMetadata.getId() + ".json");

        if (file.exists()) {
            file.delete();
        }
    }

    @Override
    public TypingDatasetMetadata findByResourceId(String resourceId) {
        return null;
    }
}
