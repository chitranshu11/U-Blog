package com.chitranshu.ublog.services;


import com.chitranshu.ublog.exceptions.PostNotFoundException;
import com.chitranshu.ublog.utils.DateTimeFormatter;
import com.chitranshu.ublog.dao.DAOFactory;
import com.chitranshu.ublog.dao.PostDAO;
import com.chitranshu.ublog.dtos.Post;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PostServiceImpl implements PostService {
    private static PostServiceImpl instance;

    private PostServiceImpl() {
    }

    PostDAO postDAO = (new DAOFactory()).getPostDAO();

    public static PostServiceImpl getInstance() {
        if (instance == null) instance = new PostServiceImpl();
        return instance;
    }

    @Override
    public Post create(Post post) throws Exception {
        try {
            postDAO.create(post);
            return post;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Some unexpected error occurred!");
        }
    }

    @Override
    public List<Post> getPostsByEmailId(String emailId) throws Exception {
        List<Post> allPost;
        try {
            allPost = postDAO.findByEmailId(emailId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Some unexpected error occurred!");
        }
        return allPost;
    }

    @Override
    public List<Post> getPostsByTag(String tag) throws Exception {
        List<Post> posts;
        try {
            posts = postDAO.findByTag(tag);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Some unexpected error occurred!");
        }
        return posts;
    }

    @Override
    public Set<String> getAllTags() throws Exception {
        Set<String> tags;
        try {
            tags = new HashSet<>(postDAO.findAllTags());
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Some unexpected error occurred!");
        }
        return tags;
    }

    @Override
    public boolean deletePost(int postId, String emailId) throws Exception {

        Post post;
        try {
            post = postDAO.findByPostId(postId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Some unexpected error occurred!");
        }
        if (post == null || post.getPostId() == 0 || post.getEmailId() == null)
            throw new PostNotFoundException("No Post exist with the given Post Id");

        if (!post.getEmailId().equals(emailId))
            return false;

        return postDAO.deleteByPostId(postId);
    }

    @Override
    public void printPost(Post post) {
        if (post == null) return;
        System.out.println("PostId: " + post.getPostId());
        System.out.println("Title: " + post.getTitle());
        System.out.println("Tag: " + post.getTag());
        System.out.println("Description: " + post.getDescription());
        System.out.println("Date & Time: " + DateTimeFormatter.format(post.getTimestamp()));
        System.out.println("-------------------------------------");
    }
}
