package org.phyloviz.pwp.administration.http.pipeline;

import org.phyloviz.pwp.administration.service.exceptions.ProjectNotFoundException;
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
    @ExceptionHandler(value = {ProjectNotFoundException.class})
    public Problem handleNotFoundException(Exception e) {
        return Problem.builder()
                .withTitle("Not Found")
                .withDetail(e.getMessage())
                .withStatus(Status.NOT_FOUND)
                .build();
    }
}
