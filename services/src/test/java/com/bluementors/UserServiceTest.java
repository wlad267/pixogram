package com.bluementors;

import com.bluementors.data.UserData;
import com.bluementors.user.User;
import com.bluementors.user.UserRepository;
import com.bluementors.user.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Test
    public void register_user_invalid_user(){
        User invalidUser = UserData.invalidUser;
        userService.register(invalidUser);
    }
}
