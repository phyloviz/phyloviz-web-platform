package org.phyloviz.pwp.gateway.http.controllers;

import org.phyloviz.pwp.gateway.http.controllers.exceptions.AuthenticationException;
import org.phyloviz.pwp.gateway.http.controllers.models.getSession.GetSessionOutputModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class AuthController {


    //TODO: If the user has been deleted in keycloak,
    // maybe the session should be invalidated automatically without logout?
    @GetMapping(path = "/session", produces = "application/json")
    public Mono<ResponseEntity<GetSessionOutputModel>> getSession(OAuth2AuthenticationToken authentication) {
        if (!(authentication.getPrincipal() instanceof OidcUser user))
            return Mono.error(new AuthenticationException());

        String name = user.getFullName();
        String email = user.getEmail();
        String picture = user.getPicture();
        return Mono.just(ResponseEntity.ok(new GetSessionOutputModel(name, email, picture)));
    }
}
