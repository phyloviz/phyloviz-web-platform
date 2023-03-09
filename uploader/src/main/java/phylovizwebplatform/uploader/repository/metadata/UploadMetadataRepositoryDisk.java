package phylovizwebplatform.uploader.repository.metadata;

import org.springframework.stereotype.Repository;
import phylovizwebplatform.uploader.repository.metadata.objects.Metadata;

@Repository
public class UploadMetadataRepositoryDisk implements UploadMetadataRepository{

    @Override
    public Metadata store(Metadata metadata) {
        return null;
        //File file = new File(metadataPath + metadata.getProject() + "\\" + metadata.getId() + ".json");

        /*try (FileWriter fileWriter = new FileWriter(file)) {
            new Gson().toJson(metadata, fileWriter);
        } catch (IOException e) {
            // TODO LOG
            e.printStackTrace();
        }*/
    }
}
