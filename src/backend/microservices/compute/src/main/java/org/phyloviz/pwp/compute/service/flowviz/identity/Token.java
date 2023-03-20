package org.phyloviz.pwp.compute.service.flowviz.identity;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Token {
    @SerializedName("jwt")
    private String token;
}
