package org.phyloviz.pwp.administration.service;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.administration.service.dtos.ProjectDTO;
import org.phyloviz.pwp.administration.service.dtos.createProject.CreateProjectInputDTO;
import org.phyloviz.pwp.administration.service.dtos.createProject.CreateProjectOutputDTO;
import org.phyloviz.pwp.administration.service.dtos.deleteDistanceMatrix.DeleteDistanceMatrixInputDTO;
import org.phyloviz.pwp.administration.service.dtos.deleteDistanceMatrix.DeleteDistanceMatrixOutputDTO;
import org.phyloviz.pwp.administration.service.dtos.deleteInferenceTree.DeleteInferenceTreeInputDTO;
import org.phyloviz.pwp.administration.service.dtos.deleteInferenceTree.DeleteInferenceTreeOutputDTO;
import org.phyloviz.pwp.administration.service.dtos.deleteProject.DeleteProjectInputDTO;
import org.phyloviz.pwp.administration.service.dtos.deleteProject.DeleteProjectOutputDTO;
import org.phyloviz.pwp.administration.service.dtos.deleteTreeView.DeleteTreeViewInputDTO;
import org.phyloviz.pwp.administration.service.dtos.deleteTreeView.DeleteTreeViewOutputDTO;
import org.phyloviz.pwp.administration.service.dtos.deleteTypingDataset.DeleteTypingDatasetInputDTO;
import org.phyloviz.pwp.administration.service.dtos.deleteTypingDataset.DeleteTypingDatasetOutputDTO;
import org.phyloviz.pwp.shared.repository.data.ObjectStorageRepository;
import org.phyloviz.pwp.shared.repository.metadata.distance_matrix.DistanceMatrixMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.distance_matrix.documents.DistanceMatrixMetadata;
import org.phyloviz.pwp.shared.repository.metadata.inference_tree.InferenceTreeMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.inference_tree.documents.InferenceTreeMetadata;
import org.phyloviz.pwp.shared.repository.metadata.project.ProjectRepository;
import org.phyloviz.pwp.shared.repository.metadata.project.documents.Project;
import org.phyloviz.pwp.shared.repository.metadata.tree_view.TreeViewMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.tree_view.documents.TreeViewMetadata;
import org.phyloviz.pwp.shared.repository.metadata.typing_dataset.TypingDatasetMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.typing_dataset.documents.TypingDatasetMetadata;
import org.phyloviz.pwp.shared.service.dtos.UserDTO;
import org.phyloviz.pwp.shared.service.exceptions.UnauthorizedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

