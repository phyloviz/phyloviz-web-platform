package org.phyloviz.pwp.gateway.config;

import org.springframework.core.ReactiveAdapterRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.http.codec.HttpMessageWriter;
import org.springframework.web.reactive.HandlerResult;
import org.springframework.web.reactive.accept.RequestedContentTypeResolver;
import org.springframework.web.reactive.result.method.annotation.ResponseBodyResultHandler;
import org.springframework.web.server.ServerWebExchange;
import org.zalando.problem.Problem;
import org.zalando.problem.StatusType;
import reactor.core.publisher.Mono;

import java.util.List;

//TODO configure this class
public class ProblemJsonResponseBodyResultHandler extends ResponseBodyResultHandler {
    public ProblemJsonResponseBodyResultHandler(List<HttpMessageWriter<?>> writers, RequestedContentTypeResolver resolver) {
        super(writers, resolver);
    }

    public ProblemJsonResponseBodyResultHandler(List<HttpMessageWriter<?>> writers, RequestedContentTypeResolver resolver, ReactiveAdapterRegistry registry) {
        super(writers, resolver, registry);
    }

    @Override
    public Mono<Void> handleResult(ServerWebExchange exchange, HandlerResult result) {
        Object body = result.getReturnValue();
        if (!(body instanceof Problem problem))
            return null;

        if (problem.getStatus() != null) {
            StatusType problemStatus = problem.getStatus();

            HttpStatus status;
            if (problemStatus == null)
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            else
                status = HttpStatus.valueOf(problemStatus.getStatusCode());

            exchange.getResponse().setStatusCode(status);
        }


        return writeBody(body, result.getReturnTypeSource(), exchange);
    }
}
