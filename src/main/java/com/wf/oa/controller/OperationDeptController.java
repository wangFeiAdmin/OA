package com.wf.oa.controller;

import com.wf.oa.bean.Dept;
import com.wf.oa.service.OperationDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/dept")
//@SessionAttributes(value = {"Dept"})
public class OperationDeptController {
    @Autowired
    @Qualifier("operationDeptServiceImpl")
    OperationDeptService operationDeptService;

    /**
     * 查询部门
     * @param view
     * @param opeId
     * @param model
     * @return
     */

    @GetMapping("/getDeptAll/{view}/{opeId}")
    public String getAll(@PathVariable String view,@PathVariable String opeId, Model model){
        //存入需要被修改的部门主键
        if(!"0".equals(opeId)){
            model.addAttribute("id",opeId);
        }
        model.addAttribute("DeptList",operationDeptService.getDept(new Dept()));
        return "/System_Department/"+view;
    }

    /**
     * 添加部门
     * @param dept
     * @return
     */
    @PostMapping("/create")
    @Transactional
    public String createDept(Dept dept){
        //判断是否添加成功
        operationDeptService.addDept(dept);
        return "redirect:/dept/getDeptAll/list_treeview/0";
    }


    @PostMapping("/update")
    @Transactional
    public  String updateDeptMassage(Dept dept){
        operationDeptService.updateDeptMassage(dept);
        return "redirect:/dept/getDeptAll/list_treeview/0";
    }

    @GetMapping("/delete/{id}")
    @Transactional
    public String deleteDept(@PathVariable String id){
        operationDeptService.delete(id);
        return "redirect:/dept/getDeptAll/list_treeview/0";
    }
}
