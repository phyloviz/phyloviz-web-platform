package org.phyloviz.pwp.shared.http.pipeline.advice;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import org.zalando.problem.Problem;
import org.zalando.problem.StatusType;

@RestControllerAdvice
public class ProblemJsonResponseBodyAdvice implements ResponseBodyAdvice<Problem> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return Problem.class.isAssignableFrom(returnType.getParameterType());
    }

    @Override
    public Problem beforeBodyWrite(
            Problem problem,
            MethodParameter returnType,
            MediaType selectedContentType,
            Class<? extends HttpMessageConverter<?>> selectedConverterType,
            ServerHttpRequest request,
            ServerHttpResponse response
    ) {
        if (problem == null)
            return null;

        if (problem.getStatus() == null)
            return problem;

        StatusType problemStatus = problem.getStatus();

        HttpStatus status;
        if (problemStatus == null)
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        else
            status = HttpStatus.valueOf(problemStatus.getStatusCode());

        response.setStatusCode(status);

        return problem;
    }
}

