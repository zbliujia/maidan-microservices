package com.wuwo.maidan.order.db;

import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * Created by jiedaibao on 4/22/16.
 */

@Configuration
public class MybitsConfig {

	@Bean
	@Primary
	@ConfigurationProperties(prefix="spring.datasource.write")
	public DataSource writeDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	@ConfigurationProperties(prefix="spring.datasource.read")
	public DataSource readDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "writeSqlSessionFactory")
	public SqlSessionFactory writeSqlSessionFactory() {
		DataSource dataSource = writeDataSource();
		TransactionFactory transactionFactory = new JdbcTransactionFactory();
		Environment environment = new Environment("write", transactionFactory, dataSource);
		org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration(environment);
		configuration.addMapper(TestMapper.class);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
		return sqlSessionFactory;
	}

	@Bean(name = "readSqlSessionFactory")
	public SqlSessionFactory readSqlSessionFactory() {
		DataSource dataSource = readDataSource();
		TransactionFactory transactionFactory = new JdbcTransactionFactory();
		Environment environment = new Environment("read", transactionFactory, dataSource);
		org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration(environment);
		configuration.addMapper(TestMapper.class);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
		return sqlSessionFactory;
	}

	@Bean(name = "writeSqlSessionTemplate")
	public SqlSessionTemplate writeSqlSessionTemplate() {
		return new SqlSessionTemplate(writeSqlSessionFactory());
	}

	@Bean(name = "readSqlSessionTemplate")
	public SqlSessionTemplate readSqlSessionTemplate() {
		return new SqlSessionTemplate(readSqlSessionFactory());
	}
}
