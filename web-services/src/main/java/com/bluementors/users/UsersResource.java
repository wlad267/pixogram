package com.bluementors.users;

import com.bluementors.bluemedia.filestorage.FileStorageService;
import com.bluementors.user.Media;
import com.bluementors.user.User;
import com.bluementors.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/users")

public class UsersResource {

    @Autowired
    private UserService userService;

    @Autowired
    private FileStorageService fileStorageService;

    @GetMapping(path = "all")
    public List<UserSummaryRepresentation> listAll() {
        List<User> users =  userService.fetchAll();

        List<UserSummaryRepresentation> userSummaryRepresentations = users.stream()
                .map(u->new UserSummaryRepresentation.Builder()
                        .userId(u.getId())
                        .profileImageUri(u.getProfilePhoto())
                        .firstName(u.getFirstName())
                        .lastName(u.getLastName())
                        .noOfComments(countMedia(u, Media::getComments, (whatever)-> true))
                        .noOfLikes(countMedia(u, Media::getLikesCount, (whatever)-> true))
                        .noOfFollowers(u.getFollowers().size())
                        .noOfImages(countMedia(u, Media::getComments, (media)->media.isImage()))
                        .noOfVideos(countMedia(u, Media::getComments, (media)->media.isVideo() ))
                        .build())
                .collect(Collectors.toList());

        return userSummaryRepresentations;
    }

    private int countMedia(User user, Function<Media,Object> mappingFunction, Predicate<Media> filter){
        return (int) user.getMedia().stream().filter(filter).map(mappingFunction).count();
    }



    @PostMapping("update")
    @Transactional
    //TODO cheeck this out -- applicative transation rollback
    public User updateUser(@ModelAttribute UserUpdateRepresentation userUpdateRepresentation){
           User user = userService.findById(userUpdateRepresentation.userId);
           setProfileImage(user, userUpdateRepresentation.file);
           if (userUpdateRepresentation.firstName != null) {
               user.setFirstName(userUpdateRepresentation.firstName);
           }
           if(userUpdateRepresentation.lastName!=null){
               user.setLastName(userUpdateRepresentation.lastName);
           }
        return userService.save(user);
    }

    private void setProfileImage(User user, MultipartFile profileImage) {
        if (profileImage == null) return;
        String fileName = fileStorageService.storeFile(profileImage);
        user.setProfilePhoto("api/media/downloadFile/".concat(fileName));
    }


    @PostMapping("follow/{followedUserId}")
    public ResponseEntity follow(@PathVariable("followedUserId") Long followedUserId, Principal principal){
        this.userService.follow(principal.getName(), followedUserId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("unfollow/{followedUserId}")
    public ResponseEntity unfollow(@PathVariable("followedUserId") Long followedUserId, Principal principal){
        this.userService.unfollow(principal.getName(), followedUserId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("follow")
    public List<User> getFollowers(Principal user){
        return new ArrayList(userService.findByEmail(user.getName()).getFollowers());
    }

    @GetMapping("following")
    public List<User> getFollowing(Principal user){
        return new ArrayList(userService.findByEmail(user.getName()).getFollow());
    }

}
