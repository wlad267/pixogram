package com.bluementors.bluemedia;

import com.bluementors.user.Media;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MediaService {

    @Autowired
    private MediaRepository mediaRepository;

    public Media save(Media media){
        return mediaRepository.save(media);
    }

    public List<Media> fetchAllMedia(){
        return mediaRepository.findAll();
    }

//    public List<Media> fetchMedia(Long userId){
//        return mediaRepository.findByUserId(userId);
//
//    }
}
