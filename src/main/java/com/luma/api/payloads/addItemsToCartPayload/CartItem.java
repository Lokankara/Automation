package com.luma.api.payloads.addItemsToCartPayload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(fluent = true)
public class CartItem{

	@JsonProperty("qty")
	private int qty;

	@JsonProperty("quote_id")
	private String quoteId;

	@JsonProperty("sku")
	private String sku;


	@Override
	public String toString() {
		return 	"qty=" + qty +
				", quoteId='" + quoteId + '\'' +
				", sku='" + sku + '\'';
	}
}