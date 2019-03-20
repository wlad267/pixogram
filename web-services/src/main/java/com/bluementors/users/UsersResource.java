package com.bluementors.users;

import com.bluementors.user.User;
import com.bluementors.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/users")

public class UsersResource {

    @Autowired
    private UserService userService;

    @GetMapping(path = "all")
    public List<User> listAll() {
        return userService.fetchAll();
    }

}
