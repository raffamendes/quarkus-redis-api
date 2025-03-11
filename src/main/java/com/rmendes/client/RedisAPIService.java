package com.rmendes.client;

import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rmendes.model.RedisDB;
import com.rmendes.model.RedisUser;

import io.quarkus.logging.Log;
import io.quarkus.rest.client.reactive.ClientBasicAuth;
import io.quarkus.rest.client.reactive.ClientExceptionMapper;
import io.vertx.core.json.JsonObject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

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

	@ClientExceptionMapper
	static RuntimeException toException(Response response) throws JsonMappingException, JsonProcessingException{
		if(response.getStatus() == 400){
			JsonNode jsonNode = new ObjectMapper().readTree(response.readEntity(String.class));
			return new RuntimeException("Erro ao Executar ação: "+jsonNode.get("description").asText()); 
		} else if(response.getStatus() == 409){
			JsonNode jsonNode = new ObjectMapper().readTree(response.readEntity(String.class));
			return new RuntimeException("Erro ao Executar ação: "+jsonNode.get("description").asText()); 
		}
		return null;
	}

	

}
