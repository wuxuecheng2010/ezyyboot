package com.enze.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * Created by summer on 2016/11/25.
 */
@Configuration
@MapperScan(basePackages = "com.enze.dao.mapper.erp", sqlSessionTemplateRef  = "erpSqlSessionTemplate")
public class DataSourceERPConfig {

    @Bean(name = "erpDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.erp")
    public DataSource testDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "erpSqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("erpDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/erp/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "erpTransactionManager")
    public DataSourceTransactionManager testTransactionManager(@Qualifier("erpDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "erpSqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("erpSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
