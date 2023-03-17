package org.phyloviz.pwp.shared.auth.user;

import java.util.Map;
import lombok.Data;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthentication;

@Data
public class User {
    private final String id;
    private final String username;
    private final String email;

    public User(BearerTokenAuthentication bearerTokenAuthentication) {
        Map<String, Object> attributes = bearerTokenAuthentication.getTokenAttributes();

        this.id = attributes.get("sub").toString();
        this.username = attributes.get("preferred_username").toString();
        this.email = attributes.get("email").toString();
    }

    public UserDTO toDTO() {
        return new UserDTO(id, username, email);
    }
}
