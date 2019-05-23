package com.github.test;


import java.sql.*;

/**
 * @program: javabase
 * @description:
 * @author: chuangkel
 * @create: 2019-04-12 16:22
 **/
public class Test {

    @org.junit.Test
    public void test(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?serverTimezone=UTC","root","");
            conn.setAutoCommit(false);
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from users");
            statement.execute("insert users(id,name,salary) value (101,\"name\",1001)");
            conn.commit();
            while (resultSet.next()){
//                byte[] bytes= resultSet.getBytes(2);  //
//                String s = resultSet.getCursorName(); //获取表头，驱动不支持
                System.out.println();
            }
            System.out.println();

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback(); //异常回滚
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
