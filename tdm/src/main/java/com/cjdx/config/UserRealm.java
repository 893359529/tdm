package com.cjdx.config;

import com.cjdx.pojo.UserInfo;
import com.cjdx.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.UUID;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;
    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行shiro认证");


        UsernamePasswordToken userToken = (UsernamePasswordToken) authenticationToken;




        UserInfo userInfo = userService.queryUserByName(userToken.getUsername());
        if(userInfo == null) {
            return null;
        }


        // 密码认证
        return new SimpleAuthenticationInfo("",userInfo.getPassword(),"");
    }
}
