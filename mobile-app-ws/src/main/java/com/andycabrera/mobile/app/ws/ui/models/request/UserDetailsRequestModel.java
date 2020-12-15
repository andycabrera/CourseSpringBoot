package com.andycabrera.mobile.app.ws.ui.models.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
 
public class UserDetailsRequestModel {
    
    @NotNull(message = "First name cannot be null")
    private String firstName;

    @NotNull(message = "Last name cannot be null")
    private String lastName;
    
    @NotNull(message = "Email cannot be null")
    @Email
    private String email;
    
    @NotNull(message = "Password cannot be null")
    @Size(min = 8,max = 16, message = "Password must be equal or grate than 8 characters and less than 16 characters")
    private String password;

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
