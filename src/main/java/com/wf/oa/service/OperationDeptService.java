package com.wf.oa.service;

import com.wf.oa.bean.Dept;
import com.wf.oa.bean.Post;

import java.util.List;

public interface OperationDeptService {
    /**
     * 查询部门表
     * @param dept
     * @return
     */
     List<Dept> getDept(Dept dept);

    /**
     * 添加部门
     * @param dept
     * @return
     */
     boolean addDept(Dept dept);

    /**
     * 修改部门信息
     * @param dept
     * @return
     */
     boolean updateDeptMassage(Dept dept);

    /**
     * 删除部门
     * @param id
     * @return
     */
    boolean delete(String id);


}
