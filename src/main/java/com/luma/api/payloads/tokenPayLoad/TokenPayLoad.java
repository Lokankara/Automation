package com.luma.api.payloads.tokenPayLoad;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(fluent = true)
public class TokenPayLoad {

    @JsonProperty("password")
    private String password;

    @JsonProperty("username")
    private String username;

    @Override
    public String toString() {
        return  "password='" + password + '\'' +
                ", username='" + username + '\'';
    }
}
