package com.luma.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.javafaker.Faker;

import java.util.List;
import java.util.Locale;

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

    public int getId() {
        return id;
    }

    public int getGroupId() {
        return groupId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getCreatedIn() {
        return createdIn;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public int getStoreId() {
        return storeId;
    }

    public int getWebsiteId() {
        return websiteId;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public int getDisableAutoGroupChange() {
        return disableAutoGroupChange;
    }

    public ExtensionAttributes getExtensionAttributes() {
        return extensionAttributes;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setCreatedIn(String createdIn) {
        this.createdIn = createdIn;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public void setWebsiteId(int websiteId) {
        this.websiteId = websiteId;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public void setDisableAutoGroupChange(int disableAutoGroupChange) {
        this.disableAutoGroupChange = disableAutoGroupChange;
    }

    public void setExtensionAttributes(ExtensionAttributes extensionAttributes) {
        this.extensionAttributes = extensionAttributes;
    }

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
