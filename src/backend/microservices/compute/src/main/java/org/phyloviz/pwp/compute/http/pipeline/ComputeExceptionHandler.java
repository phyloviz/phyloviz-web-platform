package org.phyloviz.pwp.compute.http.pipeline;

import org.phyloviz.pwp.compute.service.exceptions.DatasetDoesNotExistException;
import org.phyloviz.pwp.compute.service.exceptions.DistanceMatrixDoesNotExistException;
import org.phyloviz.pwp.compute.service.exceptions.InvalidWorkflowException;
import org.phyloviz.pwp.compute.service.exceptions.TreeDoesNotExistException;
import org.phyloviz.pwp.compute.service.exceptions.TreeViewDoesNotExistException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

@RestControllerAdvice
public class ComputeExceptionHandler {

    /**
     * Handles Bad Request Exceptions.
     *
     * @param e the exception
     * @return a Problem with the status Bad Request
     */
    @ExceptionHandler(value = {
            InvalidWorkflowException.class,
            DatasetDoesNotExistException.class,
            DistanceMatrixDoesNotExistException.class,
            TreeDoesNotExistException.class,
            TreeViewDoesNotExistException.class
    })
    public Problem handleBadRequestException(Exception e) {
        return Problem.builder()
                .withTitle("Bad Request")
                .withDetail(e.getMessage())
                .withStatus(Status.BAD_REQUEST)
                .build();
    }
}
