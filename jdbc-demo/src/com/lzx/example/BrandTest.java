package com.lzx.example;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.lzx.pojo.Brand;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class BrandTest {
    /**
     * 查询所有数据
     * 1、SQL：select * from tb_brand;
     * 2、参数：无
     * 3、返回值：List<Brand>
     */
    @Test
    public void testSelectAll() throws Exception {
        // 获取连接
        Connection connection = getConnection();
        // 定义SQL
        String sql = "select * from tb_brand";
        // 获取预编译执行SQL对象
        PreparedStatement statement = connection.prepareStatement(sql);
        // 设置参数
        // ...
        // 执行SQL
        ResultSet resultSet = statement.executeQuery();
        // 处理结果
        List<Brand> brandList = new ArrayList<>();
        while (resultSet.next()) {
            // 获取数据
            int id = resultSet.getInt("id");
            String brandName = resultSet.getString("brand_name");
            String companyName = resultSet.getString("company_name");
            int ordered = resultSet.getInt("ordered");
            String description = resultSet.getString("description");
            int status = resultSet.getInt("status");
            // 封装对象
            Brand brand = new Brand();
            brand.setId(id);
            brand.setBrandName(brandName);
            brand.setCompanyName(companyName);
            brand.setOrdered(ordered);
            brand.setDescription(description);
            brand.setStatus(status);
            // 存储对象
            brandList.add(brand);
        }
        // 打印结果
        System.out.println(brandList);
        // 释放资源
        resultSet.close();
        statement.close();
        connection.close();
    }

    /**
     * 新增数据
     * 1、SQL：insert into tb_brand (brand_name, company_name, ordered, description, status) values (?, ?, ?, ?, ?);
     * 2、参数：brandName, companyName, ordered, description, status
     * 3、返回值：boolean
     */
    @Test
    public void testInsert() throws Exception {
        String brandName = "品牌名称";
        String companyName = "品牌公司";
        int ordered = 1;
        String description = "品牌描述";
        int status = 1;
        // 获取连接
        Connection connection = getConnection();
        // 定义SQL
        String sql = "insert into tb_brand (brand_name, company_name, ordered, description, status) values (?, ?, ?, ?, ?);";
        // 获取预编译执行SQL对象
        PreparedStatement statement = connection.prepareStatement(sql);
        // 设置参数
        statement.setString(1, brandName);
        statement.setString(2, companyName);
        statement.setInt(3, ordered);
        statement.setString(4, description);
        statement.setInt(5, status);
        // 执行SQL
        int count = statement.executeUpdate();
        // 处理结果
        System.out.println("新增数据结果：" + count);
        // 释放资源
        statement.close();
        connection.close();
    }

    /**
     * 删除数据
     * 1、SQL：delete from tb_brand where id = ?;
     * 2、参数：id
     * 3、返回值：boolean
     */
    @Test
    public void testDelete() throws Exception {
        // 获取连接
        Connection connection = getConnection();
        // 定义SQL
        String sql = "delete from tb_brand where id = ?;";
        // 获取预编译执行SQL对象
        PreparedStatement statement = connection.prepareStatement(sql);
        // 设置参数
        int id = 1;
        statement.setInt(1, id);
        // 执行SQL
        int count = statement.executeUpdate();
        // 处理结果
        System.out.println("删除数据结果：" + count);
        // 释放资源
        statement.close();
        connection.close();
    }

    /**
     * 修改数据
     * 1、SQL：update tb_brand set brand_name = ?, company_name = ?, ordered = ?, description = ?, status = ? where id = ?;
     * 2、参数：brandName, companyName, ordered, description, status, id
     * 3、返回值：boolean
     */
    @Test
    public void testUpdate() throws Exception {
        // 获取连接
        Connection connection = getConnection();
        // 定义SQL
        String sql = "update tb_brand set brand_name = ?, company_name = ?, ordered = ?, description = ?, status = ? where id = ?;";
        // 获取预编译执行SQL对象
        PreparedStatement statement = connection.prepareStatement(sql);
        // 设置参数
        String brandName = "品牌名称";
        String companyName = "品牌公司";
        int ordered = 1;
        String description = "品牌描述";
        int status = 1;
        int id = 1;
        statement.setString(1, brandName);
        statement.setString(2, companyName);
        statement.setInt(3, ordered);
        statement.setString(4, description);
        statement.setInt(5, status);
        statement.setInt(6, id);
        // 执行SQL
        int count = statement.executeUpdate();
        // 处理结果
        System.out.println("修改数据结果：" + count);
        // 释放资源
        statement.close();
        connection.close();
    }

    /**
     * 返回连接
     * @return connection
     */
    public Connection getConnection() throws Exception {
        System.out.println(System.getProperty("user.dir"));
        // 加载配置信息
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/druid.properties"));
        // 获取连接池对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        // 获取数据库连接
        Connection connection = dataSource.getConnection();
        return connection;
    }
}
