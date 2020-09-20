package com.wf.oa;

import com.wf.oa.bean.Dept;
import com.wf.oa.dao.OperationDeptTable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Random;

@SpringBootTest
class OaApplicationTests {


    @Test
    void contextLoads() {
        for(int i=0; i<10000; i++){
            System.out.println(i+"次————》"+getCode());
        }

    }

    //随机一个四位数作为验证码
    private  String getCode(){
        return String.format("%04d",new Random().nextInt(9999));
    }

}
