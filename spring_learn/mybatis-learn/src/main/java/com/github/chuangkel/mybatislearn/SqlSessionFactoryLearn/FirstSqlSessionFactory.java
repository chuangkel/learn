package com.github.chuangkel.mybatislearn.SqlSessionFactoryLearn;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @program: spring_learn
 * @description: 第一种 根据配置文件获取 SqlSessionFactory
 * @author: chuangkel
 * @create: 2019-05-16 17:13
 **/
public class FirstSqlSessionFactory {

    public static void main(String[] args) throws IOException {

        String resource = "classpath:mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);


    }
}
