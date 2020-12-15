package com.andycabrera.mobile.app.ws.ui.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import com.andycabrera.mobile.app.ws.ui.models.request.UpdateUserDetailsRequestModel;
import com.andycabrera.mobile.app.ws.ui.models.request.UserDetailsRequestModel;
import com.andycabrera.mobile.app.ws.ui.models.response.UserRest;
import com.andycabrera.mobile.app.ws.exceptions.*;

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

        if(true) throw new UserServiceException("A user service exception is thrown");

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
        
        UserRest returnValue = new UserServiceImp().createUser(userDetails);
        return new ResponseEntity<UserRest>(returnValue,HttpStatus.OK);
    }

    @PutMapping(path="/{userId}",
        consumes = {
        MediaType.APPLICATION_XML_VALUE,
        MediaType.APPLICATION_JSON_VALUE
        },
        produces = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
            })
    public UserRest updateUser(@PathVariable("userId") String userId, @Valid @RequestBody UpdateUserDetailsRequestModel userDetails){
        
        UserRest storedUserDetails = users.get(userId);
        storedUserDetails.setFirstName(userDetails.getFirstName());
        storedUserDetails.setLastName(userDetails.getLastName());

        users.put(userId, storedUserDetails);
        return storedUserDetails;
        
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") String id){
        users.remove(id);

        return ResponseEntity.noContent().build();
    }
}