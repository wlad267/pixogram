package com.bluementors.bluemedia;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

public class MediaUploadRequest implements Serializable {

    public String title;
    public String description;
    public MultipartFile file;

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

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
