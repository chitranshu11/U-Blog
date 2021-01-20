package com.chitranshu.ublog.services;

import com.chitranshu.ublog.dao.DAOFactory;
import com.chitranshu.ublog.dao.UserDAO;
import com.chitranshu.ublog.dtos.User;
import com.chitranshu.ublog.exceptions.IncorrectPasswordException;
import com.chitranshu.ublog.exceptions.UserAlreadyRegisteredException;
import com.chitranshu.ublog.exceptions.UserNotFoundException;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {

    UserDAO userDAO = (new DAOFactory()).getUserDAO();
    private static UserServiceImpl instance;

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }

    @Override
    public boolean login(User user) throws Exception {
        User temp;
        try {
            temp = userDAO.findByEmailId(user.getEmailId());
        } catch (SQLException e) {
            throw new Exception("Some unexpected error occurred!");
        }
        if (temp == null)
            throw new UserNotFoundException("No user registered with the given email address!");

        if (!temp.getPassword().equals(user.getPassword()))
            throw new IncorrectPasswordException("Password is not correct.");

        return true;

    }

    @Override
    public boolean register(User user) throws Exception {
        User temp;
        try {
            temp = userDAO.findByEmailId(user.getEmailId());
        } catch (SQLException e) {
            throw new Exception("Some unexpected error occurred!");
        }

        if (temp != null)
            throw new UserAlreadyRegisteredException("A user with this email address already exists!");

        try {
            userDAO.create(user);
        } catch (SQLException e) {
            throw new Exception("Some unexpected error occurred!");
        }
        return true;
    }
}
