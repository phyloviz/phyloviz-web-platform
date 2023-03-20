package org.phyloviz.pwp.uploader.service;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.data.ObjectStorageRepository;
import org.phyloviz.pwp.shared.repository.metadata.typing_dataset.TypingDatasetMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.typing_dataset.documents.TypingDatasetMetadata;
import org.phyloviz.pwp.shared.repository.metadata.project.ProjectRepository;
import org.phyloviz.pwp.shared.repository.metadata.project.documents.Project;
import org.phyloviz.pwp.shared.repository.metadata.project.documents.Resource;
import org.phyloviz.pwp.shared.service.dtos.UserDTO;
import org.phyloviz.pwp.shared.service.exceptions.UnauthorizedException;
import org.phyloviz.pwp.uploader.repository.metadata.documents.TypingDatasetS3AdapterSpecificData;
import org.phyloviz.pwp.uploader.service.dtos.uploadTypingDataset.UploadTypingDatasetOutputDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.UUID;

/**
 * Implementation of the {@link UploadService} interface.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class UploadServiceImpl implements UploadService {

    private final ObjectStorageRepository objectStorageRepository;

    private final ProjectRepository projectRepository;
    private final TypingDatasetMetadataRepository typingDatasetMetadataRepository;

    private static final String TYPING_DATASET_COLLECTION = "typing-datasets";

    @Override
    public UploadTypingDatasetOutputDTO uploadTypingDataset(String projectId, MultipartFile multipartFile, UserDTO userDTO) {
        Project project = projectRepository.findById(projectId);

        if (!project.getOwnerId().equals(userDTO.getId()))
            throw new UnauthorizedException("User does not have permission to upload to this project");

        String resourceId = UUID.randomUUID().toString();
        String location = "/" + projectId + "/" + resourceId;

        final TypingDatasetMetadata typingDatasetMetadata = new TypingDatasetMetadata(
                projectId,
                resourceId,
                objectStorageRepository.getLocation() + location,
                objectStorageRepository.getAdapterId(),
                new TypingDatasetS3AdapterSpecificData(multipartFile.getOriginalFilename()),
                Collections.emptyList()
        );

        typingDatasetMetadataRepository.save(typingDatasetMetadata);

        boolean stored = objectStorageRepository.store(location, multipartFile);

        if (!stored)
            throw new RuntimeException("Could not store file");

        project.getResources().add(new Resource(resourceId, TYPING_DATASET_COLLECTION));

        projectRepository.save(project);

        return new UploadTypingDatasetOutputDTO(resourceId);
    }
}
