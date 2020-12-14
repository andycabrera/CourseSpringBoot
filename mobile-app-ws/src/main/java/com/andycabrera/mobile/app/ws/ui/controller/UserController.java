package com.andycabrera.mobile.app.ws.iu.controller;

@RestController
@RequestMaping("users")
public class UserController{
    
    @GetMapping
    public String getUsers(@RequestParam(value="page", defaultValue="1") int page,
        @RequestParam(value="limit", defaultValue="50") int limit,
        @RequestParam(value="sort", defaultValue="desc", require=false) String sort){
        return "get user was colled with " + page + " and limit = " + limit;
    }
    
    @GetMapping(path="/{userId}",
        produces = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
            })
    public UserRest getUser(@PathVariable String userId){

        UserRest returnValue = new UserRest();
        returnValue.setFirstName("Andy");
        returnValue.setLastName("Cabrera");
        returnValue.setEmail("ancabrera@arcor.com");
    
        return returnValue;
    }

    @PostMapping
    public String createUser(){
        return "create user was colled!";
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