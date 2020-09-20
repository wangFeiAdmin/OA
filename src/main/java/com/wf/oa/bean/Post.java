package com.wf.oa.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Post {
   private String  id;
   private String  name;
   private Integer postno;
   private Integer deptno;
   private Integer userno;

    @Override
    public String toString() {
        return name;
    }
}
