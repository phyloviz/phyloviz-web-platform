package org.phyloviz.pwp.file_transfer.http.pipeline;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

@RestControllerAdvice
public class FileTransferExceptionHandler {

    /**
     * Handles Bad Request Exceptions.
     *
     * @param e the exception
     * @return a Problem with the status BAD_REQUEST
     */
    @ExceptionHandler(value = {})
    public Problem handleBadRequestException(Exception e) {
        return Problem.builder()
                .withTitle(Status.BAD_REQUEST.getReasonPhrase())
                .withDetail(e.getMessage())
                .withStatus(Status.BAD_REQUEST)
                .build();
    }
}
