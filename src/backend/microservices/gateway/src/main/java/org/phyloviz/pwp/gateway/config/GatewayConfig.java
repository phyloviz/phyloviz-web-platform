package org.phyloviz.pwp.gateway.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.gateway.config.MicroservicesProperties.ApiRoute;
import org.phyloviz.pwp.gateway.config.MicroservicesProperties.MicroserviceProperties;
import org.phyloviz.pwp.gateway.http.NegatePathResourceLookupFunction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.GatewayFilterSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.PathContainer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.client.oidc.web.server.logout.OidcClientInitiatedServerLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizedClientRepository;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.security.web.server.authentication.HttpStatusServerEntryPoint;
import org.springframework.security.web.server.authentication.ServerAuthenticationEntryPointFailureHandler;
import org.springframework.security.web.server.authentication.ServerAuthenticationFailureHandler;
import org.springframework.security.web.server.authentication.logout.ServerLogoutSuccessHandler;
import org.springframework.security.web.server.util.matcher.PathPatternParserServerWebExchangeMatcher;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.util.pattern.PathPattern;
import org.springframework.web.util.pattern.PathPatternParser;

import java.util.List;
import java.util.Map;

// TODO: Comment this class

/**
 * Configuration for the gateway.
 */
@Configuration
@RequiredArgsConstructor
@EnableWebFluxSecurity
public class GatewayConfig {
    private final ReactiveClientRegistrationRepository clientRegistrationRepository;

    @Value("${pwp.security.logout-url}")
    private String logoutUrl;

    @Value("${pwp.base-url}")
    private String baseUrl;

    @Value("${pwp.security.oauth2.redirect-uri}")
    private String redirectUri;

    @Value("${pwp.react-client-url}")
    private String reactClientUrl;

    @Value("${pwp.debug}")
    private boolean debug;

    @Value("${pwp.static-folder-path}")
    private String staticFolderPath;

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(
            ServerHttpSecurity http,
            ServerOAuth2AuthorizedClientRepository authorizedClientRepository
    ) {
        http
                .authorizeExchange()
                .pathMatchers("/api/logout", "/api/login/**").permitAll()
                .pathMatchers("/api/**").authenticated()
                .anyExchange().permitAll()
                .and()
                .cors().disable()
                .csrf().disable() //TODO: Implement CSRF in client to enable this
                .exceptionHandling(
                        exceptionHandling -> exceptionHandling.authenticationEntryPoint(
                                serverAuthenticationEntryPoint()
                        )
                )
                .oauth2Login(oauth2LoginSpec ->
                        oauth2LoginSpec
                                .authenticationMatcher(new PathPatternParserServerWebExchangeMatcher(redirectUri))
                                .authenticationFailureHandler(authenticationFailureHandler())
                )
                // This Oauth2Client appears to be adding a web filter that catches the /oauth2/authorization/phyloviz-web-platform-client
                // so I can't change disable that endpoint...... Would be an interesting issue to add
                // to https://github.com/spring-projects/spring-security/issues
                .oauth2Client(oAuth2ClientSpec -> oAuth2ClientSpec.authorizedClientRepository(authorizedClientRepository))
                .logout()
                .logoutUrl(logoutUrl)
                .logoutSuccessHandler(logoutSuccessHandler())
        ;

        return http.build();
    }

    public ServerLogoutSuccessHandler logoutSuccessHandler() {
        OidcClientInitiatedServerLogoutSuccessHandler oidcLogoutSuccessHandler =
                new OidcClientInitiatedServerLogoutSuccessHandler(clientRegistrationRepository);

        oidcLogoutSuccessHandler.setPostLogoutRedirectUri(baseUrl);

        return oidcLogoutSuccessHandler;
    }

    public ServerAuthenticationFailureHandler authenticationFailureHandler() {
        return new ServerAuthenticationEntryPointFailureHandler(serverAuthenticationEntryPoint());
    }

    public ServerAuthenticationEntryPoint serverAuthenticationEntryPoint() {
        return new HttpStatusServerEntryPoint(HttpStatus.UNAUTHORIZED);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder, MicroservicesProperties microservicesProperties) {
        RouteLocatorBuilder.Builder routesBuilder = builder.routes();

        Map<String, MicroserviceProperties> microservices = microservicesProperties.getMicroservices();

        for (MicroserviceProperties properties : microservices.values()) {
            routesBuilder = createApiRoutes(routesBuilder, properties.getUri(), properties.getRoutes());
        }

        // Home requests receive all but api requests
        if (debug) {
            PathPatternParser patternParser = new PathPatternParser();
            PathPattern apiPathPattern = patternParser.parse("/api/**");

            routesBuilder.route("home",
                    routeSpec -> routeSpec
                            .order(Integer.MAX_VALUE)
                            .path("/**")
                            .and()
                            .predicate(request -> !apiPathPattern.matches(PathContainer.parsePath(request.getRequest().getPath().pathWithinApplication().value())))
                            .filters(GatewayFilterSpec::tokenRelay)
                            .uri(reactClientUrl)
            );
        }

        return routesBuilder.build();
    }

    @Bean
    @ConditionalOnProperty(value = "pwp.debug", havingValue = "false")
    RouterFunction<ServerResponse> staticResourceRouter() {
        return RouterFunctions.resources(new NegatePathResourceLookupFunction("/api/**", new FileSystemResource(staticFolderPath)));
    }


    private RouteLocatorBuilder.Builder createApiRoutes(RouteLocatorBuilder.Builder routeBuilder, String uri, List<ApiRoute> routes) {
        for (ApiRoute route : routes) {
            routeBuilder = routeBuilder.route(
                    routeSpec -> routeSpec
                            .path("/api" + route.getPath())
                            .and()
                            .method(route.getMethod())
                            .filters(filterSpec -> filterSpec.stripPrefix(1).tokenRelay())
                            .uri(uri)
            );
        }
        return routeBuilder;
    }

}
