package org.phyloviz.pwp.administration.service.projects.files.isolateData;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.administration.repository.data.FileStorageRepository;
import org.phyloviz.pwp.shared.repository.metadata.dataset.DatasetRepository;
import org.phyloviz.pwp.shared.repository.metadata.isolateData.documents.IsolateDataS3AdapterSpecificData;
import org.phyloviz.pwp.shared.repository.metadata.project.ProjectRepository;
import org.phyloviz.pwp.shared.repository.metadata.project.documents.Project;
import org.phyloviz.pwp.administration.service.dtos.files.IsolateDataDTO;
import org.phyloviz.pwp.administration.service.dtos.files.deleteIsolateData.DeleteIsolateDataInputDTO;
import org.phyloviz.pwp.administration.service.dtos.files.deleteIsolateData.DeleteIsolateDataOutputDTO;
import org.phyloviz.pwp.administration.service.dtos.files.uploadIsolateData.UploadIsolateDataInputDTO;
import org.phyloviz.pwp.administration.service.dtos.files.uploadIsolateData.UploadIsolateDataOutputDTO;
import org.phyloviz.pwp.administration.service.exceptions.DeniedFileDeletionException;
import org.phyloviz.pwp.shared.repository.metadata.isolateData.IsolateDataMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.isolateData.documents.IsolateDataMetadata;
import org.phyloviz.pwp.shared.service.exceptions.UnauthorizedException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class IsolateDataServiceImpl implements IsolateDataService {
    
    private final ProjectRepository projectRepository;
    private final DatasetRepository datasetRepository;
    private final FileStorageRepository fileStorageRepository;
    private final IsolateDataMetadataRepository isolateDataMetadataRepository;

    @Override
    public UploadIsolateDataOutputDTO uploadIsolateData(UploadIsolateDataInputDTO uploadIsolateDataInputDTO) {
        String projectId = uploadIsolateDataInputDTO.getProjectId();
        MultipartFile multipartFile = uploadIsolateDataInputDTO.getMultipartFile();
        Project project = projectRepository.findById(projectId);

        if (!project.getOwnerId().equals(uploadIsolateDataInputDTO.getUser().getId()))
            throw new UnauthorizedException("User does not have permission to upload to this project");

        String isolateDataId = UUID.randomUUID().toString();
        String location = "/" + projectId + "/" + "isolateData/" + isolateDataId;

        final IsolateDataMetadata isolateDataMetadata = new IsolateDataMetadata(
                projectId,
                isolateDataId,
                multipartFile.getOriginalFilename(),
                fileStorageRepository.getLocation() + location,
                fileStorageRepository.getAdapterId(),
                new IsolateDataS3AdapterSpecificData(multipartFile.getOriginalFilename())
        );

        isolateDataMetadataRepository.save(isolateDataMetadata);

        boolean stored = fileStorageRepository.store(location, multipartFile);

        if (!stored)
            throw new RuntimeException("Could not store file");

        project.getFileIds().getIsolateDataIds().add(isolateDataId);
        projectRepository.save(project);

        return new UploadIsolateDataOutputDTO(projectId, isolateDataId);
    }

    @Override
    public DeleteIsolateDataOutputDTO deleteIsolateData(DeleteIsolateDataInputDTO deleteIsolateDataInputDTO) {
        String projectId = deleteIsolateDataInputDTO.getProjectId();
        String isolateDataId = deleteIsolateDataInputDTO.getIsolateDataId();
        Project project = projectRepository.findById(projectId);

        if (!project.getOwnerId().equals(deleteIsolateDataInputDTO.getUser().getId()))
            throw new UnauthorizedException("User does not have permission to delete in this project");

        project.getDatasetIds().forEach(datasetId -> {
            if (datasetRepository.findById(datasetId).getIsolateDataId().equals(isolateDataId))
                throw new DeniedFileDeletionException("Cannot delete file. File is still being used in one or more datasets. " +
                        "Delete the datasets first.");
        });

        deleteIsolateData(isolateDataId);

        project.getFileIds().getIsolateDataIds().remove(isolateDataId);
        projectRepository.save(project);

        return new DeleteIsolateDataOutputDTO(projectId, isolateDataId);
    }

    @Override
    public IsolateDataDTO getIsolateData(String isolateDataId) {
        IsolateDataMetadata isolateDataMetadata = isolateDataMetadataRepository.findByIsolateDataId(isolateDataId);

        return new IsolateDataDTO(
                isolateDataMetadata.getIsolateDataId(),
                isolateDataMetadata.getName()
        );
    }

    @Override
    public void deleteIsolateData(String isolateDataId) {
        isolateDataMetadataRepository.findAllByIsolateDataId(isolateDataId).forEach(isolateDataMetadata -> {
            if (isolateDataMetadata.getAdapterId().equals(fileStorageRepository.getAdapterId())) {
                fileStorageRepository.delete(isolateDataMetadata.getUrl());
            } else {
                throw new RuntimeException("Unsupported adapter: " + isolateDataMetadata.getAdapterId());
            }

            isolateDataMetadataRepository.delete(isolateDataMetadata);
        });
    }
}
