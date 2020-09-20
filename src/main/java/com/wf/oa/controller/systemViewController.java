package com.wf.oa.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * 用于和系统管理相关模块的页面跳转
 */
@Controller
public class systemViewController {
    /**
     * 和index 界面相关
     * @return
     */

    @GetMapping("/top")
    public String top(){
        return "top";
    }

    @GetMapping("/left")
    public String left(){
        return "left";
    }

    @GetMapping("/right")
    public String right(){
        return "right";
    }

    @GetMapping("/bottom")
    public String bottom(){
        return "bottom";
    }

    @GetMapping("/editUserInfoUI")
    public  String editUserInfoUI(){
        return "Person_Config/editUserInfoUI";
    }

}
