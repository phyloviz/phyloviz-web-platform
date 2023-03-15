package org.isel.phylovizwebplatform.gateway.service;

import org.isel.phylovizwebplatform.gateway.http.models.FileType;
import org.isel.phylovizwebplatform.gateway.repository.data.UploadRepository;
import org.isel.phylovizwebplatform.gateway.repository.metadata.UploadMetadataRepository;
import org.isel.phylovizwebplatform.gateway.repository.metadata.objects.ProfileMetadata;
import org.isel.phylovizwebplatform.gateway.repository.project.Project;
import org.isel.phylovizwebplatform.gateway.service.dtos.CreateProjectInputDTO;
import org.isel.phylovizwebplatform.gateway.service.dtos.CreateProjectOutputDTO;
import org.isel.phylovizwebplatform.gateway.service.dtos.UploadProfileOutputDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.UUID;

/**
 * Implementation of the {@link UploadService} interface.
 */
@Service
public class UploadServiceImpl implements UploadService {

    private final UploadRepository uploadRepository;
    private final UploadMetadataRepository uploadMetadataRepository;

    public UploadServiceImpl(UploadRepository uploadRepository, UploadMetadataRepository uploadMetadataRepository) {
        this.uploadRepository = uploadRepository;
        this.uploadMetadataRepository = uploadMetadataRepository;
    }

    @Override
    public UploadProfileOutputDTO storeProfile(String projectId, MultipartFile multipartFile) {
        String id = UUID.randomUUID().toString();
        String location = "\\" + projectId + "\\" + id + FileType.PROFILE.getFileExtension();

        final ProfileMetadata profileMetadata = new ProfileMetadata(
                projectId,
                uploadRepository.getLocation() + location,
                multipartFile.getOriginalFilename()
        );

        ProfileMetadata storedProfileMetadata = uploadMetadataRepository.store(profileMetadata);

        boolean stored = uploadRepository.storeProfile(location, multipartFile);

        if (!stored)
            throw new RuntimeException("Could not store file");

        return new UploadProfileOutputDTO(id);
    }

    @Override
    public CreateProjectOutputDTO createProject(CreateProjectInputDTO createProjectInputDTO) {
        Project project = new Project(
                createProjectInputDTO.getName(),
                createProjectInputDTO.getDescription(),
                createProjectInputDTO.getOwner(),
                Collections.emptyList()
        );

        Project storedProject = uploadMetadataRepository.storeProject(project);
        return new CreateProjectOutputDTO(storedProject.getId());
    }
}
