package com.my.gn.configuration.database;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = {"com.my.gn.manage.**.dao"})
public class ContextSqlMapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ContextSqlMapper.class);
	
	ContextSqlMapper(){
		LOGGER.info("================ ContextSqlMapper Load ================");
	}
	
	@Autowired
    private ApplicationContext applicationContext;
	
	@Autowired
	DataSource dataSource;

    /**
     * <pre>
     * 1. 개요 : sqlSe
     * 2. 처리내용 :
     * </pre>
     *	@since 2019. 8. 8.
     * @return
     * @throws Exception
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
    	/**
    	 * SqlSessionFactoryBean 는 SqlSessionFactory 를 만들기 위해 사용 되어짐.
    	 * setDataSource : JDBC DataSource가 필수로 필요함.
    	 * setConfigLocation : xml설정파일 위치
    	 * setMapperLocations :  mapper 파일 위치
    	 **/
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:/mybatis/config/mybatis-config.xml"));
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/mybatis/mapper/**/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }
    
    
    @Bean
    public SqlSession sqlSession() throws Exception {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
        return sqlSessionTemplate;
    }
    
    
    /**
     * <pre>
     * 1. 개요 : @mapper 사용하기 위해
     * 2. 처리내용 :
     * </pre>
     *	@since 2020. 3. 4.
     * @return
     * @throws Exception
     */
//    @Bean
//    public MapperScannerConfigurer  mapperScannerConfigurer() throws Exception{
//    	MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
//    	mapperScannerConfigurer.setBasePackage("com.my.gn.manage.**.dao");
//    	return mapperScannerConfigurer;
//    }
//    
    
	
}
