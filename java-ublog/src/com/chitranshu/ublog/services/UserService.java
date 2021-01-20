package com.chitranshu.ublog.services;

import com.chitranshu.ublog.dtos.User;

public interface UserService {
    boolean login(User user) throws Exception;

    boolean register(User user) throws Exception;
}
