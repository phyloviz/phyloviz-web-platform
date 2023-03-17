package org.phyloviz.pwp.administration.http.pipeline;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.IgnoredPropertyException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.phyloviz.pwp.administration.service.exceptions.ProjectNotFoundException;
import org.phyloviz.pwp.administration.service.exceptions.UnauthorizedExcception;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

@RestControllerAdvice
public class AdministrationExceptionHandler {

    /**
     * Handles Not Found Exceptions.
     *
     * @param e the exception
     * @return a Problem with the status NOT_FOUND
     */
    @ExceptionHandler(value = {
            ProjectNotFoundException.class
    })
    public Problem handleNotFoundException(Exception e) {
        return Problem.builder()
                .withTitle("Not Found")
                .withDetail(e.getMessage())
                .withStatus(Status.NOT_FOUND)
                .build();
    }

    /**
     * Handles Unauthorized Exceptions.
     *
     * @param e the exception
     * @return a Problem with the status FORBIDDEN
     */
    @ExceptionHandler(value = {
            UnauthorizedExcception.class
    })
    public Problem handleForbiddenException(Exception e) {
        return Problem.builder()
                .withTitle("Forbidden")
                .withDetail(e.getMessage())
                .withStatus(Status.FORBIDDEN)
                .build();
    }

    @ExceptionHandler(value = {
            AuthenticationException.class,
            AccessDeniedException.class,
    })
    public Problem handleUnauthorizedException() {
        return Problem.builder()
                .withTitle("Unauthorized")
                .withStatus(Status.UNAUTHORIZED)
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

        String title = "Invalid request body:";

        if (ex.getRootCause() instanceof UnrecognizedPropertyException) {
            UnrecognizedPropertyException unrecognizedPropertyException = (UnrecognizedPropertyException) ex.getRootCause();
            title += "Unknown property " + unrecognizedPropertyException.getPropertyName();
        } else if (ex.getRootCause() instanceof JsonParseException) {
            JsonParseException jsonParseException = (JsonParseException) ex.getRootCause();
            title += jsonParseException.getOriginalMessage();
        } else if (ex.getRootCause() instanceof IgnoredPropertyException) {
            JsonMappingException jsonMappingException = (JsonMappingException) ex.getRootCause();
            title += "Missing property " + jsonMappingException.getOriginalMessage();
        }

        return Problem.builder()
                .withTitle(title)
                .withStatus(Status.BAD_REQUEST)
                .build();
    }

    /**
     * Handles all other exceptions.
     *
     * @return a Problem with the status INTERNAL_SERVER_ERROR
     */
    @ExceptionHandler(
            value = {
                    Exception.class
            }
    )
    public Problem handleInternalServerError() {
        return Problem.builder()
                .withTitle("Internal Server Error")
                .withStatus(Status.INTERNAL_SERVER_ERROR)
                .build();
    }
}