/**
 * Implementation of the {@link AdministrationService} interface.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class AdministrationServiceImpl implements AdministrationService {

    private final ObjectStorageRepository objectStorageRepository;

    private final ProjectRepository projectRepository;
    private final TypingDatasetMetadataRepository typingDatasetMetadataRepository;
    private final DistanceMatrixMetadataRepository distanceMatrixMetadataRepository;
    private final InferenceTreeMetadataRepository inferenceTreeMetadataRepository;
    private final TreeViewMetadataRepository treeViewMetadataRepository;

    private static final String TYPING_DATASET_COLLECTION = "typing-datasets";
    private static final String DISTANCE_MATRIX_COLLECTION = "distance-matrices";
    private static final String INFERENCE_TREE_COLLECTION = "inference-trees";
    private static final String TREE_VIEW_COLLECTION = "tree-views";

    @Override
    public CreateProjectOutputDTO createProject(CreateProjectInputDTO createProjectInputDTO) {
        Project project = new Project(
                createProjectInputDTO.getName(),
                createProjectInputDTO.getDescription(),
                createProjectInputDTO.getOwner().getId(),
                Collections.emptyList()
        );

        Project storedProject = projectRepository.save(project);
        return new CreateProjectOutputDTO(storedProject.getId());
    }

    @Override
    public DeleteProjectOutputDTO deleteProject(DeleteProjectInputDTO deleteProjectInputDTO) {
        String projectId = deleteProjectInputDTO.getProjectId();
        String userId = deleteProjectInputDTO.getUser().getId();

        Project project = projectRepository.findById(projectId);

        if (!project.getOwnerId().equals(userId))
            throw new UnauthorizedException("User is not the owner of the project");

        project.getResources().forEach(resource -> {
            deleteResource(resource.getId(), resource.getCollection());
        });

        projectRepository.delete(project);

        return new DeleteProjectOutputDTO(projectId);
    }

    @Override
    public DeleteTypingDatasetOutputDTO deleteTypingDataset(DeleteTypingDatasetInputDTO deleteTypingDatasetInputDTO) {
        String userId = deleteTypingDatasetInputDTO.getUser().getId();
        String typingDatasetId = deleteTypingDatasetInputDTO.getTypingDatasetId();

        // TODO: 3/20/2023 Change the return of findByResourceId to Optional and check if the metadata is even existent
        String projectId = typingDatasetMetadataRepository.findByResourceId(typingDatasetId).getProjectId();
        Project project = projectRepository.findById(projectId);

        if (!project.getOwnerId().equals(userId))
            throw new UnauthorizedException("User is not the owner of the project");

        deleteResource(projectId, TYPING_DATASET_COLLECTION);

        return new DeleteTypingDatasetOutputDTO(projectId, typingDatasetId);
    }

    @Override
    public DeleteDistanceMatrixOutputDTO deleteDistanceMatrix(DeleteDistanceMatrixInputDTO deleteDistanceMatrixInputDTO) {
        String userId = deleteDistanceMatrixInputDTO.getUser().getId();
        String distanceMatrixId = deleteDistanceMatrixInputDTO.getDistanceMatrixId();

        String projectId = distanceMatrixMetadataRepository.findByResourceId(distanceMatrixId).getProjectId();
        Project project = projectRepository.findById(projectId);

        if (!project.getOwnerId().equals(userId))
            throw new UnauthorizedException("User is not the owner of the project");

        deleteResource(projectId, DISTANCE_MATRIX_COLLECTION);

        return new DeleteDistanceMatrixOutputDTO(projectId, distanceMatrixId);
    }

    @Override
    public DeleteInferenceTreeOutputDTO deleteInferenceTree(DeleteInferenceTreeInputDTO deleteInferenceTreeInputDTO) {
        String userId = deleteInferenceTreeInputDTO.getUser().getId();
        String inferenceTreeId = deleteInferenceTreeInputDTO.getInferenceTreeId();

        String projectId = inferenceTreeMetadataRepository.findByResourceId(inferenceTreeId).getProjectId();
        Project project = projectRepository.findById(projectId);

        if (!project.getOwnerId().equals(userId))
            throw new UnauthorizedException("User is not the owner of the project");

        deleteResource(projectId, INFERENCE_TREE_COLLECTION);

        return new DeleteInferenceTreeOutputDTO(projectId, inferenceTreeId);
    }

    @Override
    public DeleteTreeViewOutputDTO deleteTreeView(DeleteTreeViewInputDTO deleteTreeViewInputDTO) {
        String userId = deleteTreeViewInputDTO.getUser().getId();
        String treeViewId = deleteTreeViewInputDTO.getTreeViewId();

        String projectId = treeViewMetadataRepository.findByResourceId(treeViewId).getProjectId();
        Project project = projectRepository.findById(projectId);

        if (!project.getOwnerId().equals(userId))
            throw new UnauthorizedException("User is not the owner of the project");

        deleteResource(projectId, TREE_VIEW_COLLECTION);

        return new DeleteTreeViewOutputDTO(projectId, treeViewId);
    }

    @Override
    public ProjectDTO getProject(String projectId, UserDTO toDTO) {
        Project project = projectRepository.findById(projectId);

        if (!project.getOwnerId().equals(toDTO.getId()))
            throw new UnauthorizedException("User is not the owner of the project");

        return new ProjectDTO(project);
    }

    @Override
    public List<ProjectDTO> getProjects(UserDTO userDTO) {
        List<Project> projects = projectRepository.findAllProjectsByOwnerId(userDTO.getId());

        return projects.stream().map(ProjectDTO::new).toList();
    }

    /**
     * Recursively deletes all representations of a resource and all its children.
     *
     * @param resourceId the id of the resource to delete
     * @param collection the collection of the resource to delete
     */
    private void deleteResource(String resourceId, String collection) {
        switch (collection) {
            case TYPING_DATASET_COLLECTION -> {
                projectRepository.findAllResourceRepresentations(resourceId, TypingDatasetMetadata.class)
                        .forEach(typingDatasetMetadata -> {
                            // Delete children
                            typingDatasetMetadata.getDistanceMatrixIds().forEach(distanceMatrixId -> {
                                deleteResource(distanceMatrixId, DISTANCE_MATRIX_COLLECTION);
                            });

                            objectStorageRepository.delete(typingDatasetMetadata.getUrl());


                            // TODO: 3/19/2023 Delete the actual data

                            // Delete the metadata
                            typingDatasetMetadataRepository.delete(typingDatasetMetadata);
                        });
            }
            case DISTANCE_MATRIX_COLLECTION -> {
                projectRepository.findAllResourceRepresentations(resourceId, DistanceMatrixMetadata.class)
                        .forEach(distanceMatrixMetadata -> {
                            // Delete children
                            distanceMatrixMetadata.getInferenceTreeIds().forEach(inferenceTreeId -> {
                                deleteResource(inferenceTreeId, INFERENCE_TREE_COLLECTION);
                            });

                            // TODO: 3/20/2023 Delete the actual data

                            // Delete the metadata
                            distanceMatrixMetadataRepository.deleteDistanceMatrix(distanceMatrixMetadata);
                        });
            }
            case INFERENCE_TREE_COLLECTION -> {
                projectRepository.findAllResourceRepresentations(resourceId, InferenceTreeMetadata.class)
                        .forEach(inferenceTreeMetadata -> {
                            // Delete children
                            inferenceTreeMetadata.getTreeViewIds().forEach(inferenceTreeId -> {
                                deleteResource(inferenceTreeId, INFERENCE_TREE_COLLECTION);
                            });

                            // TODO: 3/20/2023 Delete the actual data

                            // Delete the metadata
                            inferenceTreeMetadataRepository.deleteInferenceTree(inferenceTreeMetadata);
                        });
            }
            case TREE_VIEW_COLLECTION -> {
                projectRepository.findAllResourceRepresentations(resourceId, TreeViewMetadata.class)
                        .forEach(treeViewMetadata -> {
                            // No children to delete

                            // TODO: 3/20/2023 Delete the actual data

                            // Delete the metadata
                            treeViewMetadataRepository.deleteTreeView(treeViewMetadata);
                        });
            }
        }
    }
}
