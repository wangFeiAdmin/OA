package com.wf.oa.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class ExitSystem {

    @GetMapping("/logout")
    public String esc(HttpSession session){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "loginUI";
    }
}
