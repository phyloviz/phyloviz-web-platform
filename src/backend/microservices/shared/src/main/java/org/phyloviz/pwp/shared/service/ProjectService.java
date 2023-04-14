package org.phyloviz.pwp.shared.service;

import org.phyloviz.pwp.shared.repository.metadata.project.documents.Project;

public interface ProjectService {

    Project getProject(String projectId, String userId);

    void assertHasAccess(String projectId, String userId);
}
