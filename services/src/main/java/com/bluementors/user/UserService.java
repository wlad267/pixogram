package com.bluementors.user;

import com.bluementors.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> fetchAll() {
      return userRepository.findAll();
    }

    public User register(User user) {
        return userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new BusinessException("User not found"));
    }

    public User save(User user){
        return userRepository.save(user);
    }
}
