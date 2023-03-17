package org.phyloviz.pwp.shared.auth.user;

import lombok.Data;

@Data
public class UserDTO {
    private final String id;
    private final String username;
    private final String email;
}
