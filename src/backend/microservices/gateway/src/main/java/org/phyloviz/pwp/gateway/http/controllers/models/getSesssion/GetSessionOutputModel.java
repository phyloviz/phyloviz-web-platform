package org.phyloviz.pwp.gateway.http.controllers.models.getSesssion;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetSessionOutputModel {
    private String name;
    private String email;
    private String picture;
}
