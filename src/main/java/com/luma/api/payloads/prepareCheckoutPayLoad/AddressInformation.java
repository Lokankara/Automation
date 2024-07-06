package com.luma.api.payloads.prepareCheckoutPayLoad;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(fluent = true)
public class AddressInformation{

	@JsonProperty("shipping_method_code")
	private String shippingMethodCode;

	@JsonProperty("shipping_carrier_code")
	private String shippingCarrierCode;

	@JsonProperty("shipping_address")
	private ShippingAddress shippingAddress;

	@Override
	public String toString() {
		return  "shippingMethodCode='" + shippingMethodCode + '\'' +
				", shippingCarrierCode='" + shippingCarrierCode + '\'' +
				", shippingAddress=" + shippingAddress;
	}
}