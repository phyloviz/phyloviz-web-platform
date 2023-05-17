package org.phyloviz.pwp.shared.http.pipeline.advice.exception.handlers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.IgnoredPropertyException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.phyloviz.pwp.shared.service.exceptions.DatasetNotFoundException;
import org.phyloviz.pwp.shared.service.exceptions.DistanceMatrixNotFoundException;
import org.phyloviz.pwp.shared.service.exceptions.FileCorruptedException;
import org.phyloviz.pwp.shared.service.exceptions.InvalidArgumentException;
import org.phyloviz.pwp.shared.service.exceptions.IsolateDataNotFoundException;
import org.phyloviz.pwp.shared.service.exceptions.ProjectNotFoundException;
import org.phyloviz.pwp.shared.service.exceptions.TreeNotFoundException;
import org.phyloviz.pwp.shared.service.exceptions.TreeViewNotFoundException;
import org.phyloviz.pwp.shared.service.exceptions.TypingDataNotFoundException;
import org.phyloviz.pwp.shared.service.exceptions.UnauthorizedException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

@RestControllerAdvice
public class SharedExceptionHandler {

    /**
     * Handles Unauthorized Exceptions.
     *
     * @param e the exception
     * @return a Problem with the status FORBIDDEN
     */
    @ExceptionHandler(value = {UnauthorizedException.class})
    public Problem handleForbiddenException(Exception e) {
        return Problem.builder()
                .withTitle("Forbidden")
                .withDetail(e.getMessage())
                .withStatus(Status.FORBIDDEN)
                .build();
    }

    /**
     * Handles Not Found Exceptions.
     *
     * @param e the exception
     * @return a Problem with the status NOT_FOUND
     */
    @ExceptionHandler(value = {
            ProjectNotFoundException.class,
            DatasetNotFoundException.class,
            DistanceMatrixNotFoundException.class,
            TreeNotFoundException.class,
            TreeViewNotFoundException.class,
            TypingDataNotFoundException.class,
            IsolateDataNotFoundException.class
    })
    public Problem handleNotFoundException(Exception e) {
        return Problem.builder()
                .withTitle("Not Found")
                .withDetail(e.getMessage())
                .withStatus(Status.NOT_FOUND)
                .build();
    }

    /**
     * Handles HttpMessageNotReadableExceptions.
     *
     * @param ex the exception
     * @return a Problem with the status BAD_REQUEST
     */
    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    public Problem handleHttpMessageNotReadableExceptions(
            HttpMessageNotReadableException ex
    ) {
        String detail = "Invalid request body:";

        if (ex.getRootCause() instanceof UnrecognizedPropertyException unrecognizedPropertyException) {
            detail += "Unknown property " + unrecognizedPropertyException.getPropertyName();
        } else if (ex.getRootCause() instanceof JsonParseException jsonParseException) {
            detail += jsonParseException.getOriginalMessage();
        } else if (ex.getRootCause() instanceof IgnoredPropertyException) {
            JsonMappingException jsonMappingException = (JsonMappingException) ex.getRootCause();
            detail += "Missing property " + jsonMappingException.getOriginalMessage();
        }

        return Problem.builder()
                .withTitle("Bad Request")
                .withDetail(detail)
                .withStatus(Status.BAD_REQUEST)
                .build();
    }

    /**
     * Handles MissingServletRequestPartException.
     *
     * @param ex the exception
     * @return a Problem with the status BAD_REQUEST
     */
    @ExceptionHandler(value = {MissingServletRequestPartException.class})
    public Problem handleMissingServletRequestPartExceptions(
            MissingServletRequestPartException ex
    ) {
        return Problem.builder()
                .withTitle("Bad Request")
                .withDetail(String.format("Missing request part '%s'", ex.getRequestPartName()))
                .withStatus(Status.BAD_REQUEST)
                .build();
    }

    /**
     * Handles Bad Request.
     *
     * @param e the exception
     * @return a Problem with the status BAD_REQUEST
     */
    @ExceptionHandler(value = {
            FileCorruptedException.class,
            InvalidArgumentException.class
    })
    public Problem handleBadRequestExceptions(Exception e) {
        return Problem.builder()
                .withTitle("Bad Request")
                .withDetail(e.getMessage())
                .withStatus(Status.BAD_REQUEST)
                .build();
    }

    /**
     * Handles all other exceptions.
     *
     * @return a Problem with the status INTERNAL_SERVER_ERROR
     */
    @ExceptionHandler(value = {Exception.class})
    public Problem handleInternalServerError(Exception e) {

        return Problem.builder()
                .withTitle("Internal Server Error")
                .withStatus(Status.INTERNAL_SERVER_ERROR)
                .build();
    }

}
