package com.my.gn.configuration.database;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import net.sf.log4jdbc.Log4jdbcProxyDataSource;
import net.sf.log4jdbc.tools.Log4JdbcCustomFormatter;
import net.sf.log4jdbc.tools.LoggingType;

/**
 * @Class ContextDataBase.java
 *	@Description ContextDataBase
 * <pre>
 *
 *      수정일                  수정자                   수정내용
 * --------------  -------     ---------------------
 *  2019. 8. 8.          hgb             최초작성
 *
 * </pre>
 * @author hgb
 * @version 0.0  
 * @see
 *
 */
@Configuration 
@EnableTransactionManagement
public class ContextDataBase {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ContextDataBase.class);
	
	ContextDataBase(){
		LOGGER.info("================ ContextDataBase Load ================");
	}

	@Bean(destroyMethod="close")//destroyMethod는 어플리케이션 컨텍스트가 종료 되었을때 close() 해줌
	public DataSource dataSourceSpied() {
		BasicDataSource dataSourceSpied = new BasicDataSource();
		try {
			dataSourceSpied.setDriverClassName("com.mysql.cj.jdbc.Driver");
			dataSourceSpied.setUrl("jdbc:mysql://106.10.49.23:3306/GN?serverTimezone=UTC&useSSL=false");
			dataSourceSpied.setUsername("GN");
			dataSourceSpied.setPassword("GN1234");
			//dataSource.setDefaultAutoCommit(false);
		} catch (Exception e) {
			LOGGER.info("JDBC Driver error");
		}
		
		return dataSourceSpied;
	}
	
	@Bean
	public Log4jdbcProxyDataSource dataSource() {
		Log4jdbcProxyDataSource logDatasource = new Log4jdbcProxyDataSource(dataSourceSpied());
		try {
			Log4JdbcCustomFormatter customFormatter = new Log4JdbcCustomFormatter();
			customFormatter.setLoggingType(LoggingType.MULTI_LINE);
			customFormatter.setSqlPrefix("SQL  : ");
			
			logDatasource.setLogFormatter(customFormatter);
		} catch (Exception e) {
			LOGGER.info("JDBC Driver error");
		}
		
		return logDatasource;
	}

	/**
	 * <pre>
	 * 1. 개요 : 트랜잭션 등록
	 * 2. 처리내용 :
	 * </pre>
	 *	@since 2019. 8. 8.
	 * @return
	 */
	@Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }





}
