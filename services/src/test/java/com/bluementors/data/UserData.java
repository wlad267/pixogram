package com.bluementors.data;

import com.bluementors.user.User;

public class UserData {

    public static User invalidUser = new User();

    public static User JohnMaxwell() {
        return new User.Builder()
                .authenticationString("myPassword")
                .email("john@bluementors.org")
                .firstName("lulu")
                .lastName("maxwell")
                .build();

    }

    public static User RobinWilliams() {
        return new User.Builder()
                .authenticationString("2thPassword")
                .email("robin@bluementors.org")
                .firstName("robin")
                .lastName("williams")
                .build();
    }
}
