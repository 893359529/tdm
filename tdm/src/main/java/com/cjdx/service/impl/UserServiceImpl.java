package com.cjdx.service.impl;

import com.cjdx.mapper.UserInfoMapper;
import com.cjdx.pojo.UserInfo;
import com.cjdx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserInfoMapper userInfoMapper;


    @Override
    public UserInfo queryUserByName(String username) {
        Example example = new Example(UserInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username",username);
        UserInfo userInfo = userInfoMapper.selectOneByExample(example);
        return userInfo;
    }

    @Override
    public void addUser(String username,String password) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(username);
        userInfo.setPassword(password);
        userInfoMapper.insert(userInfo);
    }
}
