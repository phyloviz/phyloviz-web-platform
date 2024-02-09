package org.phyloviz.pwp.http.pipeline.advice.exception.handlers;

import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

@RestControllerAdvice
public class AuthenticationExceptionHandler {

    /**
     * Handles Unauthorized Exceptions.
     *
     * @return a Problem with the status Unauthorized
     */
    @ExceptionHandler(value = {AuthenticationException.class,})
    public Problem handleUnauthorizedException() {
        return Problem.builder()
                .withTitle("Unauthorized")
                .withStatus(Status.UNAUTHORIZED)
                .build();
    }
}
