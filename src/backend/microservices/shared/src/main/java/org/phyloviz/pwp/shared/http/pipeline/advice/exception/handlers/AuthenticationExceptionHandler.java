package org.phyloviz.pwp.shared.http.pipeline.advice.exception.handlers;

import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

@RestControllerAdvice
public class AuthenticationExceptionHandler {

    @ExceptionHandler(value = {
            AuthenticationException.class,
    })
    public Problem handleUnauthorizedException() {
        return Problem.builder()
                .withTitle("Unauthorized")
                .withStatus(Status.UNAUTHORIZED)
                .build();
    }
}
