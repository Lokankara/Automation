package com.luma.api.payloads.userPayLoad;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(fluent = true)
public class Customer {

    @JsonProperty("password")
    private String password;

    @JsonProperty("customer")
    private UserPayLoad customer;

    @Override
    public String toString() {
        return "password='" + password + '\'' +
                "," + customer;
    }

    public Customer setNewCustomerData(String email,
                                       String fistrName,
                                       String lastName,
                                       int websiteId,
                                       int groupId,
                                       String password) {
        return customer(
                new UserPayLoad()
                        .email(email)
                        .firstname(fistrName)
                        .lastname(lastName)
                        .websiteId(websiteId)
                        .groupId(groupId))
                .password(password);


    }
}