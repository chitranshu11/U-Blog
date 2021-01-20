package com.upgrad.ublog.dao;

import com.upgrad.ublog.db.Database;
import com.upgrad.ublog.dtos.User;

import java.sql.*;

/**
 * TODO: 3.5. Implement the UserDAO interface and implement this class using the Singleton pattern.
 *  (Hint: Should have a private no-arg Constructor, a private static instance attribute of type
 *  UserDAOImpl and a public static getInstance() method which returns the instance attribute.)
 * TODO: 3.6. findByEmailId() method should take email id as an input parameter and
 *  return the user details corresponding to the email id from the USER table defined
 *  in the database. (Hint: You should get the connection using the Database class)
 * TODO: 3.7. create() method should take user details as input and insert these details
 *  into the USER table. Return the same User object which was passed as an input
 *  argument. (Hint: You should get the connection using the Database class)
 */

public class UserDAOImpl implements UserDAO {
    private static UserDAOImpl instance;
    private UserDAOImpl() {}
    public static UserDAOImpl getInstance () {
        if(instance == null) instance = new UserDAOImpl();
        return instance;
    }


    @Override
    public User create(User user) throws SQLException {
        Connection connection = Database.getConnection();

        PreparedStatement ps = connection.prepareStatement("INSERT INTO user (emailId, password) VALUES (?,?)");
        //ps.setInt(1,10);
        ps.setString(1,user.getEmailId());
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

        if(resultSet.next()) {
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
