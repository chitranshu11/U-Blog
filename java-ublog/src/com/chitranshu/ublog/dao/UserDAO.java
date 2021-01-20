package com.chitranshu.ublog.dao;

import com.chitranshu.ublog.dtos.User;

import java.sql.SQLException;

public interface UserDAO {
    User create(User user) throws SQLException;

    User findByEmailId(String emailId) throws SQLException;
}
