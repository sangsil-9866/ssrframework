package com.sangsil.ssrframework.global.config;

import com.sangsil.ssrframework.global.util.UtilProperty;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;


@Configuration
@EnableTransactionManagement
@RequiredArgsConstructor
@MapperScan(basePackages = "com.sangsil.ssrframework.**.mapper", annotationClass = Mapper.class)
public class MybatisConfig {

	private final UtilProperty utilProperty;

	@Bean
	public SqlSessionFactoryBean sqlSessionFactoryForMyBatis(DataSource dataSource,
			ApplicationContext applicationContext) throws IOException {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource(utilProperty.getProperty("mybatis.config-location")));
		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources(utilProperty.getProperty("mybatis.mapper-locations")));

		// Type Aliases 패키지 설정 추가
		sqlSessionFactoryBean.setTypeAliasesPackage("com.sangsil.ssrframework.domain");

		return sqlSessionFactoryBean;
	}

	/**
	 * SqlSessionTemplate 빈 생성
	 *
	 *
	 * @param sqlSessionFactory SqlSessionFactory 객체
	 * @return SqlSessionTemplate 인스턴스
	 */
	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory); // SqlSessionTemplate 반환
	}
}
