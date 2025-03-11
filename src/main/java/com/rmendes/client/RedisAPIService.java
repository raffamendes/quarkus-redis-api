package com.rmendes.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import com.rmendes.model.RedisDB;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/v1")
@RegisterRestClient(configKey = "redis-cluster-api")
public interface RedisAPIService {
	
	
	@POST
	@Path("/bdbs")
	RedisDB createRedisDB(RedisDB db);

}
