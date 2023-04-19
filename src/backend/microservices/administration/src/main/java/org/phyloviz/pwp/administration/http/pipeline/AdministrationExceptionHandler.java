package org.phyloviz.pwp.administration.http.pipeline;

import org.phyloviz.pwp.shared.service.exceptions.*;
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
     * @return a Problem with the status BAD_REQUEST
     */
    @ExceptionHandler(value = {
            InvalidArgumentException.class,
            DeniedFileDeletionException.class,
            DeniedResourceDeletionException.class,
            IsolateDataDoesNotExistException.class,
            TypingDataDoesNotExistException.class
    })
    public Problem handleBadRequestException(Exception e) {
        return Problem.builder()
                .withTitle(Status.BAD_REQUEST.getReasonPhrase())
                .withDetail(e.getMessage())
                .withStatus(Status.BAD_REQUEST)
                .build();
    }
}
