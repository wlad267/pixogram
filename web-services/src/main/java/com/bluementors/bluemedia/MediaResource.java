package com.bluementors.bluemedia;


import com.bluementors.bluemedia.filestorage.FileStorageService;
import com.bluementors.user.Media;
import com.bluementors.user.User;
import com.bluementors.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/media")
public class MediaResource {

    Logger logger = LoggerFactory.getLogger(MediaResource.class);

    @Autowired
    private MediaService mediaService;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private UserService userService;

    @PostMapping("upload")
    public MediaUploadFileResponse saveItem(@ModelAttribute MediaUploadRequest mediaUploadRequest) {
        logger.info("saving file " + mediaUploadRequest.file.getName());

        String fileName = fileStorageService.storeFile(mediaUploadRequest.file);

        User user = userService.findById(mediaUploadRequest.getUserId());

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("api/media/downloadFile/")
                .path(fileName)
                .toUriString();

        Media media = new Media.Builder()
                .type(mediaUploadRequest.type)
                .downloadUri(fileDownloadUri)
                .build();

        user.addMedia(media);

        userService.save(user);

        return new MediaUploadFileResponse(fileName, fileDownloadUri,
                mediaUploadRequest.file.getContentType(), mediaUploadRequest.file.getSize());
    }

    @GetMapping(value = "gallery/{userId}")
    public List<Media> fetchGalery(@PathParam("userId") Long userId) {
        return mediaService.fetchMediaByUser(userId);
    }

    @GetMapping("downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @GetMapping("{userId}")
    public List<Media> loadMedia(@PathVariable Long userId) {
        return userService.findById(userId).getMedia();
    }
}
