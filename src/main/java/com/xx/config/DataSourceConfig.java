package com.xx.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by jishu04 on 2016/7/27.
 */
@Configuration
@EnableTransactionManagement
public class DataSourceConfig implements EnvironmentAware {

    private static Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

    private RelaxedPropertyResolver propertyResolver;

    @Override
    public void setEnvironment(Environment env) {
        propertyResolver = new RelaxedPropertyResolver(env, "spring.datasource.");
    }

    @Bean
    public WallFilter wallFilter() {
        WallConfig wallConfig = new WallConfig();
        wallConfig.setStrictSyntaxCheck(false);
        wallConfig.setCommentAllow(true);

        WallFilter wallFilter = new WallFilter();
        wallFilter.setConfig(wallConfig);

        return wallFilter;
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource(WallFilter wallFilter) {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(propertyResolver.getProperty("url"));
        datasource.setDriverClassName(propertyResolver.getProperty("driverClassName"));
        datasource.setUsername(propertyResolver.getProperty("username"));
        datasource.setPassword(propertyResolver.getProperty("password"));

        datasource.setInitialSize(propertyResolver.getProperty("initialSize", int.class, 5));
        datasource.setMinIdle(propertyResolver.getProperty("minIdle", int.class, 5));
        datasource.setMaxActive(propertyResolver.getProperty("maxActive", int.class, 20));
        datasource.setMaxWait(propertyResolver.getProperty("maxWait", int.class, 60000));
        datasource.setTimeBetweenEvictionRunsMillis(propertyResolver.getProperty("timeBetweenEvictionRunsMillis", int.class, 60000));
        datasource.setMinEvictableIdleTimeMillis(propertyResolver.getProperty("minEvictableIdleTimeMillis", int.class, 300000));
        datasource.setValidationQuery(propertyResolver.getProperty("validationQuery", String.class, "SELECT 1 FROM DUAL"));
        datasource.setTestWhileIdle(propertyResolver.getProperty("testWhileIdle", boolean.class, true));
        datasource.setTestOnBorrow(propertyResolver.getProperty("testOnBorrow", boolean.class, false));
        datasource.setTestOnReturn(propertyResolver.getProperty("testOnReturn", boolean.class, false));
        datasource.setPoolPreparedStatements(propertyResolver.getProperty("poolPreparedStatements", boolean.class, true));
        datasource.setMaxPoolPreparedStatementPerConnectionSize(propertyResolver.getProperty("maxPoolPreparedStatementPerConnectionSize", int.class, 20));
        datasource.setConnectProperties(propertyResolver.getProperty("connectionProperties", Properties.class, null));

        List<Filter> filters = new ArrayList<Filter>();
        filters.add(wallFilter);
        datasource.setProxyFilters(filters);

        try {
            datasource.setFilters(propertyResolver.getProperty("filters", String.class, "stat,wall,log4j"));
        } catch (SQLException ex) {
            logger.error(ex.getMessage(), ex);
        }

        return datasource;
    }
}
