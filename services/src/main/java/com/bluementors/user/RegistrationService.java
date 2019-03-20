package com.bluementors.user;

import com.bluementors.exception.BusinessException;
import com.bluementors.user.regitration.RegistrationForm;
import com.bluementors.user.regitration.RegistrationRequest;
import com.bluementors.user.regitration.RegistrationRequestRepository;
import com.bluementors.user.regitration.RegistrationStatus;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Transactional
public class RegistrationService {
    private Logger logger = LoggerFactory.getLogger(RegistrationService.class);

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private RegistrationRequestRepository registrationRequestRepository;

    @Autowired
    private UserService userService;

    public RegistrationRequest startRegistration(RegistrationForm registrationForm,
                                  String registrationConfirmationUrl,
                                  UUID registrationUuid) {
        logger.info("sending registration to " + registrationForm.getEmail() + " registration confirmation URL:" + registrationConfirmationUrl);

        //TODO: check the email is already used

        this.sendSimpleMessage(registrationForm.getEmail(),
                "BlueMentors registration confirmation",
                "Dear " + registrationForm.getFirstName() + " " + registrationForm.getLastName() + "," +
                        "T\nThank you for your interest." +
                        ",\nPlease confirm you registration to BlueMentors:\n" +
                        registrationConfirmationUrl +
                        "\n\nRegards." +
                        "\n\nBlueMentors.");

        RegistrationRequest registrationRequest = new RegistrationRequest(registrationUuid);
        registrationRequest.setRegistrationForm(registrationForm);
        return registrationRequestRepository.save(registrationRequest);

    }


    public RegistrationRequest confirmRegistration(UUID uuid) {
        RegistrationRequest registrationRequest = registrationRequestRepository
                .findById(uuid)
                .orElseThrow(() -> new BusinessException("no Such registration request"));

        userService.register(new User.Builder().firstName(registrationRequest.getRegistrationForm().getFirstName())
                .lastName(registrationRequest.getRegistrationForm().getLastName())
                .email(registrationRequest.getRegistrationForm().getEmail())
                .authenticationString(registrationRequest.getRegistrationForm().getPassword())
                .build()
        );

        registrationRequest.setStatus(RegistrationStatus.CONFIRMED);
        return registrationRequest;
    }

    private void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }
}
