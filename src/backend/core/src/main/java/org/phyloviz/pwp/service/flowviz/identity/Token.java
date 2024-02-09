package org.phyloviz.pwp.service.flowviz.identity;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Token {
    @SerializedName("jwt")
    private String token;
}
