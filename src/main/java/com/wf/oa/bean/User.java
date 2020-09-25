package com.wf.oa.bean;

import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class User    {
   private String id;
   private String logname;
   private String password;//密码
   private String name;
   private String sex;
   private String phone;
   private String email;
   private Dept deptno; //部门对象
   private Integer userno;//user对象自身的编号
   private List<Post> post;//岗位对象
   private String remark;
   private String deptSerial;// 部门编号
   private String picture;//照片名称
   private List<String> postCode;//岗位编号
   private String postName;
   private List<Role> role;//角色
    // 用户所有权限

    public User(String id){
        this.id=id;
    }


    public String getPostName(){
       return this.postName=post.toString().replace("[","").replace("]","");
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", logname='" + logname + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", deptno=" + deptno +
                ", userno=" + userno +
                ", post=" + post +
                ", remark='" + remark + '\'' +
                ", deptSerial='" + deptSerial + '\'' +
                ", picture='" + picture + '\'' +
                ", postCode=" + postCode +
                ", postName='" + postName + '\'' +
                ", role=" + role +
                '}';
    }


}
