package com.jxlt.udic.riskcontrol.website.config;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

 
//@Configuration
public class MyBatisConfig{
	private static Logger logger = LoggerFactory.getLogger(MyBatisConfig.class);
	
//	@Value("${mybatis.config-location}")  
    private String configLocation;
	
	@Value("${mybatis.mapper-locations}")  
    private String mapperLocations;
	
	@Value("${mybatis.type-aliases-package}")  
    private String typeAliasesPackage;

	@Resource
	private DataSource dataSource;

	@Bean
	public SqlSessionFactory sqlSessionFactoryBean() {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);

		bean.setTypeAliasesPackage(typeAliasesPackage);
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		try {
			bean.setConfigLocation(resolver.getResource(configLocation));
			bean.setMapperLocations(resolver.getResources(mapperLocations));
			SqlSessionFactory sessionFactory = bean.getObject();
			return sessionFactory;
		} catch (Exception e) {
			logger.error("sqlSessionFactory注入失败：" + e.getMessage());
			throw new RuntimeException("sqlSessionFactory init fail", e);
		}
	}
}