package com.cjdx.config;

import com.alibaba.fastjson.JSONObject;
import com.cjdx.exception.BusinessException;
import com.cjdx.exception.EmBusinessError;
import com.cjdx.utils.CommonReturnType;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MyAuthenticationFilter extends FormAuthenticationFilter {
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        Subject subject = SecurityUtils.getSubject();
        Object user = subject.getPrincipal();

        if (Objects.equals(user, null)) {
            throw new BusinessException(EmBusinessError.USER_NOT_LOGIN);

        }
        return false;
    }
}
