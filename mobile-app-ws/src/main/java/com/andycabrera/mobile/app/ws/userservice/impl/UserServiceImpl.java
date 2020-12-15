package com.andycabrera.mobile.app.ws.userservice.impl;
import com.andycabrera.mobile.app.ws.ui.userservice.UserService;

public class UserServiceImpl implements UserService {

    Map<String, UserRest> users;

    @Override
    public UserRest createUser(UseretailsRequestModel userDetails){
        UserRest returnValue = new UserRest();
        returnValue.setEmail(userDetails.getEmail());
        returnValue.setFirstName(userDetails.getFirstName());
        returnValue.setLastName(userDetails.getLastName());

        String userId = UUID.randomUUID().toString();
        returnValue.setUserId(userId);
        
        if(users == null) users = new HashMap<>();
        users.put(userId, returnValue);

        return returnValue;
    }
}
