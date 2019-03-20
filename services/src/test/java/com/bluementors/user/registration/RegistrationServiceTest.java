package com.bluementors.user.registration;

import com.bluementors.exception.BusinessException;
import com.bluementors.user.RegistrationService;
import com.bluementors.user.User;
import com.bluementors.user.UserService;
import com.bluementors.user.regitration.RegistrationRequest;
import com.bluementors.user.regitration.RegistrationRequestRepository;
import com.bluementors.user.regitration.RegistrationStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Optional;
import java.util.UUID;

import static com.bluementors.user.registration.RegistrationData.registrationForm;
import static com.bluementors.user.registration.RegistrationData.registrationRequest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationServiceTest {

    @Mock
    private JavaMailSender emailSender;

    @Mock
    private RegistrationRequestRepository registrationRequestRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private RegistrationService sut;

    @Test
    public void start_registration_new_user() {
        ArgumentCaptor<RegistrationRequest> argumentCaptor = ArgumentCaptor.forClass(RegistrationRequest.class);
        sut.startRegistration(RegistrationData.registrationForm(), "/home", UUID.randomUUID());
        verify(emailSender, times(1)).send(any(SimpleMailMessage.class));
        verify(registrationRequestRepository).save(argumentCaptor.capture());
        RegistrationRequest registrationRequest = argumentCaptor.getValue();
        assertThat(registrationRequest).isNotNull();

    }

    @Test
    public void start_registration_already_existing_user() {
        sut.startRegistration(RegistrationData.registrationForm(), "/home", UUID.randomUUID());
        verify(emailSender, times(1)).send(any(SimpleMailMessage.class));
    }


    @Test
    public void confirm_registration_valid_request_check_user() {
        ArgumentCaptor<User> userArgCaptor = ArgumentCaptor.forClass(User.class);
        when(registrationRequestRepository.findById(any(UUID.class))).thenReturn(Optional.of(registrationRequest()));
        sut.confirmRegistration(UUID.randomUUID());
        verify(userService, times(1)).register(userArgCaptor.capture());
        User user = userArgCaptor.getValue();
        assertThat(user).isNotNull();
        assertThat(user.getEmail()).isEqualTo(registrationForm().getEmail());
    }

    @Test
    public void confirm_registration_valid_request_check_status() {
        RegistrationRequest registrationRequest = registrationRequest();
        when(registrationRequestRepository.findById(any(UUID.class))).thenReturn(Optional.of(registrationRequest));
        sut.confirmRegistration(UUID.randomUUID());
        assertThat(registrationRequest.getStatus()).isEqualTo(RegistrationStatus.CONFIRMED);
    }

    @Test(expected = BusinessException.class)
    public void confirm_registration_invalid_request() {
        sut.confirmRegistration(UUID.randomUUID());
        verify(userService, times(0)).register(any());
    }
}
