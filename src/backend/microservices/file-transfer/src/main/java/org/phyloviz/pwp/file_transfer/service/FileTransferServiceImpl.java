package org.phyloviz.pwp.file_transfer.service;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.adapters.isolate_data.IsolateDataAdapterFactory;
import org.phyloviz.pwp.shared.adapters.isolate_data.IsolateDataAdapterId;
import org.phyloviz.pwp.shared.adapters.isolate_data.adapter.specific_data.IsolateDataAdapterSpecificData;
import org.phyloviz.pwp.shared.adapters.typing_data.TypingDataAdapterFactory;
import org.phyloviz.pwp.shared.adapters.typing_data.TypingDataAdapterId;
import org.phyloviz.pwp.shared.adapters.typing_data.adapter.specific_data.TypingDataAdapterSpecificData;
import org.phyloviz.pwp.shared.repository.metadata.isolate_data.IsolateDataMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.isolate_data.documents.IsolateDataMetadata;
import org.phyloviz.pwp.shared.repository.metadata.project.ProjectRepository;
import org.phyloviz.pwp.shared.repository.metadata.typing_data.TypingDataMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.typing_data.documents.TypingDataMetadata;
import org.phyloviz.pwp.shared.service.dtos.files.isolate_data.UploadIsolateDataOutput;
import org.phyloviz.pwp.shared.service.dtos.files.typing_data.UploadTypingDataOutput;
import org.phyloviz.pwp.shared.service.exceptions.ProjectNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileTransferServiceImpl implements FileTransferService {

    private final ProjectRepository projectRepository;

    private final TypingDataMetadataRepository typingDataMetadataRepository;
    private final IsolateDataMetadataRepository isolateDataMetadataRepository;

    private final IsolateDataAdapterFactory isolateDataAdapterFactory;
    private final TypingDataAdapterFactory typingDataAdapterFactory;

    @Value("${adapters.upload-typing-data-adapter}")
    private TypingDataAdapterId uploadTypingDataAdapter;

    @Value("${adapters.upload-isolate-data-adapter}")
    private IsolateDataAdapterId uploadIsolateDataAdapter;

    @Override
    public UploadTypingDataOutput uploadTypingData(String projectId, MultipartFile file, String userId) {
        if(!projectRepository.existsByIdAndOwnerId(projectId, userId))
            throw new ProjectNotFoundException();

        String typingDataId = UUID.randomUUID().toString();

        TypingDataAdapterSpecificData typingDataAdapterSpecificData = typingDataAdapterFactory
                .getTypingDataAdapter(uploadTypingDataAdapter)
                .uploadTypingData(projectId, typingDataId, file);

        TypingDataMetadata typingDataMetadata = new TypingDataMetadata(
                projectId,
                typingDataId,
                file.getOriginalFilename(),
                uploadTypingDataAdapter,
                typingDataAdapterSpecificData
        );

        typingDataMetadataRepository.save(typingDataMetadata);

        return new UploadTypingDataOutput(projectId, typingDataId);
    }

    @Override
    public UploadIsolateDataOutput uploadIsolateData(String projectId, MultipartFile file, String userId) {
        if(!projectRepository.existsByIdAndOwnerId(projectId, userId))
            throw new ProjectNotFoundException();

        String isolateDataId = UUID.randomUUID().toString();

        IsolateDataAdapterSpecificData isolateDataAdapterSpecificData = isolateDataAdapterFactory
                .getIsolateDataAdapter(uploadIsolateDataAdapter)
                .uploadIsolateData(projectId, isolateDataId, file);

        IsolateDataMetadata isolateDataMetadata = new IsolateDataMetadata(
                projectId,
                isolateDataId,
                file.getOriginalFilename(),
                uploadIsolateDataAdapter,
                isolateDataAdapterSpecificData
        );

        isolateDataMetadataRepository.save(isolateDataMetadata);

        return new UploadIsolateDataOutput(projectId, isolateDataId);
    }
}
