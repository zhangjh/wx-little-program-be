package me.zhangjh.wx.program.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @author zhangjh451@midea.com
 * @date 6:00 PM 2023/2/22
 * @Description
 */
@Configuration
public class DataSourceConfig {

    @Primary
    @Bean("chatGptDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.chatgpt")
    public DataSource chatGptDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean("wxDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.wx")
    public DataSource wxDataSource() {
        return DataSourceBuilder.create().build();
    }
}
