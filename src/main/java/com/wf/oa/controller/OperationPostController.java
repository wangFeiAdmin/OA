package com.wf.oa.controller;

import com.wf.oa.bean.Dept;
import com.wf.oa.bean.Post;
import com.wf.oa.bean.Role;
import com.wf.oa.service.OperationDeptService;
import com.wf.oa.service.OperationPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/post")
public class OperationPostController {

    @Autowired
    @Qualifier("operationPostServiceImpl")
    OperationPostService operationPostService;

    @Autowired
    @Qualifier("operationDeptServiceImpl")
    OperationDeptService operationDeptService;

    /**
     * 查询全部岗位
     * @param model
     * @return
     */
    @GetMapping("/selectAll/{view}/{id}")
    public  String selectPostAll(@PathVariable String view,@PathVariable String id,Model model){
       if(!"0".equals(id)){
           //查询部门
           model.addAttribute("DeptList",operationDeptService.getDept(new Dept()));
           Post p=new Post();
           p.setId(id);
           //查询需要被修改的岗位
           model.addAttribute("updatePost",operationPostService.getPost(p));
       }else{
           //去条件查询全部岗位
           model.addAttribute("postList",operationPostService.getPost(new Post()));
       }

        return "System_Role/"+view;
    }

    /**
     * 删除岗位
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    @Transactional
    public  String deletePost(@PathVariable String id){
        operationPostService.delete(id);
        return "redirect:/post/selectAll/list/0";
    }

    /**
     * 查询所有部门并跳转到增岗位界面
     * @param model
     * @return
     */
    @GetMapping("/saveUI")
    public String selectDept(Model model){
        model.addAttribute("DeptList",operationDeptService.getDept(new Dept()));
        return "System_Role/saveUI";
    }

    /**
     * 添加岗位
     * @param post
     * @return
     */
    @PostMapping("/create")
    @Transactional
    public String  addPost(Post post){
    operationPostService.createPost(post);
        return "redirect:/post/selectAll/list/0";
    }


    /**
     * 修改岗位
     * @param post
     * @return
     */
    @PostMapping("/update")
    @Transactional
    public String  updatePost(Post post){
        operationPostService.updatePost(post);
        return "redirect:/post/selectAll/list/0";
    }

    /**
     * 对部门授权
     * @param resourceIdList
     * @return
     */
    @PostMapping("/auth")
    @Transactional
    public String authorization(@RequestParam("postno") String postno,@RequestParam("userno") String userno,@RequestParam("resourceIdList") List<String> resourceIdList){
        resourceIdList.add(0,userno);
        resourceIdList.add(1,postno);
        operationPostService.addAuth(resourceIdList);
        return "redirect:/post/selectAll/list/0";
    }

    //权限配置
    @GetMapping("/System_Role/setPrivilegeUI/{id}")
    public  String setPrivilegeUI(@PathVariable String id, Model model) {
        Post post = new Post();
        post.setId(id);
        model.addAttribute("authPost",operationPostService.getPost(post));
        return "/System_Role/setPrivilegeUI";
    }
}
