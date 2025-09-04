package com.lzx.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * JDBC 查询结果集
 */
public class JDBCDemo4 {
    public static void main(String[] args) throws Exception {
        // 1、注册驱动
        // Class.forName("com.mysql.jdbc.Driver");

        // 2、获取连接
        String url = "jdbc:mysql://192.168.2.27:3306/db1";
        String username = "root";
        String password = "123456";
        Connection connection = DriverManager.getConnection(url, username, password);

        // 3、定义sql
        String sql = "select * from tb_user where id <= 3";

        // 4、获取执行sql的对象
        Statement statement = connection.createStatement();

        // 5、执行sql
        ResultSet resultSet = statement.executeQuery(sql);

        // 6、处理结果
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString(2);
            int age = resultSet.getInt("age");
            String createTime = resultSet.getDate("create_time").toString() +
                    " " + resultSet.getTime("create_time").toString();
            System.out.println(id + "\t" + name + "\t" + age + "\t" + createTime);
        }

        // 7、释放资源
        resultSet.close();
        statement.close();
        connection.close();
    }
}
