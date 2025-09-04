package com.lzx.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * JDBC 快速入门
 */
public class JDBCDemo {
    public static void main(String[] args) throws Exception {
        // 1、注册驱动
        Class.forName("com.mysql.jdbc.Driver");

        // 2、获取连接
        String url = "jdbc:mysql://192.168.2.27:3306/db1";
        String username = "root";
        String password = "123456";
        Connection connection = DriverManager.getConnection(url, username, password);

        // 3、定义sql
        String sql = "update tb_user set age = 48 where id = 1";

        // 4、获取执行sql的对象
        Statement statement = connection.createStatement();

        // 5、执行sql
        int count = statement.executeUpdate(sql);

        // 6、处理结果
        System.out.println("受影响的行数：" + count);

        // 7、释放资源
        statement.close();
        connection.close();
    }
}
