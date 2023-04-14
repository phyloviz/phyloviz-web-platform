package org.phyloviz.pwp.visualization.http.pipeline;

import org.phyloviz.pwp.visualization.service.exceptions.TreeIndexingNeededException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

@RestControllerAdvice
public class VisualizationExceptionHandler {

    /**
     * Handles Bad Request Exceptions.
     *
     * @param e the exception
     * @return a Problem with the status BAD_REQUEST
     */
    @ExceptionHandler(value = {
            TreeIndexingNeededException.class
    })
    public Problem handleBadRequestException(Exception e) {
        return Problem.builder()
                .withTitle("Bad Request")
                .withDetail(e.getMessage())
                .withStatus(Status.BAD_REQUEST)
                .build();
    }
}
