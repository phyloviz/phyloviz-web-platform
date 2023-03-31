package org.phyloviz.pwp.shared.repository.metadata.typingData.disk;

import com.nimbusds.jose.shaded.gson.Gson;
import org.phyloviz.pwp.shared.repository.metadata.typingData.TypingDataMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.typingData.documents.TypingDataMetadata;
import org.phyloviz.pwp.shared.utils.Logger;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Repository
public class TypingDataMetadataRepositoryDisk implements TypingDataMetadataRepository {

    private final String metadataPath = new File("").getAbsolutePath() + "\\diskMetadataFiles";

    @Override
    public TypingDataMetadata save(TypingDataMetadata typingDataMetadata) {
        String id = UUID.randomUUID().toString();

        File file = new File(metadataPath + "\\typing-data\\" + id + ".json");

        try (FileWriter fileWriter = new FileWriter(file)) {
            new Gson().toJson(typingDataMetadata, fileWriter);
        } catch (IOException e) {
            Logger.warn("Error while storing metadata in disk" + e.getMessage());
            return null;
        }

        return typingDataMetadata;
    }

    @Override
    public void delete(TypingDataMetadata typingDataMetadata) {
        File file = new File(metadataPath + "\\typing-data\\" + typingDataMetadata.getId() + ".json");

        if (file.exists()) {
            file.delete();
        }
    }

    @Override
    public TypingDataMetadata findByTypingDataId(String typingDataId) {
        return null;
    }

    @Override
    public List<TypingDataMetadata> findAllByTypingDataId(String typingDataId) {
        return null;
    }
}
