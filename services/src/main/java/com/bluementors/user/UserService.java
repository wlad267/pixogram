package com.bluementors.user;

import com.bluementors.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
        return userRepository.findById(id).orElseThrow(() -> new BusinessException("User " + id+ " not found"));
    }

    public User save(User user){
        return userRepository.save(user);
    }

    @Transactional
    public void follow(String followerEmail, Long followedUserId) {
        User follower = userRepository.findByEmail(followerEmail);
        follower.follow(
                userRepository.findById(followedUserId)
                        .orElseThrow(()-> new BusinessException("User " + followedUserId +" not found")));
    }

    @Transactional
    public void unfollow(String followerEmail, Long followedUserId) {
        User follower = userRepository.findByEmail(followerEmail);
        follower.setFollow(
                follower.getFollow()
                        .stream()
                        .filter(u-> !u.getId().equals(followedUserId))
                        .collect(Collectors.toList())
        );
    }
}
