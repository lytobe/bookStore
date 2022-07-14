package com.ly.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {
    private static DruidDataSource dataSource;
//    private static ThreadLocal<Connection> cons = new ThreadLocal<Connection>();


    static{

        try{
            Properties properties = new Properties();
            //读取配置文件
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
           //从流中加载数据
            properties.load(inputStream);
            //创建数据库连接池
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);

            //System.out.println(dataSource.getConnection());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

//
//    public static void main(String[] args) {
//
//    }
    //获取数据库连接池中的连接
    public static Connection getConnection() {
        Connection conn = null;
            try{
                conn = dataSource.getConnection();
            }catch (Exception e){
                e.printStackTrace();
            }
        return conn;
    }
//
//    /**
//     * 提交事务并关闭释放连接
//     */
//    public static void commitAndClose(){
//        Connection conn = cons.get();
//        if (conn!=null){
//            try {
//                conn.commit();//提交事务
//            } catch (SQLException e) {
//                e.printStackTrace();
////                throw new RuntimeException();
//            }finally {
//                try {
//                    conn.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
////                    throw new RuntimeException();
//                }
//            }
//        }
//        //手动管理事务一定要执行remove操作，否则会出错（）因为tomcat底层使用了线程池技术
//        cons.remove();
//    }
//
//    /**
//     * 提交事务并关闭释放连接
//     */
//    public static void rollBackAndClose(){
//        Connection conn = cons.get();
//        if (conn!=null){
//            try {
//                conn.rollback();//回滚事务
//            } catch (SQLException e) {
//                e.printStackTrace();
////                throw new RuntimeException();
//            }finally {
//                try {
//                    conn.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
////                    throw new RuntimeException();
//                }
//            }
//        }
//        //手动管理事务一定要执行remove操作，否则会出错（）因为tomcat底层使用了线程池技术
//        cons.remove();
//    }

    //关闭连接，放回数据库连接池
    public static void close(Connection conn){
        if (conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
