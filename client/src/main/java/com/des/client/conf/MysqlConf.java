//package com.des.client.conf;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.context.config.annotation.RefreshScope;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@RefreshScope
//@Configuration
//public class MysqlConf {
//
//    @Value("${custom.mysql.address}")
//    private String address;
//
//    @Value("${custom.mysql.pwd}")
//    private String pwd;
//
//    @Value("${custom.mysql.user}")
//    private String user;
//
//    @Value("${custom.mysql.driver}")
//    private String driver;
//
//    @Bean
//    public DruidDataSource druidDataSource() {
//        //Druid 数据源配置
//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setDriverClassName(driver);
//        dataSource.setUrl(address);
//        dataSource.setUsername(user);
//        dataSource.setPassword(pwd);
//        //初始连接数(默认值0)
//        dataSource.setInitialSize(8);
//        //最小连接数(默认值0)
//        dataSource.setMinIdle(8);
//        //最大连接数(默认值8,注意"maxIdle"这个属性已经弃用)
//        dataSource.setMaxActive(32);
//        return dataSource;
//    }
//}
