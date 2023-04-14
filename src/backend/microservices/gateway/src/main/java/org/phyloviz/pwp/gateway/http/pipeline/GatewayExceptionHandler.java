package org.phyloviz.pwp.gateway.http.pipeline;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.IgnoredPropertyException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.phyloviz.pwp.gateway.http.controllers.exceptions.AuthenticationException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

@RestControllerAdvice
public class GatewayExceptionHandler {

    /**
     * Handles Authentication Exceptions.
     *
     * @param e the exception
     * @return a Problem with the status UNAUTHORIZED
     */
    @ExceptionHandler(value = {AuthenticationException.class})
    public Problem handleUnauthorizedException(Exception e) {
        return Problem.builder()
                .withTitle(Status.UNAUTHORIZED.getReasonPhrase())
                .withDetail(e.getMessage())
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
        //TODO: Improve this
        String title = "Invalid request body:";

        if (ex.getRootCause() instanceof UnrecognizedPropertyException unrecognizedPropertyException)
            title += "Unknown property " + unrecognizedPropertyException.getPropertyName();
        else if (ex.getRootCause() instanceof JsonParseException jsonParseException)
            title += jsonParseException.getOriginalMessage();
        else if (ex.getRootCause() instanceof IgnoredPropertyException) {
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
    @ExceptionHandler(value = {Exception.class})
    public Problem handleInternalServerError(Exception e) {

        return Problem.builder()
                .withTitle(Status.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .withStatus(Status.INTERNAL_SERVER_ERROR)
                .build();
    }
}
