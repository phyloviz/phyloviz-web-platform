package org.phyloviz.pwp.administration.repository.metadata.project;

import com.nimbusds.jose.shaded.gson.Gson;
import org.phyloviz.pwp.shared.repository.metadata.Metadata;
import org.phyloviz.pwp.administration.repository.metadata.project.documents.Project;
import org.phyloviz.pwp.shared.utils.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class ProjectRepositoryDisk implements ProjectRepository {
    private final String metadataPath = new File("").getAbsolutePath() + "\\diskMetadataFiles";

    @Override
    public Project save(Project project) {
        String id = UUID.randomUUID().toString();

        File file = new File(metadataPath + "\\projects\\" + id + ".json");
        try (FileWriter fileWriter = new FileWriter(file)) {
            new Gson().toJson(project, fileWriter);
        } catch (IOException e) {
            Logger.warn("Error while storing project in disk" + e.getMessage());
        }

        return new Project(id, project.getName(), project.getDescription(), project.getOwnerId(), project.getResources());
    }

    @Override
    public Project findById(String id) {
        return null;
    }

    @Override
    public void delete(Project project) {
        File file = new File(metadataPath + "\\projects\\" + project.getId() + ".json");

        if (file.exists()) {
            file.delete();
        }
    }

    @Override
    public <T extends Metadata> List<T> findAllResourceRepresentations(String resourceId, Class<T> resourceClass) {
        return null;
    }

    @Override
    public List<Project> findAllProjectsByOwnerId(String ownerId) {
        return null;
    }
}
