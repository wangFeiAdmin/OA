package com.wf.oa.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Post {
   private String  id;
   private String  name; //岗位名称
   private Integer postno; //岗位编号
   private Integer deptno; //部门编号
   private Integer userno; //用户编号
    private String remark;
    @Override
    public String toString() {
        return name;
    }
}
