package com.luma.api.payloads.createOrderPayLoad;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.luma.api.payloads.addressPayLoad.AddressPayLoad;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(fluent = true)
public class CreateOrderPayLoad {

    @JsonProperty("paymentMethod")
    private PaymentMethod paymentMethod;

    @JsonProperty("billing_address")
    private BillingAddress billingAddress;

    @Override
    public String toString() {
        return paymentMethod + ", " + billingAddress;
    }

    public CreateOrderPayLoad setPaymentData(String checkMoneyOrder,
                                             AddressPayLoad addressPayLoad) {
        return paymentMethod(
                new PaymentMethod()
                        .method(checkMoneyOrder))
                .billingAddress(
                        new BillingAddress()
                                .email(addressPayLoad.email())
                                .region(addressPayLoad.region())
                                .regionId(addressPayLoad.regionId())
                                .regionCode(addressPayLoad.regionCode())
                                .countryId(addressPayLoad.countryCode())
                                .street(addressPayLoad.street())
                                .postcode(addressPayLoad.postCode())
                                .city(addressPayLoad.city())
                                .telephone(addressPayLoad.telephone())
                                .firstname(addressPayLoad.firstName())
                                .lastname(addressPayLoad.lastName()));
    }
}