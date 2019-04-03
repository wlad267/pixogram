package com.bluementors.bluemedia;

import com.bluementors.user.Media;
import com.bluementors.user.User;
import com.bluementors.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MediaService {

    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private UserService userService;

    public Media save(Media media){
        return mediaRepository.save(media);
    }

    public List<Media> fetchMediaByUser(Long userId){
        User user = userService.findById(userId);
        return user.getMedia();
    }

}
