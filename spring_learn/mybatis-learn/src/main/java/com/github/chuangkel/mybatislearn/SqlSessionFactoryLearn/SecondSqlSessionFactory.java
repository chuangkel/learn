package com.github.chuangkel.mybatislearn.SqlSessionFactoryLearn;

import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;

/**
 * @program: spring_learn
 * @description: 第二种获取 SqlSessionFactory 方式
 * @author: chuangkel
 * @create: 2019-05-16 17:08
 **/
public class SecondSqlSessionFactory {

    public static void main(String[] args) {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        //使用sqlSessionFactory
        SqlSession session = sqlSessionFactory.openSession();
        try {
            Blog blog = (Blog) session.selectOne("org.mybatis.example.BlogMapper.selectBlog", 101);
        } finally {
            session.close();
        }

        //或者
        SqlSession session1 = sqlSessionFactory.openSession();
        try {
            BlogMapper mapper = session1.getMapper(BlogMapper.class);
            Blog blog = mapper.selectBlog(101);
        } finally {
            session1.close();
        }


    }

    public static SqlSessionFactory getSqlSessionFactory(){
        // 创建 sqlSessionFactory
        DataSource dataSource = BlogDataSourceFactory.getBlogDataSource();
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        configuration.addMapper(BlogMapper.class);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);

        return sqlSessionFactory;
    }
}
