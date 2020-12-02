package com.jxlt.udic.riskcontrol.website.config;


import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

/**
 * 在使用jndi连接的时候开启此类注释部分，并将DruidConfig全部内容注释掉
 * @author Administrator
 *
 */
@Slf4j
@Configuration
public class DataSourceConfig{

    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceConfig.class);

	@Value("${use-jndi}")
	private boolean useJndi;

	@Value("${spring.datasource.jndi-name}")
	private String jndiName;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.initialSize}")
    private int initialSize;
    @Value("${spring.datasource.minIdle}")
    private int minIdle;
    @Value("${spring.datasource.maxActive}")
    private int maxActive;
    @Value("${spring.datasource.maxWait}")
    private int maxWait;
    @Value("${spring.datasource.validationQuery}")
    private String validationQuery;
    @Value("${spring.datasource.testOnBorrow}")
    private boolean testOnBorrow;
    @Value("${spring.datasource.testOnReturn}")
    private boolean testOnReturn;
    @Value("${spring.datasource.testWhileIdle}")
    private boolean testWhileIdle;
    @Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;
    @Value("${spring.datasource.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;
    @Value("${spring.datasource.removeAbandoned}")
    private boolean removeAbandoned;
    @Value("${spring.datasource.removeAbandonedTimeout}")
    private int removeAbandonedTimeout;
    @Value("${spring.datasource.logAbandoned}")
    private boolean logAbandoned;
    @Value("${spring.datasource.filters}")
    private String filters;
    @Value("${spring.datasource.poolPreparedStatements}")
    private boolean poolPreparedStatements;
    @Value("${spring.datasource.maxPoolPreparedStatementPerConnectionSize}")
    private int maxPoolPreparedStatementPerConnectionSize;

	@Bean
	@Primary //该注解表示在同一个接口有多个实现类可以注入的时候，默认选择哪一个，而不是让autowire注解报错，官网要求当多个数据源时，必须指定一个datasource，另一个datasource则不用添加。
	public DataSource dataSource() {

        if (useJndi) {
            log.info("使用jndi数据源");
            return jndiSource();
        } else {
            log.info("使用普通druid数据源");
            return normalDataSource();
        }

	}

    public DataSource jndiSource() {
            JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
            dataSourceLookup.setResourceRef(true);
            return dataSourceLookup.getDataSource(jndiName);
    }

    /**
     * 正常的dataSource
     * 开发、测试用
     * @return
     */
    public DataSource normalDataSource() {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setDriverClassName(driverClassName);
        datasource.setUrl(url);
        datasource.setUsername(username);
        datasource.setPassword(password);
        //其它配置
        datasource.setInitialSize(initialSize);
        datasource.setMinIdle(minIdle);
        datasource.setMaxActive(maxActive);
        datasource.setMaxWait(maxWait);
        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        //        datasource.setValidationQuery(validationQuery);
        //        datasource.setTestWhileIdle(testWhileIdle);
        //        datasource.setTestOnBorrow(testOnBorrow);
        //        datasource.setTestOnReturn(testOnReturn);
        datasource.setPoolPreparedStatements(poolPreparedStatements);
        datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        //        try {
        //            datasource.setFilters(filters);
        //        } catch (SQLException e) {
        //            LOGGER.error("druid configuration initialization filter", e);
        //        }
        return datasource;
    }
}
