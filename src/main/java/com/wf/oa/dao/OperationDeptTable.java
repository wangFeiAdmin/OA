package com.wf.oa.dao;

import com.wf.oa.bean.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 操作部门表
 */
@Mapper
@Repository
public interface OperationDeptTable {

    /**
     * 获取部门,或者获取全部部门
     * @return
     */
   List<Dept> getDept(Dept dept);

    /**
     * 获取最大部门编号
     * @return
     */
   int getMaxNumber();

    /**
     * 添加部门
     * @param dept
     * @return
     */
    boolean addDept(Dept dept);

    /**
     * 修改部门
     * @param dept
     * @return
     */
    boolean updateDept(Dept dept);

    /**
     * 删除部门
     * @param dept
     * @return
     */
    boolean deleteDept(Dept dept);
}

