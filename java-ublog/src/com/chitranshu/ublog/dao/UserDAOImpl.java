package com.chitranshu.ublog.dao;

import com.chitranshu.ublog.dtos.User;
import com.chitranshu.ublog.db.Database;

import java.sql.*;

public class UserDAOImpl implements UserDAO {
    private static UserDAOImpl instance;

    private UserDAOImpl() {
    }

    public static UserDAOImpl getInstance() {
        if (instance == null) instance = new UserDAOImpl();
        return instance;
    }

    @Override
    public User create(User user) throws SQLException {
        Connection connection = Database.getConnection();

        PreparedStatement ps = connection.prepareStatement("INSERT INTO user (emailId, password) VALUES (?,?)");
        //ps.setInt(1,10);
        ps.setString(1, user.getEmailId());
        ps.setString(2, user.getPassword());

        int row = ps.executeUpdate();

        return user;

    }

    @Override
    public User findByEmailId(String emailId) throws SQLException {
        Connection connection = Database.getConnection();
        Statement statement = connection.createStatement();

        String sql = "SELECT * FROM user WHERE emailId = " + "'" + emailId + "'";
        //System.out.println(sql);

        ResultSet resultSet = statement.executeQuery(sql);
        //System.out.println(resultSet.toString());

        if (resultSet.next()) {
            User user = new User();
            user.setUserId(resultSet.getInt("userId"));
            user.setEmailId(resultSet.getString("emailId"));
            user.setPassword(resultSet.getString("password"));

            return user;
        } else {
            return null;
        }

    }

    public static void main(String[] args) {
        try {
            UserDAO userDAO = new UserDAOImpl();
            User temp = new User();
            temp.setUserId(2);
            temp.setEmailId("temp@chitranshu.gour");
            temp.setPassword("temp");
            userDAO.create(temp);
            System.out.println(userDAO.findByEmailId("temp@temp.temp"));
        } catch (Exception e) {

            System.out.println(e.getMessage());
        }

        /**
         * Following should be the desired output of the main method.
         * User{userId=11, emailId='temp@temp.temp', password='temp'}
         */
    }
}
