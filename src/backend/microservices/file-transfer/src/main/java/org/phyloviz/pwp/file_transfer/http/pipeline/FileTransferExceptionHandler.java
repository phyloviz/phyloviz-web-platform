package org.phyloviz.pwp.file_transfer.http.pipeline;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

@RestControllerAdvice
public class FileTransferExceptionHandler {

    @Value("${spring.servlet.multipart.max-file-size}")
    private String MAX_UPLOAD_SIZE;

    /**
     * Handles Multipart Max Upload Size Exceptions.
     *
     * @param e the exception
     * @return a Problem with the status BAD_REQUEST
     */
    @ExceptionHandler(value = {
            MaxUploadSizeExceededException.class
    })
    public Problem handleMultipartMaxUploadException(Exception e) {
        return Problem.builder()
                .withTitle("Bad Request")
                .withDetail("Maximum file upload size exceeded (Max is " + MAX_UPLOAD_SIZE + ")")
                .withStatus(Status.BAD_REQUEST)
                .build();
    }
}
