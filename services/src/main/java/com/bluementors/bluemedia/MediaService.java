package com.bluementors.bluemedia;

import com.bluementors.exception.BusinessException;
import com.bluementors.user.Comment;
import com.bluementors.user.Media;
import com.bluementors.user.User;
import com.bluementors.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class MediaService {

    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private UserService userService;

    public Media save(Media media) {
        return mediaRepository.save(media);
    }

    public List<Media> fetchMediaByUser(Long userId) {
        User user = userService.findById(userId);
        return user.getMedia();
    }

    @Transactional
    public Media likeMedia(Long mediaId) {
        return this.changeLikesCount(mediaId, 1);
    }

    @Transactional
    public Media dislikeMedia(Long mediaId) {
        return this.changeLikesCount(mediaId, -1);
    }

    private Media changeLikesCount(Long mediaId, int direction) {
        Optional<Media> m = mediaRepository.findById(mediaId);
        if (!m.isPresent()) {
            throw new BusinessException("Media not found");
        }

        Media media = m.get();
        media.setLikesCount(media.getLikesCount() + direction);

        return media;
    }

    public Media findById(Long mediaId) {
        return mediaRepository.findById(mediaId)
                .orElseThrow(() -> new BusinessException("media " + mediaId + " not found"));
    }

    @Transactional
    public Media comment(Long mediaId, String comment) {
        Media media = mediaRepository.findById(mediaId).orElseThrow(() -> new BusinessException("media " + mediaId + " not found!"));
        Comment newComment = new Comment();
        newComment.setValue(comment);
        newComment.setMedia(media);
        media.addCommnet(newComment);
        return media;
    }
}
