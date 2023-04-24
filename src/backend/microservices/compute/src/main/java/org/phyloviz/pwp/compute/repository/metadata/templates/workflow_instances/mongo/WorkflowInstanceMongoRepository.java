package org.phyloviz.pwp.compute.repository.metadata.templates.workflow_instances.mongo;

import org.phyloviz.pwp.compute.repository.metadata.templates.workflow_instances.documents.WorkflowInstance;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkflowInstanceMongoRepository extends MongoRepository<WorkflowInstance, String> {

    List<WorkflowInstance> findAllByProjectId(String projectId);

    @Query("{ 'projectId' : ?0, 'status' : { $in: ['SUCCESS', 'FAILED'] } }")
    List<WorkflowInstance> findAllByProjectIdAndNotRunning(String projectId);

    @Query("{ 'projectId' : ?0, 'status' : 'RUNNING' }")
    List<WorkflowInstance> findAllByProjectIdAndRunning(String projectId);
}
