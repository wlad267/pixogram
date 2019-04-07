package com.bluementors.user;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "USERS")
@Inheritance(strategy = InheritanceType.JOINED)
@SequenceGenerator(name = "user_seq", initialValue = 10, allocationSize = 1000000)
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    private Long id;
    @Email
    private String email;
    @NotNull
    private String authenticationString;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    private String contactNo;
    @OneToMany(cascade = {CascadeType.ALL})
    private List<Media> media = new ArrayList<>();
    @JsonIgnore
    @ManyToMany
    private Set<User> follow = new HashSet<>();
    @ManyToMany
    @JsonIgnore
    private Set<User> followers = new HashSet<>();

    private String profilePhoto;

    private LocalDateTime registrationDate;
    private String registrationCode;

    private Boolean active = Boolean.TRUE;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAuthenticationString() {
        return authenticationString;
    }

    public void setAuthenticationString(String authenticationString) {
        this.authenticationString = authenticationString;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getRegistrationCode() {
        return registrationCode;
    }

    public void setRegistrationCode(String registrationCode) {
        this.registrationCode = registrationCode;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<Media> getMedia() {
        return media;
    }

    public void addMedia(Media media) {
        this.media.add(media);
    }

    public void follow(User user){
        this.follow.add(user);
        user.getFollowers().add(this);
    }

    public void setMedia(List<Media> media) {
        this.media = media;
    }

    public Set<User> getFollow() {
        return follow;
    }

    public void setFollow(Set<User> follow) {
        this.follow = follow;
    }

    public Set<User> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<User> followers) {
        this.followers = followers;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", authenticationString='" + authenticationString + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", contactNo='" + contactNo + '\'' +
                ", registrationDate=" + registrationDate +
                ", registrationCode='" + registrationCode + '\'' +
                ", active=" + active +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static class Builder {
        private User user;

        public Builder() {
            this.user = new User();
        }

        public Builder firstName(String firstName) {
            this.user.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.user.lastName = lastName;
            return this;
        }

        public Builder email(String email) {
            this.user.email = email;
            return this;
        }

        public Builder authenticationString(String authenticationString) {
            this.user.authenticationString = authenticationString;
            return this;
        }


        public Builder contactNo(String contactNo) {
            this.user.contactNo = contactNo;
            return this;
        }

        public Builder registrationCode(String registrationCode) {
            this.user.registrationCode = registrationCode;
            return this;
        }

        public Builder active(Boolean active) {
            this.user.active = active;
            return this;
        }

        public Builder registrationDate(LocalDateTime registrationDate) {
            this.user.registrationDate = registrationDate;
            return this;
        }

        public User build() {
            return this.user;
        }
    }

}
