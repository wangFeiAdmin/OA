package com.wf.oa.service;

import com.wf.oa.bean.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

public interface OperationUserService {

    /**
     * 用于验证当前用户是否存在
     * @param user
     * @return
     */
     User verifyUser(User user);


    /**
     * 查询用户
     * @param user
     * @return
     */
    List<User> selectUserAll(User user);


    /**
     * 用于验证用户
     * @param user
     * @return
     */
      boolean verify(User user);

    /**
     * 查询所有岗位
     * @return
     */
    List<HashMap<String, Object>> selectPostAll();

    /**
     * 创建用户
     * @param user
     * @return
     */
     boolean createUser(User user);

    /**
     * 删除用户
     * @param userno
     * @return
     */
    boolean deleteUser(int userno);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
     boolean updateUser(User user);

    /**
     * 修改密码
     * @param user
     * @return
     */
 boolean  updatePassword(User user);

    /**
     * 发送邮件，生成验证码
     * @param email
     */
      String sendEmail(String email);

    /**
     * 修改用户头像
     * @param file
     * @param loginUser
     * @return
     */
     boolean filesUpload(MultipartFile file, User loginUser);
}
