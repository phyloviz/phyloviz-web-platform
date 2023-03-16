package org.phyloviz.pwp.uploader.service;

import org.phyloviz.pwp.uploader.http.models.FileType;
import org.phyloviz.pwp.uploader.repository.data.UploadRepository;
import org.phyloviz.pwp.uploader.repository.metadata.UploadMetadataRepository;
import org.phyloviz.pwp.uploader.repository.metadata.objects.ProfileMetadata;
import org.phyloviz.pwp.uploader.repository.project.Project;
import org.phyloviz.pwp.uploader.service.dtos.createProject.CreateProjectInputDTO;
import org.phyloviz.pwp.uploader.service.dtos.createProject.CreateProjectOutputDTO;
import org.phyloviz.pwp.uploader.service.dtos.uploadeProfile.UploadProfileOutputDTO;
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
    public UploadProfileOutputDTO uploadProfile(String projectId, MultipartFile multipartFile) {
        String id = UUID.randomUUID().toString();
        String location = "\\" + projectId + "\\" + id + FileType.PROFILE.getFileExtension();

        final ProfileMetadata profileMetadata = new ProfileMetadata(
                projectId,
                id,
                uploadRepository.getLocation() + location,
                uploadRepository.getAdapterId(),
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
