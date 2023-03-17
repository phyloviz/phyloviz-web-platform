package org.phyloviz.pwp.uploader.service;

import java.util.UUID;
import lombok.AllArgsConstructor;
import org.phyloviz.pwp.shared.repository.metadata.documents.File;
import org.phyloviz.pwp.shared.repository.metadata.documents.Project;
import org.phyloviz.pwp.shared.repository.metadata.mongo.ProjectMongoRepository;
import org.phyloviz.pwp.shared.service.dtos.UserDTO;
import org.phyloviz.pwp.shared.service.exceptions.ProjectNotFoundException;
import org.phyloviz.pwp.shared.service.exceptions.UnauthorizedException;
import org.phyloviz.pwp.uploader.repository.data.UploadRepository;
import org.phyloviz.pwp.uploader.repository.metadata.UploadMetadataRepository;
import org.phyloviz.pwp.uploader.repository.metadata.documents.ProfileMetadata;
import org.phyloviz.pwp.uploader.service.dtos.uploadeProfile.UploadProfileOutputDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * Implementation of the {@link UploadService} interface.
 */
@Service
@AllArgsConstructor
@Transactional
public class UploadServiceImpl implements UploadService {

    private final UploadRepository uploadRepository;
    private final UploadMetadataRepository uploadMetadataRepository;
    private final ProjectMongoRepository projectMongoRepository;

    @Override
    public UploadProfileOutputDTO uploadProfile(String projectId, MultipartFile multipartFile, UserDTO userDTO) {
        Project project = projectMongoRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException(projectId));

        if (!project.getOwner().equals(userDTO.getId()))
            throw new UnauthorizedException("User does not have permission to upload to this project");

        String id = UUID.randomUUID().toString();
        String location = "/" + projectId + "/" + id;

        final ProfileMetadata profileMetadata = new ProfileMetadata(
                projectId,
                id,
                uploadRepository.getLocation() + location,
                uploadRepository.getAdapterId(),
                multipartFile.getOriginalFilename()
        );

        uploadMetadataRepository.store(profileMetadata);


        boolean stored = uploadRepository.storeProfile(location, multipartFile);

        if (!stored)
            throw new RuntimeException("Could not store file");

        project.getFiles().add(new File(id, profileMetadata.getOriginalFileName()));

        projectMongoRepository.save(project);

        return new UploadProfileOutputDTO(id);
    }

}
