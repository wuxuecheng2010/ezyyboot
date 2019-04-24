package com.enze.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.xa.DruidXADataSource;

import java.sql.SQLException;

import javax.sql.DataSource;

/**
 * Created by summer on 2016/11/25.
 */
@Configuration
@MapperScan(basePackages = "com.enze.dao.mapper.mapserver", sqlSessionTemplateRef  = "mapserverSqlSessionTemplate")
public class DataSourceMapServerConfig {


	 // 配置数据源
  @Bean(name = "mapserverDataSource")
  public DataSource testDataSource(DBConfigMapserver testConfig) throws SQLException {
      //Atomikos统一管理分布式事务
      AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();

      //用druidXADataSource方式或者上面的Properties方式都可以
      DruidXADataSource druidXADataSource = new DruidXADataSource();
      druidXADataSource.setUrl(testConfig.getUrl());
      druidXADataSource.setUsername(testConfig.getUsername());
      druidXADataSource.setPassword(testConfig.getPassword());
      
      xaDataSource.setUniqueResourceName("mysql-mapserver");
      xaDataSource.setXaDataSource(druidXADataSource);
      xaDataSource.setXaDataSourceClassName("com.alibaba.druid.pool.xa.DruidXADataSource");
      xaDataSource.setMaxLifetime(testConfig.getMaxLifetime());
      xaDataSource.setMinPoolSize(testConfig.getMinPoolSize());
      xaDataSource.setMaxPoolSize(testConfig.getMaxPoolSize());
      xaDataSource.setBorrowConnectionTimeout(testConfig.getBorrowConnectionTimeout());
      xaDataSource.setLoginTimeout(testConfig.getLoginTimeout());
      xaDataSource.setMaintenanceInterval(testConfig.getMaintenanceInterval());
      xaDataSource.setMaxIdleTime(testConfig.getMaxIdleTime());
      xaDataSource.setTestQuery(testConfig.getTestQuery());
      
      //LOG.info("分布式事物dataSource1实例化成功");
      return xaDataSource;
  }

  @Bean(name = "mapserverSqlSessionFactory")
  public SqlSessionFactory testSqlSessionFactory(@Qualifier("mapserverDataSource") DataSource dataSource)
          throws Exception {
      SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
      bean.setDataSource(dataSource);
      bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/mapserver/*.xml"));
      return bean.getObject();
  }

  @Bean(name = "mapserverSqlSessionTemplate")
  public SqlSessionTemplate testSqlSessionTemplate(
          @Qualifier("mapserverSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
      return new SqlSessionTemplate(sqlSessionFactory);
  }
	
  
  
	/*
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
    }*/

}
