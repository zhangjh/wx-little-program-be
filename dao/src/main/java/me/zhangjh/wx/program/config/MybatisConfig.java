package me.zhangjh.wx.program.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @author njhxzhangjihong@126.com
 * @date 6:18 PM 2023/2/22
 * @Description
 */
@Configuration
public class MybatisConfig {

    @Primary
    @Bean(name = "chatGptSqlSessionFactory")
    public SqlSessionFactory chatGptSqlSessionFactory(@Qualifier("chatGptDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);

        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        sqlSessionFactoryBean.setConfiguration(configuration);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/chatgpt/*.xml"));

        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "pinyinSqlSessionFactory")
    public SqlSessionFactory wxSqlSessionFactory(@Qualifier("wxDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        sqlSessionFactoryBean.setConfiguration(configuration);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/pinyin/*.xml"));

        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "orderSqlSessionFactory")
    public SqlSessionFactory orderSqlSessionFactory(@Qualifier("wxDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        sqlSessionFactoryBean.setConfiguration(configuration);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/order/*.xml"));

        return sqlSessionFactoryBean.getObject();
    }


    @Bean
    public MapperScannerConfigurer chatGptMapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("chatGptSqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("me.zhangjh.wx.program.mapper.chatgpt");
        return mapperScannerConfigurer;
    }

    @Bean
    public MapperScannerConfigurer pinyinMapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("pinyinSqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("me.zhangjh.wx.program.mapper.pinyin");
        return mapperScannerConfigurer;
    }

    @Bean
    public MapperScannerConfigurer orderMapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("orderSqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("me.zhangjh.wx.program.mapper.order");
        return mapperScannerConfigurer;
    }
}
