package com.wf.oa.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Dept {
    //主键
    private String id;
    //部门名称
    private String name;
    //部门编号
    private Integer number;
    // 部门级别
    private Integer rank;
    //上级部门对象
    private Dept superiors;
    //上级部门编号
    private String superiorsId;
    //职能描述
    private String remark;
    @Override
    public String toString() {
        return "Dept{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", rank=" + rank +
                ", superiors=" + superiors +
                ", superiorsId='" + superiorsId + '\'' +
                "remark='"+remark+
                '}';
    }
}
