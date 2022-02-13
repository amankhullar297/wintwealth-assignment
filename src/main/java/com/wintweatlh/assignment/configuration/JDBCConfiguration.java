package com.wintweatlh.assignment.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class JDBCConfiguration {
    @Value("${mysql.url}")
    private String mysqlHost;

    @Value("${mysql.userName}")
    private String userName;

    @Value("${mysql.password}")
    private String password;

    @Bean
    public DataSource getMysqlDataStore(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl(mysqlHost);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);

        return dataSource;
    }

    @Bean
    public JdbcTemplate getMysqlJdbcTemplate(DataSource datasource){
        return new JdbcTemplate(datasource);
    }
}
