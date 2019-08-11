package org.simplyatul.cassandrademo.service.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ApiResponse {

	private String guid;
	private String errorMsg;
	
	public ApiResponse(@JsonProperty("guid") String guid,
	        @JsonProperty("errorMsg") String errorMsg) {
	    this.guid = guid;
		this.errorMsg = errorMsg;
	}
}
