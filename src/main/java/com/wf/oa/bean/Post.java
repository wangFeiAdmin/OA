package com.wf.oa.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Post {
   private String  id;
   private String  name; //岗位名称
   private Integer postno; //岗位编号
   private Integer deptno; //部门编号
   private Integer userno; //用户编号
    private String remark;
    private List<Role> role;//每个岗位都对应有角色权限

    @Override
    public String toString() {
        return name;
    }



    public String toString2() {
        return "Post{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", postno=" + postno +
                ", deptno=" + deptno +
                ", userno=" + userno +
                ", remark='" + remark + '\'' +
                ", role=" + role +
                '}';
    }
}
