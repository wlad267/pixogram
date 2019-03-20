package com.bluementors.bluemedia;

import com.bleumedia.MediaType;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

public class MediaUploadRequest implements Serializable {

    public String title;
    public String description;
    public MediaType type;
    public MultipartFile file;

    /**
     *
     * Crazy multipart mapper requires setters
     *
     */


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MediaType getType() {
        return type;
    }

    public void setType(MediaType type) {
        this.type = type;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
