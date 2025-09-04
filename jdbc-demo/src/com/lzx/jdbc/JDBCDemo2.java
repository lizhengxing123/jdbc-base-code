package com.lzx.jdbc;

import java.sql.*;

/**
 * JDBC 事务
 */
public class JDBCDemo2 {
    public static void main(String[] args) throws Exception {
        // 1、注册驱动
        Class.forName("com.mysql.jdbc.Driver");

        // 2、获取连接
        String url = "jdbc:mysql://192.168.2.27:3306/db1";
        String username = "root";
        String password = "123456";
        Connection connection = DriverManager.getConnection(url, username, password);

        // 3、定义sql
        String sql1 = "update tb_user set age = 48 where id = 1";
        String sql2 = "update tb_user set age = 68 where id = 12";

        // 4、获取执行sql的对象
        Statement statement = connection.createStatement();
        PreparedStatement preparedStatement = connection.prepareStatement(sql1);
        CallableStatement callableStatement = connection.prepareCall(sql2);


        try {
            // 开启事务
            connection.setAutoCommit(false);

            // 5.1、执行sql
            int count1 = statement.executeUpdate(sql1);

            // 6.1、处理结果
            System.out.println("受影响的行数：" + count1);

            // 5.2、执行sql
            int count2 = statement.executeUpdate(sql2);

            // 6.2、处理结果
            System.out.println("受影响的行数：" + count2);

            // 提交事务
            connection.commit();
        } catch (Exception e) {
            // 回滚事务
            connection.rollback();
            throw new RuntimeException(e);
        }

        // 7、释放资源
        statement.close();
        connection.close();
    }
}
