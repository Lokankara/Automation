package com.luma.api.payloads.prepareCheckoutPayLoad;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.luma.api.payloads.addressPayLoad.AddressPayLoad;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(fluent = true)
public class PrepareCheckoutPayLoad {

    @JsonProperty("addressInformation")
    private AddressInformation addressInformation;

    @Override
    public String toString() {
        return "" + addressInformation;
    }

    public PrepareCheckoutPayLoad setCheckoutData(AddressPayLoad addressPayLoad,
                                                  String shippingMethod,
                                                  String shippingCarrierCode) {
        return addressInformation(new AddressInformation().shippingAddress(
                        new ShippingAddress()
                                .region(addressPayLoad.region())
                                .regionId(addressPayLoad.regionId())
                                .regionCode(addressPayLoad.regionCode())
                                .countryId(addressPayLoad.countryCode())
                                .street(addressPayLoad.street())
                                .postcode(addressPayLoad.postCode())
                                .city(addressPayLoad.city())
                                .firstname(addressPayLoad.firstName())
                                .lastname(addressPayLoad.lastName())
                                .email(addressPayLoad.email())
                                .telephone(addressPayLoad.telephone()))
                .shippingCarrierCode(shippingMethod)
                .shippingMethodCode(shippingCarrierCode));
    }
}