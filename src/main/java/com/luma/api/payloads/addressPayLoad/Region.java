package com.luma.api.payloads.addressPayLoad;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(fluent = true)
public class Region{

	@JsonProperty("region_id")
	private int regionId;

	@JsonProperty("region")
	private String region;

	@JsonProperty("region_code")
	private String regionCode;

	@Override
 	public String toString(){
		return 
			"Region{" + 
			"region_id = '" + regionId + '\'' + 
			",region = '" + region + '\'' + 
			",region_code = '" + regionCode + '\'' + 
			"}";
		}
}