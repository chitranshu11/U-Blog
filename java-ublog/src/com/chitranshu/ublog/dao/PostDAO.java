package com.chitranshu.ublog.dao;

import com.chitranshu.ublog.dtos.Post;

import java.sql.SQLException;
import java.util.List;

public interface PostDAO {
    Post create(Post post) throws SQLException;

    List<Post> findByEmailId(String emailId) throws SQLException;

    List<Post> findByTag(String tag) throws SQLException;

    Post findByPostId(int postId) throws SQLException;

    List<String> findAllTags() throws SQLException;

    boolean deleteByPostId(int postId) throws SQLException;
}
