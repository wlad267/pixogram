package com.bluementors.registration;

import com.bluementors.user.RegistrationService;
import com.bluementors.user.regitration.RegistrationForm;
import com.bluementors.user.regitration.RegistrationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@RestController
@RequestMapping(value = "api/registration",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
public class RegistrationResource {

    private Logger loggget = LoggerFactory.getLogger(RegistrationResource.class);

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity userRegistration(@RequestBody RegistrationForm registrationForm, HttpServletRequest httpRequest) {
        loggget.info("receiver registration request: " + registrationForm);

        //encode password before making further steps
        registrationForm.setPassword(passwordEncoder.encode(registrationForm.getPassword()));

        UUID registrationReqUuid = UUID.randomUUID();
        String confirmationURL = httpRequest.getRequestURL().toString() + "/confirmation/" + registrationReqUuid.toString();

        RegistrationRequest registrationRequest = registrationService.startRegistration(registrationForm,
                confirmationURL,
                registrationReqUuid);

        return ResponseEntity.ok(registrationRequest);
    }

    @GetMapping(value = "confirmation/{requestUuid}",
            produces = MediaType.ALL_VALUE,
            consumes = MediaType.ALL_VALUE)
    public ResponseEntity registrationConfirmation(@PathVariable("requestUuid") String uuid) {
        loggget.info("Received confirmation for " + uuid);
        registrationService.confirmRegistration(UUID.fromString(uuid));
        return ResponseEntity.ok("You are fine to visit BlumeMentors application now.");
    }


}
