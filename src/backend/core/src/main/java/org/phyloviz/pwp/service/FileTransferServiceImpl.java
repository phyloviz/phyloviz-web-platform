package org.phyloviz.pwp.service;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.repository.data.isolate_data.IsolateDataDataRepositoryId;
import org.phyloviz.pwp.repository.data.isolate_data.repository.IsolateDataDataRepository;
import org.phyloviz.pwp.repository.data.isolate_data.repository.specific_data.IsolateDataDataRepositorySpecificData;
import org.phyloviz.pwp.repository.data.registry.isolate_data.IsolateDataDataRepositoryFactory;
import org.phyloviz.pwp.repository.data.registry.typing_data.TypingDataDataRepositoryFactory;
import org.phyloviz.pwp.repository.data.typing_data.TypingDataDataRepositoryId;
import org.phyloviz.pwp.repository.data.typing_data.repository.TypingDataDataRepository;
import org.phyloviz.pwp.repository.data.typing_data.repository.specific_data.TypingDataDataRepositorySpecificData;
import org.phyloviz.pwp.repository.metadata.isolate_data.IsolateDataMetadataRepository;
import org.phyloviz.pwp.repository.metadata.isolate_data.documents.IsolateDataMetadata;
import org.phyloviz.pwp.repository.metadata.project.ProjectRepository;
import org.phyloviz.pwp.repository.metadata.typing_data.TypingDataMetadataRepository;
import org.phyloviz.pwp.repository.metadata.typing_data.documents.TypingDataMetadata;
import org.phyloviz.pwp.service.dtos.files.isolate_data.UploadIsolateDataOutput;
import org.phyloviz.pwp.service.dtos.files.typing_data.UploadTypingDataOutput;
import org.phyloviz.pwp.service.exceptions.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class FileTransferServiceImpl implements FileTransferService {

    private final ProjectRepository projectRepository;

    private final TypingDataMetadataRepository typingDataMetadataRepository;
    private final IsolateDataMetadataRepository isolateDataMetadataRepository;

    private final IsolateDataDataRepositoryFactory isolateDataDataRepositoryFactory;
    private final TypingDataDataRepositoryFactory typingDataDataRepositoryFactory;

    @Value("${data-repositories.upload-typing-data-repository}")
    private TypingDataDataRepositoryId uploadTypingDataRepositoryId;

    @Value("${data-repositories.download-typing-data-repository}")
    private TypingDataDataRepositoryId downloadTypingDataRepositoryId;

    @Value("${data-repositories.upload-isolate-data-repository}")
    private IsolateDataDataRepositoryId uploadIsolateDataRepositoryId;

    @Value("${data-repositories.download-isolate-data-repository}")
    private IsolateDataDataRepositoryId downloadIsolateDataRepositoryId;

    @Override
    public UploadTypingDataOutput uploadTypingData(String projectId, MultipartFile file, String type, String userId) {
        if (!projectRepository.existsByIdAndOwnerId(projectId, userId))
            throw new ProjectNotFoundException();

        String typingDataId = UUID.randomUUID().toString();

        TypingDataDataRepositorySpecificData typingDataDataRepositorySpecificData = typingDataDataRepositoryFactory
                .getRepository(uploadTypingDataRepositoryId)
                .uploadTypingData(projectId, typingDataId, file);

        TypingDataMetadata typingDataMetadata = new TypingDataMetadata(
                projectId,
                typingDataId,
                type,
                file.getOriginalFilename(),
                Map.of(uploadTypingDataRepositoryId, typingDataDataRepositorySpecificData)
        );

        typingDataMetadataRepository.save(typingDataMetadata);

        return new UploadTypingDataOutput(projectId, typingDataId);
    }

    @Override
    public String downloadTypingData(String projectId, String typingDataId, String userId) {
        if (!projectRepository.existsByIdAndOwnerId(projectId, userId))
            throw new ProjectNotFoundException();

        TypingDataMetadata typingDataMetadata = typingDataMetadataRepository
                .findByProjectIdAndTypingDataId(projectId, typingDataId)
                .orElseThrow(TypingDataNotFoundException::new);

        if (!typingDataMetadata.getRepositorySpecificData().containsKey(downloadTypingDataRepositoryId))
            throw new FileCorruptedException("Typing Data isn't in the file storage, but has metadata.");

        TypingDataDataRepositorySpecificData repositorySpecificData = typingDataMetadata
                .getRepositorySpecificData()
                .get(downloadTypingDataRepositoryId);

        TypingDataDataRepository typingDataDataRepository = typingDataDataRepositoryFactory.getRepository(downloadTypingDataRepositoryId);

        return typingDataDataRepository.downloadTypingData(repositorySpecificData);
    }

    @Override
    public UploadIsolateDataOutput uploadIsolateData(String projectId, MultipartFile file, String userId) {
        if (!projectRepository.existsByIdAndOwnerId(projectId, userId))
            throw new ProjectNotFoundException();

        String isolateDataId = UUID.randomUUID().toString();

        IsolateDataDataRepositorySpecificData isolateDataDataRepositorySpecificData = isolateDataDataRepositoryFactory
                .getRepository(uploadIsolateDataRepositoryId)
                .uploadIsolateData(projectId, isolateDataId, file);

        IsolateDataMetadata isolateDataMetadata = new IsolateDataMetadata(
                projectId,
                isolateDataId,
                getIsolateDataKeys(file),
                file.getOriginalFilename(),
                Map.of(uploadIsolateDataRepositoryId, isolateDataDataRepositorySpecificData)
        );

        isolateDataMetadataRepository.save(isolateDataMetadata);

        return new UploadIsolateDataOutput(projectId, isolateDataId);
    }

    @Override
    public String downloadIsolateData(String projectId, String isolateDataId, String userId) {
        if (!projectRepository.existsByIdAndOwnerId(projectId, userId))
            throw new ProjectNotFoundException();

        IsolateDataMetadata isolateDataMetadata = isolateDataMetadataRepository
                .findByProjectIdAndIsolateDataId(projectId, isolateDataId)
                .orElseThrow(IsolateDataNotFoundException::new);

        if (!isolateDataMetadata.getRepositorySpecificData().containsKey(downloadIsolateDataRepositoryId))
            throw new FileCorruptedException("Isolate Data isn't in the file storage, but has metadata.");

        IsolateDataDataRepositorySpecificData repositorySpecificData = isolateDataMetadata
                .getRepositorySpecificData()
                .get(downloadIsolateDataRepositoryId);

        IsolateDataDataRepository isolateDataDataRepository = isolateDataDataRepositoryFactory.getRepository(downloadIsolateDataRepositoryId);

        return isolateDataDataRepository.downloadIsolateData(repositorySpecificData);
    }

    private List<String> getIsolateDataKeys(MultipartFile file) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            String firstLine = reader.readLine();
            reader.close();

            return List.of(firstLine.split("\t+"));
        } catch (IOException e) {
            throw new MultipartFileReadException("Could not read first line of file", e);
        }
    }
}
