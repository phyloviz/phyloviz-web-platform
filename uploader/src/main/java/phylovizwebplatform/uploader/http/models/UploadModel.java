package phylovizwebplatform.uploader.http.models;

import org.springframework.web.multipart.MultipartFile;

public class UploadModel {
    String project;
    String type;
    MultipartFile file;

    public String getProject() {
        return project;
    }

    public String getType() {
        return type;
    }

    public MultipartFile getFile() {
        return file;
    }
}
