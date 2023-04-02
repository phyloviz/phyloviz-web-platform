package org.phyloviz.pwp.shared.repository.metadata.project.mongo;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.metadata.project.ProjectRepository;
import org.phyloviz.pwp.shared.repository.metadata.project.documents.Project;
import org.phyloviz.pwp.shared.service.exceptions.ProjectNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProjectRepositoryMongo implements ProjectRepository {

    private final ProjectMongoRepository projectMongoRepository;

    @Override
    public Project save(Project project) {
        return projectMongoRepository.save(project);
    }

    @Override
    public Project findById(String id) throws ProjectNotFoundException {
        return projectMongoRepository
                .findById(id)
                .orElseThrow(ProjectNotFoundException::new);
    }

    @Override
    public void delete(Project project) {
        projectMongoRepository.delete(project);
    }

    @Override
    public List<Project> findAllByOwnerId(String ownerId) {
        return projectMongoRepository.findAllByOwnerId(ownerId);
    }
}