package org.phyloviz.pwp.administration.service.projects.files.typingData;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.administration.repository.data.FileStorageRepository;
import org.phyloviz.pwp.administration.repository.metadata.dataset.DatasetRepository;
import org.phyloviz.pwp.shared.repository.metadata.typingData.documents.TypingDataS3AdapterSpecificData;
import org.phyloviz.pwp.shared.repository.metadata.project.ProjectRepository;
import org.phyloviz.pwp.shared.repository.metadata.project.documents.Project;
import org.phyloviz.pwp.administration.service.dtos.files.TypingDataDTO;
import org.phyloviz.pwp.administration.service.dtos.files.deleteTypingData.DeleteTypingDataInputDTO;
import org.phyloviz.pwp.administration.service.dtos.files.deleteTypingData.DeleteTypingDataOutputDTO;
import org.phyloviz.pwp.administration.service.dtos.files.uploadTypingData.UploadTypingDataInputDTO;
import org.phyloviz.pwp.administration.service.dtos.files.uploadTypingData.UploadTypingDataOutputDTO;
import org.phyloviz.pwp.administration.service.exceptions.DeniedFileDeletionException;
import org.phyloviz.pwp.shared.repository.metadata.typingData.TypingDataMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.typingData.documents.TypingDataMetadata;
import org.phyloviz.pwp.shared.service.exceptions.UnauthorizedException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TypingDataServiceImpl implements TypingDataService {

    private final ProjectRepository projectRepository;
    private final DatasetRepository datasetRepository;
    private final FileStorageRepository fileStorageRepository;
    private final TypingDataMetadataRepository typingDataMetadataRepository;

    @Override
    public UploadTypingDataOutputDTO uploadTypingData(UploadTypingDataInputDTO uploadTypingDataInputDTO) {
        String projectId = uploadTypingDataInputDTO.getProjectId();
        MultipartFile multipartFile = uploadTypingDataInputDTO.getMultipartFile();
        Project project = projectRepository.findById(projectId);

        if (!project.getOwnerId().equals(uploadTypingDataInputDTO.getUser().getId()))
            throw new UnauthorizedException("User does not have permission to upload to this project");

        String typingDataId = UUID.randomUUID().toString();
        String location = "/" + projectId + "/" + "typingData/" + typingDataId;

        final TypingDataMetadata typingDataMetadata = new TypingDataMetadata(
                projectId,
                typingDataId,
                multipartFile.getOriginalFilename(),
                fileStorageRepository.getLocation() + location,
                fileStorageRepository.getAdapterId(),
                new TypingDataS3AdapterSpecificData(multipartFile.getOriginalFilename())
        );

        typingDataMetadataRepository.save(typingDataMetadata);

        boolean stored = fileStorageRepository.store(location, multipartFile);

        if (!stored)
            throw new RuntimeException("Could not store file");

        project.getFileIds().getTypingDataIds().add(typingDataId);

        projectRepository.save(project);

        return new UploadTypingDataOutputDTO(projectId, typingDataId);
    }

    @Override
    public TypingDataDTO getTypingData(String typingDataId) {
        TypingDataMetadata typingDataMetadata = typingDataMetadataRepository.findByTypingDataId(typingDataId);

        return new TypingDataDTO(
                typingDataMetadata.getTypingDataId(),
                typingDataMetadata.getName()
        );
    }

    @Override
    public DeleteTypingDataOutputDTO deleteTypingData(DeleteTypingDataInputDTO deleteTypingDataInputDTO) {
        String projectId = deleteTypingDataInputDTO.getProjectId();
        String typingDataId = deleteTypingDataInputDTO.getTypingDataId();
        Project project = projectRepository.findById(projectId);

        if (!project.getOwnerId().equals(deleteTypingDataInputDTO.getUser().getId()))
            throw new UnauthorizedException("User does not have permission to delete in this project");

        project.getDatasetIds().forEach(datasetId -> {
            if (datasetRepository.findById(datasetId).getTypingDataId().equals(typingDataId))
                throw new DeniedFileDeletionException("Cannot delete file. File is still being used in one or more datasets. " +
                        "Delete the datasets first.");
        });

        typingDataMetadataRepository.findAllByTypingDataId(typingDataId).forEach(typingDataMetadata -> {
            if (typingDataMetadata.getAdapterId().equals(fileStorageRepository.getAdapterId())) {
                fileStorageRepository.delete(typingDataMetadata.getUrl());
            } else {
                throw new RuntimeException("Unsupported adapter: " + typingDataMetadata.getAdapterId());
            }

            typingDataMetadataRepository.delete(typingDataMetadata);
        });

        project.getFileIds().getTypingDataIds().remove(typingDataId);
        projectRepository.save(project);

        return new DeleteTypingDataOutputDTO(projectId, typingDataId);
    }
}
