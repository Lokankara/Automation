package com.luma.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.javafaker.Faker;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Locale;

@Setter
@Getter
@ToString
@EqualsAndHashCode
public class Address {

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("company_name")
    private String companyName;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("street_address")
    private String streetAddress;

    @JsonProperty("city")
    private String city;

    @JsonProperty("region")
    private String region;

    @JsonProperty("state_province")
    private String stateProvince;

    @JsonProperty("zip_postal_code")
    private String zipPostalCode;

    @JsonProperty("country")
    private String country;

    public Address() {
        createAddress(new Faker(Locale.ENGLISH));
    }

    private void createAddress(Faker faker) {
        this.setFirstName(faker.name().firstName());
        this.setLastName(faker.name().lastName());
        this.setCompanyName(faker.company().name());
        this.setPhoneNumber(faker.phoneNumber().cellPhone());
        this.setStreetAddress(faker.address().streetAddress());
        this.setCity(faker.address().city());
        this.setStateProvince(faker.address().stateAbbr());
        this.setZipPostalCode(faker.address().zipCode());
        this.setCountry(faker.address().country());
    }
}
