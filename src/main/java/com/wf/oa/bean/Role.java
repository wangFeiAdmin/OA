package com.wf.oa.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 角色对象
 */
@Data
@NoArgsConstructor
public class Role {
    private Integer id;
    private String role;

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", role='" + role + '\'' +
                '}';
    }
}
