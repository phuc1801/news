package com.vnnet.newscommon.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

@Configuration
@MapperScan("com.vnnet.newscommon.persistence") // Quét các Mapper Interface
public class MybatisConfig {

    private final DataSource dataSource;

    public MybatisConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);

        // Sử dụng file myBatis-config.xml
        sessionFactoryBean.setConfigLocation(new ClassPathResource("myBatis-config.xml"));

        return sessionFactoryBean.getObject();
    }
}
