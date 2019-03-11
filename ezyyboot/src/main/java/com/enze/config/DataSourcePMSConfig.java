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
@MapperScan(basePackages = "com.enze.dao.mapper.pms", sqlSessionTemplateRef  = "pmsSqlSessionTemplate")
public class DataSourcePMSConfig {

    @Bean(name = "pmsDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.pms")
    public DataSource testDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "pmsSqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("pmsDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/pms/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "pmsTransactionManager")
    public DataSourceTransactionManager testTransactionManager(@Qualifier("pmsDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "pmsSqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("pmsSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
