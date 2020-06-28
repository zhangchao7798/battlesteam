package com.medical.exam.config;
/**
* @author Wu
* @date 2019年7月1日
* MyBatis配置类
*/

import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

@EnableTransactionManagement
@Configuration
@MapperScan(basePackages= {"com.medical.exam.dao"})
public class MyBatisConfigurer {

}
