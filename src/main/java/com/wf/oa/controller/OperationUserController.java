package com.wf.oa.controller;

import com.wf.oa.bean.Dept;
import com.wf.oa.bean.User;
import com.wf.oa.service.OperationDeptService;
import com.wf.oa.service.OperationUserService;
import com.wf.oa.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
@SessionAttributes(value = {"loginUser"})
public class OperationUserController {
    @Autowired
    @Qualifier("operationUserServiceImpl")
    private OperationUserService OperationUserService;

    @Autowired
    @Qualifier("operationDeptServiceImpl")
    OperationDeptService operationDeptService;

    /**
     * 验证登录信息
     * @param user
     * @param model
     * @return
     */
    @PostMapping("/login")
    public String verifyLogin(User user,Model model){
        //验证用户信息是否正确
        User verifyUser = OperationUserService.verifyUser(user);
        if(verifyUser!=null){
            model.addAttribute("loginUser",verifyUser);
            //返回至首页
            return "index";
        }
        model.addAttribute("error","用户名或密码错误");
        return "loginUI";
    }



    /**
     * 查询用户
     * @param model
     * @return
     */
    @GetMapping("/select")
    public  String getUser(Model model){
        model.addAttribute("userAll",OperationUserService.selectUserAll(new User()));
        return "/System_User/list";
    }

    /**
     * 查询所有部门，并跳转到新增用户界面
     * @return
     */
    @GetMapping("/saveUI/{id}")
    public String saveUI(@PathVariable String id,Model model){
        //判断当前要进行的操作是否是修改  非0表示修改 0表示添加
        if(!"0".equals(id)){
            //查询即将修改的user信息
            model.addAttribute("updateUser",OperationUserService.selectUserAll(new User(id)).get(0));
        }
       model.addAttribute("DeptList",operationDeptService.getDept(new Dept()));
       model.addAttribute("PostMap",OperationUserService.selectPostAll());
        return "/System_User/saveUI";
    }

    /**
     * 验证用户名是否重复
     * @param user
     * @return
     */
    @PostMapping("/verifyName")
    @ResponseBody
    public  Map<String,Object> verifyName(User user){
        Map<String,Object> result=new HashMap<>();
        result.put("target",OperationUserService.verify(user));
        return result;
    }

    /**
     * 创建一个新的用户
     * @param user
     * @return
     */
    @PostMapping("/create")
    @Transactional
    public String createUser(User user){
        OperationUserService.createUser(user);
        return "redirect:/user/select";
    }

    @GetMapping("/delete/{userno}")
    @Transactional
    public  String deleteUser(@PathVariable String userno){
        OperationUserService.deleteUser(Integer.parseInt(userno));
        return "redirect:/user/select";
    }

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @RequestMapping("/update")
    @Transactional
    public  String updateUser(User user){
        OperationUserService.updateUser(user);
        return "redirect:/user/select";
    }

    /**
     * 初始化密码
     * @param id
     * @param password
     * @return
     */
    @GetMapping("/initialize/{id}/{password}")
    @Transactional
    public  String  initializePassword(@PathVariable String id,@PathVariable String password){
       User u=new User();
       u.setId(id);
       //对密码进行加密
       u.setPassword(MD5Utils.getEncryptPassword(password));
       //执行初始化密码
        OperationUserService.updatePassword(u);
        return "/Globals/opSuccess";
    }

    /**
     * 向邮箱发送验证码
     * @param email
     * @return
     */
    @PostMapping("/verifyCode")
    @ResponseBody
    public  Map<String,Object> sendEmail(String email,Model model){
        String code = OperationUserService.sendEmail(email);
        code="96".concat(code);
        Map<String,Object> map=new HashMap<>();
        map.put("verifyCode",code);
       return map;

    }

    /**
     * 修改头像
     * @param file
     * @param modelMap
     * @return
     */
    @PostMapping("/upload")
    public String upload(@RequestParam("resource") MultipartFile file, ModelMap modelMap) {
        User loginUser = (User)modelMap.getAttribute("loginUser");
        OperationUserService.filesUpload(file,loginUser);
        return "redirect:/user/select";
    }

    @PostMapping("/updatePas")
    public String  updatePas(
            @RequestParam("oldPas") String oldPas, //旧密码
            @RequestParam("newPas") String newPas,// 新密码
            @RequestParam("affirmPas") String affirmPas,//确认密码
            ModelMap modelMap ,Model model){
        //存储返回结果
        HashMap<String,Object> target=new HashMap<>();
           //获取当前登录用户
        User u=(User) modelMap.get("loginUser");
        if(!newPas.equals(affirmPas)){
            target.put("ms","密码不一致");
            //返回至密码修改界面
        }else {
            if(!u.getPassword().equals(MD5Utils.getEncryptPassword(oldPas))){
                target.put("ms","原密码错误");
            }else{
                u.setPassword(MD5Utils.getEncryptPassword(newPas));
                //修改密码
                OperationUserService.updatePassword(u);
                target.put("ms","修改成功");
            }
        }
        model.addAttribute("result",target);
        //返回至密码修改界面
        return "Person_Config/editPasswordUI";
    }
}

