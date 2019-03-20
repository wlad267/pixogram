package com.bluementors.user.registration;

import com.bluementors.user.regitration.RegistrationForm;
import com.bluementors.user.regitration.RegistrationRequest;

import java.util.UUID;

public class RegistrationData {

    public static RegistrationForm registrationForm(){
      RegistrationForm rf = new RegistrationForm();
      rf.setEmail("admin@bleumentors.com");
      rf.setFirstName("Dur");
      rf.setLastName("Paneus");
      rf.setPassword("the-Strongest#Passcode_Ever");
      return rf;
    }

    public static RegistrationRequest registrationRequest(){
        RegistrationRequest registrationRequest = new RegistrationRequest(UUID.randomUUID());
        registrationRequest.setRegistrationForm(registrationForm());
        return registrationRequest;
    }
}
