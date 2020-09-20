package com.wf.oa.serviceImpl;

import com.wf.oa.bean.Post;
import com.wf.oa.bean.User;
import com.wf.oa.dao.OperationUserTable;
import com.wf.oa.service.OperationUserService;
import com.wf.oa.util.CreateUUID;
import com.wf.oa.util.MD5Utils;
import jdk.nashorn.internal.objects.annotations.Where;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


@Service
public class OperationUserServiceImpl implements OperationUserService {
    //默认密码
    private static final String   DEFAULT_PASSWORD="ICy5YqxZB1uWSwcVLSNLcA==";
    //默认头像
    private static final String   DEFAULT_PICTURE="tu.jpeg";

    @Autowired
    @Qualifier("operationUserTable")
    private OperationUserTable OperationUserTable;

    //此处报错不影响执行
    @Autowired
    JavaMailSenderImpl javaMailSender;


    /**
     * 用于验证当前用户是否存在
     * @param user
     * @return
     */
    public User verifyUser(User user){
        //对当前需要校验的密码进行加密
        user.setPassword(MD5Utils.getEncryptPassword(user.getPassword()));
        List<User> users = this.selectUserAll(user);
        return users.size()>0?users.get(0):null;
    }


    /**
     * 查询用户
     * @param user
     * @return
     */
    public  List<User> selectUserAll(User user){
        List<User> users = OperationUserTable.selectUserAll(user);
        return  users;
    }

    /**
     * 用于验证用户
     * @param user
     * @return
     */
    public  boolean verify(User user){
        return OperationUserTable.selectUserAll(user).size()!=0;
    }

    /**
     * 查询所有部门
     * @return
     */
    @Override
    public List<HashMap<String, Object>> selectPostAll() {
        return OperationUserTable.selectPostAll();
    }


    /**
     * 创建用户
     * @param user
     * @return
     */
    public boolean createUser(User user){
        //设置主键
        user.setId(CreateUUID.getUUID());
        //设置用户默认密码
        user.setPassword(DEFAULT_PASSWORD);
        //设置默认头像
        user.setPicture(DEFAULT_PICTURE);
        //设置用户编号
        user.setUserno(OperationUserTable.getMaxUserNO()+1);
        //获取岗位编号
        List<String> postno=user.getPostCode();
        postno.add(0,String.valueOf(user.getUserno()));
        //为指定岗位设置员工
        OperationUserTable.addPost(postno);
        //添加用户
        return OperationUserTable.addUser(user);
    }

    /**
     * 删除用户
     * @param userno
     * @return
     */
    public boolean deleteUser(int userno){
        //删除用户
        OperationUserTable.deleteUser(userno);
        //置空岗位
        return OperationUserTable.updatePost(userno);
    }

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    public boolean updateUser(User user){
        //将原有岗位置空
        OperationUserTable.updatePost(user.getUserno());
        List<String> postno=user.getPostCode();
        postno.add(0,String.valueOf(user.getUserno()));
        //修改岗位
        OperationUserTable.addPost(postno);
        //修改信息
        return OperationUserTable.updateUserMassage(user);
    }

    /**
     * 修改密码
     * @param user
     * @return
     */
    public  boolean  updatePassword(User user){
        return OperationUserTable.updateUserMassage(user);
    }


    /**
     * 发送邮件，生成验证码
     * @param email
     */
    public  String  sendEmail(String email){
        //一个复杂的邮件
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        //组装
        MimeMessageHelper helper = null;
        //随机一个验证码
        String code=getCode();
        try {
            helper = new MimeMessageHelper(mimeMessage, true);
            helper.setSubject("OA邮箱验证");//通知
            helper.setText("本次登录的验证码为：<p style='color: red'>"+code+"</p>",true);//发送文本内容，并设置字体颜色
            helper.setTo(email);//发送给谁
            helper.setFrom("3218839619@qq.com");//由谁发送
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return code;
    }

    //随机一个四位数作为验证码
    private  String getCode(){
       return String.format("%04d",new Random().nextInt(9999));
    }
}
