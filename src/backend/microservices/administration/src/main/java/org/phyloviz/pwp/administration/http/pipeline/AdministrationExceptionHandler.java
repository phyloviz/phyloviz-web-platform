package org.phyloviz.pwp.administration.http.pipeline;

import org.phyloviz.pwp.shared.service.exceptions.DeniedFileDeletionException;
import org.phyloviz.pwp.shared.service.exceptions.DeniedResourceDeletionException;
import org.phyloviz.pwp.shared.service.exceptions.EmptyDatasetNameException;
import org.phyloviz.pwp.shared.service.exceptions.EmptyProjectNameException;
import org.phyloviz.pwp.shared.service.exceptions.EmptyTypingDataIdException;
import org.phyloviz.pwp.shared.service.exceptions.InvalidIsolateDataIdException;
import org.phyloviz.pwp.shared.service.exceptions.IsolateDataDoesNotExistException;
import org.phyloviz.pwp.shared.service.exceptions.TreeIndexingNeededException;
import org.phyloviz.pwp.shared.service.exceptions.TypingDataDoesNotExistException;
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
            DeniedFileDeletionException.class,
            DeniedResourceDeletionException.class,
            EmptyDatasetNameException.class,
            EmptyProjectNameException.class,
            EmptyTypingDataIdException.class,
            InvalidIsolateDataIdException.class,
            IsolateDataDoesNotExistException.class,
            TypingDataDoesNotExistException.class,
            TreeIndexingNeededException.class
    })
    public Problem handleBadRequestException(Exception e) {
        return Problem.builder()
                .withTitle(Status.BAD_REQUEST.getReasonPhrase())
                .withDetail(e.getMessage())
                .withStatus(Status.BAD_REQUEST)
                .build();
    }
}
