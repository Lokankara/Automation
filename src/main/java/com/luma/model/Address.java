package com.luma.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.javafaker.Faker;

import java.util.Locale;
import java.util.Objects;

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

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getCity() {
        return city;
    }

    public String getStateProvince() {
        return stateProvince;
    }

    public String getZipPostalCode() {
        return zipPostalCode;
    }

    public String getCountry() {
        return country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }

    public void setZipPostalCode(String zipPostalCode) {
        this.zipPostalCode = zipPostalCode;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(firstName, address.firstName)
                && Objects.equals(lastName, address.lastName)
                && Objects.equals(companyName, address.companyName)
                && Objects.equals(phoneNumber, address.phoneNumber)
                && Objects.equals(streetAddress, address.streetAddress)
                && Objects.equals(city, address.city)
                && Objects.equals(region, address.region)
                && Objects.equals(stateProvince, address.stateProvince)
                && Objects.equals(zipPostalCode, address.zipPostalCode)
                && Objects.equals(country, address.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                firstName,
                lastName,
                companyName,
                phoneNumber,
                streetAddress,
                city,
                region,
                stateProvince,
                zipPostalCode,
                country);
    }
}
