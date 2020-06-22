package com.cjdx.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    // 1. ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        // 设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);

        // 添加shiro内置过滤器
        /*
            anon: 无需认证
            authc: 必须认证
            user: 必须拥有记住我才能访问
            perms: 拥有对某个资源的访问权限才能访问
            roles: 拥有某个角色权限才能访问
         */
        Map<String, String> filterMap = new LinkedHashMap<>();

//        filterMap.put("/hello","authc");
//        filterMap.put("/hi","anon");
        filterMap.put("/toLogin","anon");
        filterMap.put("/login","anon");
        filterMap.put("/*","authc");

        bean.setFilterChainDefinitionMap(filterMap);

        //设置登录请求
        //bean.setLoginUrl("/login");
        //未授权操作请求
        bean.setUnauthorizedUrl("/toLogin");

        return bean;
    }


    // 2. DefaultWebSecurityManager
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 关联UserRealm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    // 3. 创建realm
    @Bean
    public UserRealm userRealm(){


        return new UserRealm();
    }
}
