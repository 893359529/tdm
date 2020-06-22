package com.cjdx.service;

import com.cjdx.pojo.UserInfo;

public interface UserService {
    UserInfo queryUserByName(String username);

    void addUser(String username,String password);
}
