package org.phyloviz.pwp.http.pipeline;

import org.phyloviz.pwp.service.exceptions.DeniedFileDeletionException;
import org.phyloviz.pwp.service.exceptions.DeniedResourceDeletionException;
import org.phyloviz.pwp.service.exceptions.InvalidArgumentException;
import org.phyloviz.pwp.service.exceptions.IsolateDataDoesNotExistException;
import org.phyloviz.pwp.service.exceptions.TypingDataDoesNotExistException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

@RestControllerAdvice
public class AdministrationExceptionHandler {

    /**
     * Handles Bad Request Exceptions.
     *
     * @param e the exception
     * @return a Problem with the status Bad Request
     */
    @ExceptionHandler(value = {
            InvalidArgumentException.class,
            IsolateDataDoesNotExistException.class,
            TypingDataDoesNotExistException.class
    })
    public Problem handleBadRequestException(Exception e) {
        return Problem.builder()
                .withTitle("Bad Request")
                .withDetail(e.getMessage())
                .withStatus(Status.BAD_REQUEST)
                .build();
    }

    /**
     * Handles Denied Deletion Exceptions.
     *
     * @param e the exception
     * @return a Problem with the status Bad Request
     */
    @ExceptionHandler(value = {
            DeniedFileDeletionException.class,
            DeniedResourceDeletionException.class
    })
    public Problem handleDeniedDeletionException(Exception e) {
        return Problem.builder()
                .withTitle("Denied Deletion")
                .withDetail(e.getMessage())
                .withStatus(Status.BAD_REQUEST)
                .build();
    }
}
