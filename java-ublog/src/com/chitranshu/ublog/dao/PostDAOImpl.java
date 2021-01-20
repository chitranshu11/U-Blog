package com.chitranshu.ublog.dao;

import com.chitranshu.ublog.dtos.Post;
import com.chitranshu.ublog.db.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PostDAOImpl implements PostDAO {
    private static PostDAOImpl instance;

    private PostDAOImpl() {
    }

    public static PostDAOImpl getInstance() {
        if (instance == null) instance = new PostDAOImpl();
        return instance;
    }

    Connection connection = Database.getConnection();
    PreparedStatement statement;

    @Override
    public Post create(Post post) throws SQLException {

        statement = connection.prepareStatement("INSERT into post (emailId, tag, title," +
                "description,timestamp) VALUES (?,?,?,?,?)");
        statement.setString(1, post.getEmailId());
        statement.setString(2, post.getTag());
        statement.setString(3, post.getTitle());
        statement.setString(4, post.getDescription());
        statement.setString(5, post.getTimestamp().toString());

        statement.executeUpdate();

        return post;
    }

    @Override
    public List<Post> findByEmailId(String emailId) throws SQLException {

        String sql = "SELECT * from post where emailId = " + "'" + emailId + "'";
        statement = connection.prepareStatement(sql);

        ResultSet resultSet = statement.executeQuery();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime;

        List<Post> allPost = new ArrayList<>();
        while (resultSet.next()) {
            Post temp = new Post();
            temp.setEmailId(emailId);
            temp.setTitle(resultSet.getString("title"));
            temp.setTag(resultSet.getString("tag"));
            temp.setPostId(resultSet.getInt("postId"));
            temp.setDescription(resultSet.getString("description"));
            dateTime = LocalDateTime.parse(resultSet.getString("timestamp"));
            temp.setTimestamp(dateTime);

            allPost.add(temp);
        }
        return allPost;
    }

    @Override
    public List<Post> findByTag(String tag) throws SQLException {
        List<Post> posts = new ArrayList<>();
        String sql = "SELECT * FROM post where tag = " + "'" + tag + "'";
        statement = connection.prepareStatement(sql);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            Post post = new Post();
            LocalDateTime timestamp = LocalDateTime.parse(resultSet.getString("timestamp"));
            post.setTimestamp(timestamp);
            post.setTag(resultSet.getString("tag"));
            post.setDescription(resultSet.getString("description"));
            post.setTitle(resultSet.getString("title"));
            post.setPostId(resultSet.getInt("postId"));
            post.setEmailId(resultSet.getString("emailId"));

            posts.add(post);

        }
        return posts;
    }

    @Override
    public Post findByPostId(int postId) throws SQLException {
        Post post = new Post();
        String sql = "SELECT * FROM post WHERE postId = " + postId;
        statement = connection.prepareStatement(sql);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            LocalDateTime timestamp = LocalDateTime.parse(resultSet.getString("timestamp"));
            post.setTimestamp(timestamp);
            post.setTag(resultSet.getString("tag"));
            post.setDescription(resultSet.getString("description"));
            post.setTitle(resultSet.getString("title"));
            post.setPostId(resultSet.getInt("postId"));
            post.setEmailId(resultSet.getString("emailId"));
        }
        return post;
    }

    @Override
    public List<String> findAllTags() throws SQLException {
        List<String> allTag = new ArrayList<>();
        String sql = "SELECT * FROM post";

        statement = connection.prepareStatement(sql);

        ResultSet resultSet = statement.executeQuery();
        String tag;
        while (resultSet.next()) {
            tag = resultSet.getString("tag");
            allTag.add(tag);
        }

        return allTag;
    }

    @Override
    public boolean deleteByPostId(int postId) throws SQLException {
        String sql = "DELETE FROM post WHERE postId = " + postId;
        statement = connection.prepareStatement(sql);

        int row = statement.executeUpdate();
        return row != 0;
    }
}
