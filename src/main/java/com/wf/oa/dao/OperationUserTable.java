package com.wf.oa.dao;

import com.wf.oa.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 操作用户表
 */
@Mapper
@Repository
public interface OperationUserTable {
    /**
     * 查询用户
     * @param user
     * @return
     */
    List<User> selectUserAll(User user);

    /**
     * 查询所有岗位
     * @return
     */
    List<HashMap<String, Object>> selectPostAll();

    /**
     * 查询最大用户编号
     * @return
     */
    int getMaxUserNO();

    /**
     * 设置员工所属部门
     * @param ages
     * @return
     */
    boolean   addPost(List<String> ages);

    /**
     * 添加员工
     * @param user
     * @return
     */
    boolean addUser(User user);

    /**
     * 删除用户
     * @param userno
     * @return
     */
    boolean deleteUser(int userno);

    /**
     * 将删除的用户的岗位留空
     * @param userno
     * @return
     */
    boolean updatePost(int userno);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    boolean updateUserMassage(User user);
}
