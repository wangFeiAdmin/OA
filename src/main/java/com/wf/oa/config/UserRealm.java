package com.wf.oa.config;


import com.wf.oa.bean.Post;
import com.wf.oa.bean.Role;
import com.wf.oa.bean.User;
import com.wf.oa.service.OperationUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    @Qualifier("operationUserServiceImpl")
    OperationUserService operationUserService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //对用户进行授权
        SimpleAuthorizationInfo sai=new SimpleAuthorizationInfo();
        //获取当前登录的用户
        Subject subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();
        //获取用户下的岗位，岗位做具有的所有角色
        List<Role> role = user.getRole();
        //给指定角色设置权限
        for (Role r:role){
            sai.addStringPermission(r.getRole());
        }
        return sai;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token= (UsernamePasswordToken) authenticationToken;
        char[] password = token.getPassword();
        String pas = new String(password);
        String name=token.getUsername();//获取用户名
       User u=new User();
       u.setLogname(name);
       u.setPassword(pas);
        u=operationUserService.verifyUser(u);//查询数据库用户
        //认证用户名是否正确
        if(u==null){
            return  null;
        }
        System.out.println(u);
        //将登录的当前用户存入session中
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        session.setAttribute("loginUser",u);
        //认证密码
        return new SimpleAuthenticationInfo(u,pas,"");
    }
}
