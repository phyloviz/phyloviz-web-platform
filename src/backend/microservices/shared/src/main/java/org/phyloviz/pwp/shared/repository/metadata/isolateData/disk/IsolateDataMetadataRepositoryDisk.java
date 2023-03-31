package org.phyloviz.pwp.shared.repository.metadata.isolateData.disk;

import com.nimbusds.jose.shaded.gson.Gson;
import org.phyloviz.pwp.shared.repository.metadata.isolateData.IsolateDataMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.isolateData.documents.IsolateDataMetadata;
import org.phyloviz.pwp.shared.utils.Logger;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Repository
public class IsolateDataMetadataRepositoryDisk implements IsolateDataMetadataRepository {

    private final String metadataPath = new File("").getAbsolutePath() + "\\diskMetadataFiles";

    @Override
    public IsolateDataMetadata save(IsolateDataMetadata isolateDataMetadata) {
        String id = UUID.randomUUID().toString();

        File file = new File(metadataPath + "\\isolate-data\\" + id + ".json");

        try (FileWriter fileWriter = new FileWriter(file)) {
            new Gson().toJson(isolateDataMetadata, fileWriter);
        } catch (IOException e) {
            Logger.warn("Error while storing metadata in disk" + e.getMessage());
            return null;
        }

        return isolateDataMetadata;
    }

    @Override
    public void delete(IsolateDataMetadata isolateDataMetadata) {
        File file = new File(metadataPath + "\\isolate-data\\" + isolateDataMetadata.getId() + ".json");

        if (file.exists()) {
            file.delete();
        }
    }

    @Override
    public IsolateDataMetadata findByIsolateDataId(String isolateDataId) {
        return null;
    }

    @Override
    public List<IsolateDataMetadata> findAllByIsolateDataId(String isolateDataId) {
        return null;
    }
}
