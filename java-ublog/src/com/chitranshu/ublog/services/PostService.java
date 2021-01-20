package com.chitranshu.ublog.services;

import com.chitranshu.ublog.dtos.Post;

import java.util.List;
import java.util.Set;

public interface PostService {
    Post create(Post post) throws Exception;

    List<Post> getPostsByEmailId(String emailId) throws Exception;

    List<Post> getPostsByTag(String tag) throws Exception;

    Set<String> getAllTags() throws Exception;

    boolean deletePost(int postId, String emailId) throws Exception;

    void printPost(Post post);
}
