package com.medical;

import com.medical.exam.ExamApp;
import com.medical.exam.dao.UserMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = ExamApp.class)
@RunWith(SpringRunner.class)
public class StudyMapperTest
{
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;


    /**
     * uploadByCamera test :-)
     */
    @Test
    public void uploadByCameraTest()
    {

       System.out.println( sqlSessionFactory.getConfiguration().isMapUnderscoreToCamelCase());

        System.out.println( sqlSessionFactory.getConfiguration().getTypeAliasRegistry());

        userMapper.getUserPage(null, 1).forEach(study -> {
          System.out.println(study);
      });
    }
}
