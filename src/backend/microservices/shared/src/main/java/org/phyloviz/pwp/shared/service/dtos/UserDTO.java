package org.phyloviz.pwp.shared.service.dtos;

import lombok.Data;

@Data
public class UserDTO {
    private final String id;
    private final String username;
    private final String email;
}
