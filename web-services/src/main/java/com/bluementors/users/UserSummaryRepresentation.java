package com.bluementors.users;

public class UserSummaryRepresentation {
    public Long id;
    public String profileImageUri;
    public String firstName;
    public String lastName;
    public Integer noOfFollowers;
    public Integer noOfComments;
    public Integer noOfLikes;
    public Integer noImages;
    public Integer noOfVideo;

    public static class Builder{
        private UserSummaryRepresentation userSummaryRepresentation;

        public Builder(){
            this.userSummaryRepresentation = new UserSummaryRepresentation();
        }

        public Builder userId(Long userId){
            this.userSummaryRepresentation.id = userId;
            return this;
        }

        public Builder profileImageUri(String profileImageUri){
            this.userSummaryRepresentation.profileImageUri = profileImageUri;
            return this;
        }

        public Builder firstName(String firstName){
            this.userSummaryRepresentation.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName){
            this.userSummaryRepresentation.lastName = lastName;
            return this;
        }

        public Builder noOfFollowers(int noOfFollowers){
            this.userSummaryRepresentation.noOfFollowers = noOfFollowers;
            return this;
        }

        public Builder noOfLikes(int noOfLikes){
            this.userSummaryRepresentation.noOfLikes = noOfLikes;
            return this;
        }

        public Builder noOfComments(int noOfComments){
            this.userSummaryRepresentation.noOfComments = noOfComments;
            return this;
        }

        public Builder noOfImages(int noOfImages){
            this.userSummaryRepresentation.noImages = noOfImages;
            return this;
        }

        public Builder noOfVideos(int noOfVideos){
            this.userSummaryRepresentation.noOfVideo = noOfVideos;
            return this;
        }

        public UserSummaryRepresentation build(){
            return this.userSummaryRepresentation;
        }

    }
}
