package com.luma.api.payloads.addressPayLoad;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Setter
@Getter
@Accessors(fluent = true)
public class AddressCustomer {

	@JsonProperty("store_id")
	private int storeId;

	@JsonProperty("firstname")
	private String firstname;

	@JsonProperty("addresses")
	private List<AddressesItem> addresses;

	@JsonProperty("group_id")
	private int groupId;

	@JsonProperty("id")
	private int id;

	@JsonProperty("website_id")
	private int websiteId;

	@JsonProperty("email")
	private String email;

	@JsonProperty("lastname")
	private String lastname;

	@Override
 	public String toString(){
		return 
			"AddressCustomer{" +
			"store_id = '" + storeId + '\'' + 
			",firstname = '" + firstname + '\'' + 
			",addresses = '" + addresses + '\'' + 
			",group_id = '" + groupId + '\'' + 
			",id = '" + id + '\'' + 
			",website_id = '" + websiteId + '\'' + 
			",email = '" + email + '\'' + 
			",lastname = '" + lastname + '\'' + 
			"}";
		}
}
