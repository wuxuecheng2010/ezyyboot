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
@MapperScan(basePackages = "com.enze.dao.mapper.mapserver", sqlSessionTemplateRef  = "mapserverSqlSessionTemplate")
public class DataSourceMapServerConfig {

    @Bean(name = "mapserverDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.mapserver")
    public DataSource testDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "mapserverSqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("mapserverDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/mapserver/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "mapserverTransactionManager")
    public DataSourceTransactionManager testTransactionManager(@Qualifier("mapserverDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "mapserverSqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("mapserverSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
