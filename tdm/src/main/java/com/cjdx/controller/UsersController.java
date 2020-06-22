package com.cjdx.controller;

import com.cjdx.exception.BusinessException;
import com.cjdx.exception.EmBusinessError;
import com.cjdx.pojo.UserInfo;
import com.cjdx.service.UserService;
import com.cjdx.utils.CommonReturnType;
import com.cjdx.utils.JSONResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
public class UsersController {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;


    @RequestMapping("/toLogin")
    public CommonReturnType toLogin() throws Exception{
        throw new BusinessException(EmBusinessError.USER_NOT_LOGIN,"去登录");
    }

    @RequestMapping("/l")
    public CommonReturnType l(String username) throws Exception{
        if(StringUtils.isBlank(username)) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        userService.queryUserByName(username);
        return CommonReturnType.create(null);
    }
    @RequestMapping("/register")
    public CommonReturnType register(String username,String password) throws Exception {
        UserInfo userInfo = userService.queryUserByName(username);
        if (userInfo != null) {
            throw new BusinessException(EmBusinessError.UNKNOWN_ERROR,"该用户名已存在");
        }
        userService.addUser(username, password);
        return CommonReturnType.create(null);
    }

    @RequestMapping("/login")
    public CommonReturnType login(String username,String password) throws Exception{
        if (StringUtils.isBlank(username)) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"账号不能为空");
        }
        if (StringUtils.isBlank(password)) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"密码不能为空");
        }
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();


        //封装用户登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        //执行登录方法，没有异常则登录成功
        try {
            subject.login(token);

            // 修改成若用户登录验证成功后将对应的登录信息和登录凭证一起存入redis中
            // 1.生成登录凭证token , UUID
            String uuidToken = UUID.randomUUID().toString();
            // 优化掉横杠
            uuidToken = uuidToken.replace("-","");
            // 建立token和用户登录状态之间的联系
            UserInfo userInfo = userService.queryUserByName(username);
            redisTemplate.opsForValue().set(uuidToken, userInfo);
            redisTemplate.expire(uuidToken, 12, TimeUnit.HOURS);

            return CommonReturnType.create(uuidToken);
        } catch (UnknownAccountException e) {   //用户名不存在
            return CommonReturnType.create("用户名不存在");
        } catch (IncorrectCredentialsException e) {   //密码错误
            return CommonReturnType.create("密码错误");
        }
    }
}
