package com.rmendes.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RedisDB {
	
	public long uid;
	
	public String name;
	
	public String type;
	
	@JsonProperty(value = "memory_size")
	public Long memorySize;

	
	
	

}
