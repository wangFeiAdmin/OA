package com.wf.oa.serviceImpl;

import com.wf.oa.bean.Dept;
import com.wf.oa.dao.OperationDeptTable;
import com.wf.oa.service.OperationDeptService;
import com.wf.oa.util.CreateUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OperationDeptServiceImpl implements OperationDeptService {
    @Autowired
    @Qualifier("operationDeptTable")
    private OperationDeptTable operationDeptTable;

    /**
     * 查询部门表
     * @param dept
     * @return
     */
    public List<Dept> getDept(Dept dept){
        return operationDeptTable.getDept(dept);
    }


    /**
     * 添加部门
     * @param dept
     * @return
     */
    public boolean addDept(Dept dept){
        //设置主键
        dept.setId(CreateUUID.getUUID());
        //设置部门编号
        dept.setNumber(operationDeptTable.getMaxNumber()+1);
        int rank=dept.getSuperiorsId().equals("0")?0:1;
        //设置部门级别
        dept.setRank(rank);
        return operationDeptTable.addDept(dept);
    }

    /**
     * 修改部门信息
     * @param dept
     * @return
     */
    public boolean updateDeptMassage(Dept dept){
        int rank=dept.getSuperiorsId().equals("0")?0:1;
        //设置部门级别
        dept.setRank(rank);
        operationDeptTable.updateDept(dept);
        return true;
    }

    /**
     * 删除部门
     * @param id
     * @return
     */
    @Override
    public boolean delete(String id) {
        Dept dept=new Dept();
        dept.setId(id);
        return  operationDeptTable.deleteDept(dept);
    }
}
