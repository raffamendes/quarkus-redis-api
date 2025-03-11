package com.rmendes.client;

import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;


import com.rmendes.model.RedisDB;
import com.rmendes.model.RedisUser;

import io.quarkus.rest.client.reactive.ClientBasicAuth;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/v1")
@RegisterRestClient(configKey = "redis-cluster-api")
@ClientBasicAuth(username = "${username}", password = "${password}")
public interface RedisAPIService {
	
	
	@POST
	@Path("/bdbs")
	RedisDB createRedisDB(RedisDB db);

	@DELETE
	@Path("/bdbs/{uid}")
	void deleteRedisDB(@PathParam("uid") Long uid);

	@POST
	@Path("/users")
	RedisUser createRedisUser(RedisUser user);

	@GET
	@Path("/users")
	List<RedisUser> getAllUsers();

	

}
