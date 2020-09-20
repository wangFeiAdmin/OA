package com.wf.oa.config;


import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RequestInterceptor  implements HandlerInterceptor {
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        HttpSession session = request.getSession();
//        User user = (User) ((HttpSession) session).getAttribute("user");
//        if(user!=null){
//            return true;
//        }else{
//            //拦截请求，提示错误信息
//            request.setAttribute("mss","非法登录");
//            //返回登录界面
//            request.getRequestDispatcher("/index.html").forward(request,response);
//            return false;
//        }
//    }
}