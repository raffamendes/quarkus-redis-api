package com.rmendes.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class RedisDB {
	
	public Long uid;
	
	public String name;
	
	public String type;
	
	@JsonProperty(value = "memory_size")
	public Long memorySize;

	
	
	

}
