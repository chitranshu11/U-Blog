package com.chitranshu.ublog.dtos;

import java.time.LocalDateTime;

public class Post {
    private int postId;
    private String emailId;
    private String tag;
    private String title;
    private String description;
    private LocalDateTime timestamp;

    public Post() {
    }

    public Post(int postId, String emailId, String tag, String title, String description, LocalDateTime timestamp) {
        this.postId = postId;
        this.emailId = emailId;
        this.tag = tag;
        this.title = title;
        this.description = description;
        this.timestamp = timestamp;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

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

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", emailId='" + emailId + '\'' +
                ", tag='" + tag + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
