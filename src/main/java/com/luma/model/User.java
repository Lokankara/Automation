package com.luma.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.javafaker.Faker;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Locale;

@Setter
@Getter
@ToString
@EqualsAndHashCode
public class User {

    public User() {
        createUser(new Faker(Locale.ENGLISH));
    }
    private int id;
    @JsonProperty("group_id")
    private int groupId;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("created_in")
    private String createdIn;
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    @JsonProperty("store_id")
    private int storeId;
    @JsonProperty("website_id")
    private int websiteId;
    private List<Address> addresses;
    @JsonProperty("disable_auto_group_change")
    private int disableAutoGroupChange;
    @JsonProperty("extension_attributes")
    private ExtensionAttributes extensionAttributes;

    public void createUser(Faker faker) {
        this.setPassword(faker.internet().emailAddress() + faker.number().randomDigit());
        this.setId(faker.number().randomDigit());
        this.setGroupId(faker.number().randomDigit());
        this.setCreatedAt(faker.date().toString());
        this.setUpdatedAt(faker.date().toString());
        this.setCreatedIn(faker.address().city());
        this.setEmail(faker.internet().emailAddress());
        this.setFirstname(faker.name().firstName());
        this.setLastname(faker.name().lastName());
        this.setStoreId(faker.number().randomDigit());
        this.setWebsiteId(faker.number().randomDigit());
        this.setDisableAutoGroupChange(faker.number().randomDigit());
    }
}
