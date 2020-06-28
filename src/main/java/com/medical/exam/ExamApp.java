package com.medical.exam;

import com.medical.exam.dao.OrderMapper;
import com.medical.exam.util.WechatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class ExamApp {//implements CommandLineRunner {
    //@Autowired
   // OrderMapper orderMapper;
    public static void main(String[] args) {
        SpringApplication.run(ExamApp.class, args);
    }
    /*
    @Override
    public void run(String... args) throws Exception {
        WechatUtils.sendTemplateMessage("oWywd5A1t9TEWI91lqwP6we8fH98",orderMapper.selectByPrimaryKey("2019072909401215820"));
    }
    */
}
