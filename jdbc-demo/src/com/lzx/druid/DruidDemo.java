package com.lzx.druid;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 * Druid 数据库连接池
 */
public class DruidDemo {
    public static void main(String[] args) throws Exception {
        System.out.println(System.getProperty("user.dir"));
        // 加载配置信息
        Properties properties = new Properties();
        properties.load(new FileInputStream("jdbc-demo/src/druid.properties"));
        // 获取连接池对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        // 获取数据库连接
        Connection connection = dataSource.getConnection();
    }
}
