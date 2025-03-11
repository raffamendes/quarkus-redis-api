package com.rmendes;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.rmendes.client.RedisAPIService;
import com.rmendes.model.RedisDB;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class RedisAPIInteractor implements QuarkusApplication{
	
	@RestClient
	RedisAPIService restClient;
	
	

    @Override
	public int run(String... args) throws Exception {
    	restClient.createRedisDB(createDB());
		return 0;
	}
    
    private RedisDB createDB() {
    	RedisDB db = new RedisDB();
    	db.name="test";
    	db.memorySize = 1073741824l;
    	return db;
    }
}
