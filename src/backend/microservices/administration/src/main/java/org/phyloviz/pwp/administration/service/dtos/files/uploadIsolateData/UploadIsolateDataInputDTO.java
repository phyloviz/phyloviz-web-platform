package org.phyloviz.pwp.administration.service.dtos.files.uploadIsolateData;

import lombok.Data;
import org.phyloviz.pwp.shared.service.dtos.UserDTO;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UploadIsolateDataInputDTO {
    private final String projectId;
    private final MultipartFile multipartFile;
    private final UserDTO user;
}
