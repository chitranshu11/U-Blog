package com.chitranshu.ublog.services;

public class ServiceFactory {
    public PostService getPostService() {
        return PostServiceImpl.getInstance();
    }

    public UserService getUserService() {
        return UserServiceImpl.getInstance();
    }

}
