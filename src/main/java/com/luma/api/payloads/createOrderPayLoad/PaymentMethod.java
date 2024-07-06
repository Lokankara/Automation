package com.luma.api.payloads.createOrderPayLoad;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(fluent = true)
public class PaymentMethod{

	@JsonProperty("method")
	private String method;

	@Override
	public String toString() {
		return "method='" + method + '\'';
	}
}