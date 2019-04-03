package com.bluementors;

import com.bluementors.data.UserData;
import com.bluementors.user.User;
import com.bluementors.user.UserService;
import org.hibernate.ObjectDeletedException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;


public class UserServiceIT extends IntegrationTest {

    Logger logger = LoggerFactory.getLogger(UserServiceIT.class);

    @Autowired
    private UserService userService;


    @Test(expected = ConstraintViolationException.class)
    public void test_add_invalid_user(){
        User user = UserData.invalidUser;

        User savedUuser = userService.register(user);

        entityManager.flush();

    }

    @Test
    public void test_add_valid_user(){
        User user = UserData.JohnMaxwell();

        User savedUuser = userService.register(user);

        entityManager.flush();
    }

    @Test
    public void test_find_user_by_email() {
        User user = UserData.JohnMaxwell();
        User savedUuser = userService.register(user);
        entityManager.flush();

        User fetchedUser = userService.findByEmail(user.getEmail());
        assertThat(fetchedUser).isNotNull();
        assertThat(fetchedUser).isEqualTo(user);
    }

    @Test(expected = ObjectDeletedException.class)
    public void test_entity_manager_merges() {
        User user = UserData.JohnMaxwell();
        User savedUuser = userService.register(user);
        entityManager.flush();

        boolean b = false;
        savedUuser = entityManager.merge(savedUuser);
        entityManager.remove(savedUuser);
        savedUuser = entityManager.merge(savedUuser);
        b = entityManager.contains(savedUuser);

        assertTrue(b);
    }
}
