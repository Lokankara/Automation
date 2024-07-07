package com.luma.api.payloads.addressPayLoad;

import com.github.javafaker.Faker;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

import static java.util.Collections.singletonList;

@Setter
@Getter
@Accessors(fluent = true)
public class AddressPayLoad {

    private String region;
    private int regionId;
    private String regionCode;
    private String countryCode;
    private List<String> street;
    private String postCode;
    private String city;
    private String firstName;
    private String lastName;
    private String email;
    private String telephone;


    public AddressPayLoad create() {
        Faker faker = new Faker();
        this.region = faker.address().city();
        this.regionId = faker.number().randomDigitNotZero();
        this.regionCode = faker.address().stateAbbr();
        this.countryCode = "US";
        this.street = singletonList(faker.address().streetAddress());
        this.postCode = faker.address().zipCode();
        this.city = faker.address().city();
        this.firstName = faker.name().firstName();
        this.lastName = faker.name().lastName();
        this.email = faker.internet().emailAddress();
        this.telephone = faker.phoneNumber().cellPhone();
        return this;
    }
}
