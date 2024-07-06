package com.luma.api.payloads.userPayLoad;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
@Getter
@Setter
@Accessors(fluent = true)
public class UserPayLoad {


    @JsonProperty("firstname")
    private String firstname;

    @JsonProperty("group_id")
    private int groupId;

    @JsonProperty("website_id")
    private int websiteId;

    @JsonProperty("email")
    private String email;

    @JsonProperty("lastname")
    private String lastname;

    @Override
    public String toString() {
        return
                "firstname='" + firstname + '\'' +
                ", groupId=" + groupId +
                ", websiteId=" + websiteId +
                ", email='" + email + '\'' +
                ", lastname='" + lastname + '\'';
    }

}
