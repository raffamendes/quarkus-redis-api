package com.rmendes.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class RedisUser {

    /*{
     "email": "newuser@example.com",
     "password": "my-password",
     "name": "Pat Doe",
     "email_alerts": true,
     "bdbs_email_alerts": ["1","2"],
     "role_uids": [ 3, 4 ],
     "auth_method": "regular"
} */

    public String email;

    public String password;

    public String name;

    @JsonProperty("email_alerts")
    public Boolean emailAlerts;

    @JsonProperty("bdbs_email_alerts")
    public String[] bdbsEmailAlerts;

    @JsonProperty("role_uids")
    public Integer[] roleUids;

    public String authMethod;

    public String role;

}