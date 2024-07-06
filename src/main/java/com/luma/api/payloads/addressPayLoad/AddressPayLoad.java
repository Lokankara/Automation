package com.luma.api.payloads.addressPayLoad;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Setter
@Getter
@Accessors(fluent = true)
public class AddressPayLoad {

    @JsonProperty("addressCustomer")
    private AddressCustomer addressCustomer;

    @Override
    public String toString() {
        return
                "AddressPayLoad{" +
                        "addressCustomer = '" + addressCustomer + '\'' +
                        "}";
    }

    public AddressPayLoad setUserAndAddressData(int userId,
                                                int groupId,
                                                String email,
                                                String firstName,
                                                String lastName,
                                                int storeId,
                                                int websiteId,
                                                List<AddressesItem> addresses,
                                                int regionId,
                                                String region,
                                                String regionCode) {
        return addressCustomer(
                new AddressCustomer()
                        .storeId(storeId)
                        .firstname(firstName)
                        .addresses(addresses)
                        .groupId(groupId)
                        .id(userId)
                        .websiteId(websiteId)
                        .lastname(lastName)
        );
    }


    private List<AddressesItem> setAddresses(String firstname,
                                             String city,
                                             List<String> street,
                                             int regionId,
                                             String postcode,
                                             String telephone,
                                             int customerId,
                                             Region region,
                                             String countryId,
                                             String lastName,
                                             boolean defaultBilling,
                                             boolean defaultShipping) {


        return (List<AddressesItem>) new AddressesItem();
    }
}
