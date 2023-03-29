package org.phyloviz.pwp.gateway.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.client.reactive.ReactiveOAuth2ClientAutoConfiguration;
import org.springframework.boot.autoconfigure.web.reactive.WebFluxAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.core.ReactiveAdapterRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.client.oidc.web.server.logout.OidcClientInitiatedServerLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.server.WebSessionServerOAuth2AuthorizedClientRepository;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.security.web.server.authentication.HttpStatusServerEntryPoint;
import org.springframework.security.web.server.authentication.ServerAuthenticationEntryPointFailureHandler;
import org.springframework.security.web.server.authentication.ServerAuthenticationFailureHandler;
import org.springframework.security.web.server.authentication.logout.ServerLogoutHandler;
import org.springframework.security.web.server.authentication.logout.ServerLogoutSuccessHandler;
import org.springframework.security.web.server.authentication.logout.WebSessionServerLogoutHandler;
import org.springframework.security.web.server.ui.LogoutPageGeneratingWebFilter;
import org.springframework.security.web.server.util.matcher.PathPatternParserServerWebExchangeMatcher;
import org.springframework.web.reactive.accept.RequestedContentTypeResolver;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.server.support.ServerResponseResultHandler;
import org.springframework.web.reactive.result.method.annotation.ResponseBodyResultHandler;
import org.zalando.problem.jackson.ProblemModule;

@Configuration
@RequiredArgsConstructor
@EnableWebFluxSecurity
public class GatewayConfig  {
    private final ReactiveClientRegistrationRepository clientRegistrationRepository;

    @Value("${pwp.security.logout-url}")
    private String logoutUrl;

    @Value("${pwp.base-url}")
    private String baseUrl;

    @Value("${pwp.security.oauth2.redirect-uri}")
    private String redirectUri;

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(
            ServerHttpSecurity http,
            ServerOAuth2AuthorizedClientRepository authorizedClientRepository
    ) {

        http
                .authorizeExchange()
                .pathMatchers("/api/**").authenticated()
                .anyExchange().permitAll()
                .and()
                .cors().disable()
                .csrf().disable() //TODO: Implement CSRF in client to enable this
                .exceptionHandling(
                        (exceptionHandling) -> exceptionHandling.authenticationEntryPoint(
                                serverAuthenticationEntryPoint()
                        )
                )
                .oauth2Login(oauth2LoginSpec ->
                        oauth2LoginSpec
                                .authenticationMatcher(
                                        new PathPatternParserServerWebExchangeMatcher(redirectUri)
                                )
                                .authenticationFailureHandler(authenticationFailureHandler())
                )
                // This Oauth2Client appears to be adding a web filter that catches the /oauth2/authorization/phyloviz-web-platform-client
                // so I can't change disable that endpoint...... Would be an interesting issue to add
                // to https://github.com/spring-projects/spring-security/issues
                .oauth2Client(oAuth2ClientSpec -> oAuth2ClientSpec.authorizedClientRepository(authorizedClientRepository))
                .logout(logoutSpec ->
                        logoutSpec
                                .logoutUrl(logoutUrl)
                                .logoutHandler(logoutHandler())
                                .logoutSuccessHandler(logoutSuccessHandler())
                );

        return http.build();
    }


    // Saves the authorized client (containing access, refresh token, etc)
    // in the spring session. since we included the redis session dependency
    // the spring session will be saved in redis
    // so the authorized client will be saved in redis too
    @Bean
    public ServerOAuth2AuthorizedClientRepository authorizedClientRepository() {
        return new WebSessionServerOAuth2AuthorizedClientRepository();
    }

    // Maybe use a service instead of the repository? Storing all the access and refresh tokens in a session is sus...
//    @Bean
//    public ReactiveOAuth2AuthorizedClientService authorizedClientService() {
//        return new RedisReactiveOAuth2AuthorizedClientService();
//    }

    public ServerLogoutHandler logoutHandler() {
        // Deletes Web Sesssion
        return new WebSessionServerLogoutHandler();
    }

    public ServerLogoutSuccessHandler logoutSuccessHandler() {
        OidcClientInitiatedServerLogoutSuccessHandler oidcLogoutSuccessHandler =
                new OidcClientInitiatedServerLogoutSuccessHandler(clientRegistrationRepository);
        oidcLogoutSuccessHandler.setLogoutSuccessUrl(URI.create(baseUrl)); //Doing useless redirect...
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
        return new ObjectMapper()
                .setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }
}
