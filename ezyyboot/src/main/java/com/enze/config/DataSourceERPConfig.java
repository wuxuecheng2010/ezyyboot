package com.enze.config;

import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.alibaba.druid.pool.xa.DruidXADataSource;

/**
 * Created by summer on 2016/11/25.
 */
@Configuration
@MapperScan(basePackages = "com.enze.dao.mapper.erp", sqlSessionTemplateRef  = "erpSqlSessionTemplate")
public class DataSourceERPConfig {
	
	 // 配置数据源
    @Bean(name = "erpDataSource")
    public DataSource testDataSource(DBConfigERP testConfig) throws SQLException {
        //Atomikos统一管理分布式事务
        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();

        //用druidXADataSource方式或者上面的Properties方式都可以
        DruidXADataSource druidXADataSource = new DruidXADataSource();
        druidXADataSource.setUrl(testConfig.getUrl());
        druidXADataSource.setUsername(testConfig.getUsername());
        druidXADataSource.setPassword(testConfig.getPassword());
        
        xaDataSource.setUniqueResourceName("oracle-erp");
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

    @Bean(name = "erpSqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("erpDataSource") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/erp/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "erpSqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(
            @Qualifier("erpSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }


    /*@Bean(name = "erpDataSource")
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
    }*/

}
