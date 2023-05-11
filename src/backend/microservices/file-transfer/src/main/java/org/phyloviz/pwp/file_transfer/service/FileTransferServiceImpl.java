package org.phyloviz.pwp.file_transfer.service;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.data.isolate_data.IsolateDataDataRepositoryId;
import org.phyloviz.pwp.shared.repository.data.isolate_data.repository.specific_data.IsolateDataDataRepositorySpecificData;
import org.phyloviz.pwp.shared.repository.data.registry.isolate_data.IsolateDataDataRepositoryFactory;
import org.phyloviz.pwp.shared.repository.data.registry.typing_data.TypingDataDataRepositoryFactory;
import org.phyloviz.pwp.shared.repository.data.typing_data.TypingDataDataRepositoryId;
import org.phyloviz.pwp.shared.repository.data.typing_data.repository.specific_data.TypingDataDataRepositorySpecificData;
import org.phyloviz.pwp.shared.repository.metadata.isolate_data.IsolateDataMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.isolate_data.documents.IsolateDataMetadata;
import org.phyloviz.pwp.shared.repository.metadata.project.ProjectRepository;
import org.phyloviz.pwp.shared.repository.metadata.typing_data.TypingDataMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.typing_data.documents.TypingDataMetadata;
import org.phyloviz.pwp.shared.service.dtos.files.isolate_data.UploadIsolateDataOutput;
import org.phyloviz.pwp.shared.service.dtos.files.typing_data.UploadTypingDataOutput;
import org.phyloviz.pwp.shared.service.exceptions.MultipartFileReadException;
import org.phyloviz.pwp.shared.service.exceptions.ProjectNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileTransferServiceImpl implements FileTransferService {

    private final ProjectRepository projectRepository;

    private final TypingDataMetadataRepository typingDataMetadataRepository;
    private final IsolateDataMetadataRepository isolateDataMetadataRepository;

    private final IsolateDataDataRepositoryFactory isolateDataDataRepositoryFactory;
    private final TypingDataDataRepositoryFactory typingDataDataRepositoryFactory;

    @Value("${data-repositories.upload-typing-data-repository}")
    private TypingDataDataRepositoryId uploadTypingDataRepositoryId;

    @Value("${data-repositories.upload-isolate-data-repository}")
    private IsolateDataDataRepositoryId uploadIsolateDataRepositoryId;

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
                uploadTypingDataRepositoryId,
                typingDataDataRepositorySpecificData
        );

        typingDataMetadataRepository.save(typingDataMetadata);

        return new UploadTypingDataOutput(projectId, typingDataId);
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
                uploadIsolateDataRepositoryId,
                isolateDataDataRepositorySpecificData
        );

        isolateDataMetadataRepository.save(isolateDataMetadata);

        return new UploadIsolateDataOutput(projectId, isolateDataId);
    }

    private List<String> getIsolateDataKeys(MultipartFile file) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            String firstLine = reader.readLine();

            return List.of(firstLine.split("/\s+/")); // TODO: Check regex (maybe / +/)
        } catch (IOException e) {
            throw new MultipartFileReadException("Could not read first line of file", e);
        }
    }
}
