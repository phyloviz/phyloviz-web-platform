package org.phyloviz.pwp.visualization.http.pipeline;

import org.phyloviz.pwp.visualization.service.exceptions.IndexingNeededException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

@RestControllerAdvice
public class VisualizationExceptionHandler {

    /**
     * Handles Indexing Needed Exceptions.
     *
     * @param e the exception
     * @return a Problem with the status BAD_REQUEST
     */
    @ExceptionHandler(value = {
            IndexingNeededException.class
    })
    public Problem handleIndexingNeededException(Exception e) {
        return Problem.builder()
                .withTitle("Indexing Needed")
                .withDetail(e.getMessage())
                .withStatus(Status.BAD_REQUEST)
                .build();
    }
}
