package com.rmendes;

import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.rmendes.client.RedisAPIService;
import com.rmendes.model.RedisDB;
import com.rmendes.model.RedisUser;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import org.jboss.logging.Logger;

@QuarkusMain
public class RedisAPIInteractor implements QuarkusApplication{
	
	private static final Logger	LOG = Logger.getLogger(RedisAPIInteractor.class);

	@RestClient
	RedisAPIService restClient;

	public static void main(String[] args) {
		Quarkus.run(RedisAPIInteractor.class, args);
	}
	
	

    @Override
	public int run(String... args) throws Exception {
		if(args.length == 0){
			LOG.error("The database name cannot be blank");
			return 1;
		}
		String dbName = args[0];
		if(dbName.isBlank()){
			LOG.error("Please provide a valid name for the database");
			return 1;
		} 
		LOG.info("Now Creating Redis DB with name "+dbName);
    	RedisDB createdDB = restClient.createRedisDB(createDB(dbName));
		LOG.info("Database succesfully created with uid: "+createdDB.uid); 

		LOG.info("Creating User John Doe with email john.doe@example.com and Role db_viewer");
		restClient.createRedisUser(createRedisUser("John Doe", "john.doe@example.com", new Integer[]{2}));
		
		LOG.info("Creating User Mike Smith with email mike.smith@example.com and Role db_member");
		restClient.createRedisUser(createRedisUser("Mike Smith", "mike.smith@example.com", new Integer[]{3}));
		
		LOG.info("Creating User Cary Johnson with email cary.johnson@example.com and Role admin");
		restClient.createRedisUser(createRedisUser("Cary Johnson", "cary.johnson@example.com", new Integer[]{1}));
		
		LOG.info("Listing all Users:");
		List<RedisUser> userList = restClient.getAllUsers();
		userList.forEach(user -> LOG.info("Name: "+user.name+" Email: "+user.email+ "Role: "+user.role));

		LOG.info("Deleting Database with uid: "+createdDB.uid);
		restClient.deleteRedisDB(createdDB.uid);
		return 0;
	}
    
    private RedisDB createDB(String dbName) {
    	RedisDB db = new RedisDB();
    	db.name=dbName;
    	db.memorySize = 1073741824l;
		db.type = "redis";
		db.uid = null;
    	return db;
    }

	private RedisUser createRedisUser(String name, String email, Integer[] roleUids){
		RedisUser user = new RedisUser();
		user.name = name;
		user.email = email;
		user.roleUids = roleUids;
		user.emailAlerts = true;
		user.bdbsEmailAlerts = new String[]{"1","2"}; 
		user.password = "redispass";  
		return user;
	}
}
