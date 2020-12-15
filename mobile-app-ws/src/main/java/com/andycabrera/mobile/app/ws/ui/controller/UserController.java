package com.andycabrera.mobile.app.ws.ui.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import com.andycabrera.mobile.app.ws.ui.models.request.UserDetailsRequestModel;
import com.andycabrera.mobile.app.ws.ui.models.response.UserRest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController{

    Map<String, UserRest> users;
    
    @GetMapping
    public Map<String, UserRest> getUsers(@RequestParam(value="page", defaultValue="1") int page,
        @RequestParam(value="limit", defaultValue="50") int limit,
        @RequestParam(value="sort", defaultValue="desc", required=false) String sort){
        return users;
    }
    
    @GetMapping(path="/{userId}",
        produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
            })
    public ResponseEntity<UserRest> getUser(@PathVariable String userId){

        if(users.containsKey(userId)){
            return new ResponseEntity<UserRest>(users.get(userId), HttpStatus.OK); 
        }else{
            return new ResponseEntity<UserRest>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping(
        consumes = {
        MediaType.APPLICATION_XML_VALUE,
        MediaType.APPLICATION_JSON_VALUE
        },
        produces = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
            })
    public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequestModel userDetails){
        UserRest returnValue = new UserRest();
        returnValue.setEmail(userDetails.getEmail());
        returnValue.setFirstName(userDetails.getFirstName());
        returnValue.setLastName(userDetails.getLastName());

        String userId = UUID.randomUUID().toString();
        returnValue.setUserId(userId);
        
        if(users == null) users = new HashMap<>();
        users.put(userId, returnValue);
    
        return new ResponseEntity<UserRest>(returnValue,HttpStatus.OK);
    }

    @PutMapping
    public String updateUser(){
        return "update user was colled!";
    }

    @DeleteMapping
    public String deleteUser(){
        return "delete user was colled!";
    }
}