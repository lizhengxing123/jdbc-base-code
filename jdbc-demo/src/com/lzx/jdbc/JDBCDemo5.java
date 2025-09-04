package com.lzx.jdbc;

import java.sql.*;

/**
 * JDBC 预编译sql
 *  1、预编译sql可以防止sql注入
 *  2、预编译sql可以提高sql执行效率
 */
public class JDBCDemo5 {
    public static void main(String[] args) throws Exception {
        // 1、注册驱动
        // Class.forName("com.mysql.jdbc.Driver");

        // 2、获取连接
        String url = "jdbc:mysql://192.168.2.27:3306/db1?useServerPrepStmts=true&serverTimezone=UTC";
        String username = "root";
        String password = "123456";
        Connection connection = DriverManager.getConnection(url, username, password);

        // 3、定义sql
        String name = "zhangsan";
        String pwd = "' or '1'='1";
        String sql = "select * from tb_user where name= ? and age= ?";
        System.out.println(sql);

        // 4、获取执行sql的对象
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, pwd);

        // 5、执行sql
        ResultSet resultSet = preparedStatement.executeQuery();
        System.out.println(resultSet);

        // 6、处理结果
        if (resultSet.next()) {
            System.out.println("登录成功");
        } else {
            System.out.println("登录失败");
        }

        // 7、释放资源
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }
}
