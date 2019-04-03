package com.bluementors.user;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    @OneToMany
    private List<User> follow = new ArrayList<>();
    @OneToMany
    private List<User> followers = new ArrayList<>();

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

    public void setMedia(List<Media> media) {
        this.media = media;
    }

    public List<User> getFollow() {
        return follow;
    }

    public void setFollow(List<User> follow) {
        this.follow = follow;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public void setFollowers(List<User> followers) {
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
        return Objects.equals(id, user.id) &&
                Objects.equals(email, user.email) &&
                Objects.equals(authenticationString, user.authenticationString) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(contactNo, user.contactNo) &&
                Objects.equals(media, user.media) &&
                Objects.equals(follow, user.follow) &&
                Objects.equals(followers, user.followers) &&
                Objects.equals(profilePhoto, user.profilePhoto) &&
                Objects.equals(registrationDate, user.registrationDate) &&
                Objects.equals(registrationCode, user.registrationCode) &&
                Objects.equals(active, user.active);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, authenticationString, firstName, lastName, contactNo, media, follow, followers, profilePhoto, registrationDate, registrationCode, active);
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
