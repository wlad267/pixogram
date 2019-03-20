package com.bleumedia;
import com.bluementors.user.User;

import javax.persistence.*;

@Entity
@SequenceGenerator(name = "media_seq", initialValue = 10, allocationSize = 1000000)
@Table(name = "MEDIA")
public class Media2 {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "media_seq")
    private Long id;

    @Lob
    private byte[] data;

    @Enumerated(EnumType.STRING)
    private MediaType mediaType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }
}
