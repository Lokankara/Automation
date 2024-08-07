package com.luma.api.payloads.updateUserPayLoad;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(fluent = true)
public class UpdateUserPayLoad {

    @JsonProperty("customer")
    private CustomerData customer;

    @Override
    public String toString() {
        return "" + customer;
    }

    public UpdateUserPayLoad addDataForUpdateUser(int userId,
                                                  String randEmail,
                                                  String firstName,
                                                  String lastName,
                                                  int websiteId,
                                                  int groupId) {
        return customer(
                new CustomerData()
                        .id(userId)
                        .email(randEmail)
                        .firstname(firstName)
                        .lastname(lastName)
                        .websiteId(websiteId)
                        .groupId(groupId));

    }
}