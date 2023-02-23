package me.zhangjh.wx.program.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @author zhangjh451@midea.com
 * @date 6:18 PM 2023/2/22
 * @Description
 */
@Configuration
@MapperScan(basePackages = "me.zhangjh.wx.program.mapper.chatgpt", sqlSessionFactoryRef = "chatGptSelSessionFactory")
public class MybatisConfig {

    @Primary
    @Bean(name = "chatGptSelSessionFactory")
    public SqlSessionFactory chatGptSelSessionFactory(@Qualifier("chatGptDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);

        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        sqlSessionFactoryBean.setConfiguration(configuration);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/chatgpt/*.xml"));

        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "pinyinSelSessionFactory")
    public SqlSessionFactory pinyinSelSessionFactory(@Qualifier("pinyinDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        sqlSessionFactoryBean.setConfiguration(configuration);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/pinyin/*.xml"));

        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public MapperScannerConfigurer chatGptMapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("chatGptSelSessionFactory");
        mapperScannerConfigurer.setBasePackage("me.zhangjh.wx.program.mapper.chatgpt");
        return mapperScannerConfigurer;
    }

    @Bean
    public MapperScannerConfigurer pinyinMapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("pinyinSelSessionFactory");
        mapperScannerConfigurer.setBasePackage("me.zhangjh.wx.program.mapper.pinyin");
        return mapperScannerConfigurer;
    }
}
