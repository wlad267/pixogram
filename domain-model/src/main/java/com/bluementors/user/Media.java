package com.bluementors.user;
import com.bleumedia.MediaType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@SequenceGenerator(name = "media_seq", initialValue = 10, allocationSize = 1000000)
@Table(name = "MEDIA")
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "media_seq")
    private Long id;

    @Enumerated(EnumType.STRING)
    private MediaType mediaType;

    private String downloadUri;

    private int likesCount;

    private String fileName;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Comment> comments = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    public String getDownloadUri() {
        return downloadUri;
    }

    public void setDownloadUri(String downloadUri) {
        this.downloadUri = downloadUri;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public boolean isImage(){
        return MediaType.image.equals(this.mediaType);
    }

    public boolean isVideo(){
        return MediaType.video.equals(this.mediaType);
    }

    public void addCommnet(Comment comment){
        this.comments.add(comment);
        comment.setMedia(this);
    }

    @Override
    public String toString() {
        return "Media{" +
                "id=" + id +
                ", mediaType=" + mediaType +
                ", downloadUri='" + downloadUri + '\'' +
                ", comments=" + comments +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Media media = (Media) o;
        return Objects.equals(id, media.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static class Builder{
        private Media media;

        public Builder(){
            this.media = new Media();
        }

        public Builder type(MediaType mediaType){
            this.media.mediaType = mediaType;
            return this;
        }

        public Builder comment(Comment comment){
            this.media.comments.add(comment);
            return this;
        }

        public Builder downloadUri(String downloadUri){
            this.media.downloadUri = downloadUri;
            return this;
        }

        public Builder fileName(String fileName){
            this.media.fileName = fileName;
            return this;
        }

        public Media build(){
            return this.media;
        }
    }
}
