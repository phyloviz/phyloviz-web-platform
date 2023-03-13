package org.isel.phylovizwebplatform.gateway.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.isel.phylovizwebplatform.gateway.http.models.FileType;
import org.isel.phylovizwebplatform.gateway.repository.data.UploadRepository;
import org.isel.phylovizwebplatform.gateway.repository.metadata.UploadMetadataRepository;
import org.isel.phylovizwebplatform.gateway.repository.metadata.objects.Metadata;
import org.isel.phylovizwebplatform.gateway.repository.metadata.objects.ProfileMetadata;
import org.isel.phylovizwebplatform.gateway.repository.project.Project;

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
    public void store(String projectName, FileType fileType, MultipartFile multipartFile) {
        UUID id = UUID.randomUUID();
        String location = "\\" + projectName + "\\" + id + fileType.getFileExtension();

        final Metadata metadata = switch (fileType) {
            case PROFILE, FASTA, NEWICK, AUXILIARY -> new ProfileMetadata(
                    projectName,
                    uploadRepository.getLocation() + location,
                    fileType.toString(),
                    multipartFile.getOriginalFilename()
            );
        };

        uploadMetadataRepository.store(metadata);

        boolean stored = uploadRepository.store(location, multipartFile);

        if (!stored)
            throw new RuntimeException("Could not store file");
    }

    @Override
    public void createProject(String name, String description, String owner) {
        Project project = new Project(name, description, owner, Collections.emptyList());

        uploadMetadataRepository.storeProject(project);
    }
}
