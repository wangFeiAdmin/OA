package com.wf.oa.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean(name = "getShiroFilterFactoryBean")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("getDefaultWebSecurityManager") DefaultWebSecurityManager dWSM) {
        ShiroFilterFactoryBean sffb = new ShiroFilterFactoryBean();
        //设置安全管理器
        sffb.setSecurityManager(dWSM);
        Map<String,String> map=new LinkedHashMap<>();
        //此处的key为请求的地址
        map.put("/index","perms[ROLE_SystemAdministrator ]");//系统管理
        map.put("/index","perms[ROLE_ApprovalProcess]");//审批流转
        map.put("/index","perms[ROLE_PracticalUtil]");// 实用工具
        map.put("/post/**","perms[ROLE_POST]");
        map.put("/dept/**","perms[ROLE_DEPT]");
        map.put("/user/login","anon");//登录验证不被过滤
        map.put("/user/verifyCode","anon");//邮箱验证码不被过滤
        map.put("/user/**","perms[ROLE_USER]");
        map.put("/style/**","anon");//静态资源不被过滤
        map.put("/script/**","anon");//静态资源不被过滤
        map.put("/upload/**","anon");//静态资源不被过滤
        map.put("/**","authc"); // 对所有请求进行认证
        //退出系统的controller
        map.put("/logout","logout");
        sffb.setFilterChainDefinitionMap(map);
        sffb.setLoginUrl("/login");
        //授权去的页面
        sffb.setSuccessUrl("/index");
        //没授权去的页面
        sffb.setUnauthorizedUrl("/login");
        return sffb;
    }

    //创建安全管理器
    @Bean(name = "getDefaultWebSecurityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager dwsm = new DefaultWebSecurityManager();
        //关联权限对象
        dwsm.setRealm(userRealm);
        return dwsm;
    }


    //创建权限对象
    @Bean(name = "userRealm")
    public UserRealm userRealm() {
        return new UserRealm();
    }

    //装配shiro和thymeleof
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }

}