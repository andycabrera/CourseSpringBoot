package com.andycabrera.mobile.app.ws.ui.controller;

import com.andycabrera.mobile.app.ws.ui.models.request.UserDetailsRequestModel;
import com.andycabrera.mobile.app.ws.ui.models.response.UserRest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController{
    
    @GetMapping
    public String getUsers(@RequestParam(value="page", defaultValue="1") int page,
        @RequestParam(value="limit", defaultValue="50") int limit,
        @RequestParam(value="sort", defaultValue="desc", required=false) String sort){
        return "get user was colled with " + page + " and limit = " + limit;
    }
    
    @GetMapping(path="/{userId}",
        produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
            })
    public ResponseEntity<UserRest> getUser(@PathVariable String userId){

        UserRest returnValue = new UserRest();
        returnValue.setFirstName("Andy");
        returnValue.setLastName("Cabrera");
        returnValue.setEmail("ancabrera@arcor.com");
    
        return new ResponseEntity<UserRest>(returnValue, HttpStatus.OK);
        // return new ResponseEntity<UserRest>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping(consumes = {
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.APPLICATION_XML_VALUE
        },
        produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
            })
    public ResponseEntity<UserRest> createUser(@RequestBody UserDetailsRequestModel userDetails){
        UserRest returnValue = new UserRest();
        returnValue.setEmail(userDetails.getEmail());
        returnValue.setFirstName(userDetails.getFirstName());
        returnValue.setLastName(userDetails.getLastName());
    
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