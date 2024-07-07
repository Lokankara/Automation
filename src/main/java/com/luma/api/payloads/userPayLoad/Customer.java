package com.luma.api.payloads.userPayLoad;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.javafaker.Faker;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import static com.luma.data.TestData.PASSWORD;

@Getter
@Setter
@Accessors(fluent = true)
public class Customer {

    @JsonProperty("password")
    private String password;

    @JsonProperty("customer")
    private UserPayLoad customer;

    private final Faker faker = new Faker();

    @Override
    public String toString() {
        return "password='" + password + '\'' + ", " + customer;
    }

    public Customer create(String email, String firstName,
                           String lastName,
                           int websiteId,
                           int groupId,
                           String password) {
        return customer(
                new UserPayLoad()
                        .email(email)
                        .firstname(firstName)
                        .lastname(lastName)
                        .websiteId(websiteId)
                        .groupId(groupId))
                .password(password);
    }


    public Customer create(String firstName,
                           String lastName,
                           int websiteId,
                           int groupId,
                           String password) {
        return create(faker.internet().emailAddress(), firstName, lastName, websiteId, groupId, password);
    }


    public Customer create() {
        UserPayLoad userPayLoad = new UserPayLoad()
                .email(faker.internet().emailAddress())
                .firstname(faker.name().firstName())
                .lastname(faker.name().lastName())
                .websiteId(faker.number().randomDigit())
                .groupId(faker.number().randomDigit());
        return customer(userPayLoad).password(PASSWORD);
    }

}