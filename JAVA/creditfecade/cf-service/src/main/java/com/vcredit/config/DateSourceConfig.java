package com.vcredit.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @Author chenyubin
 * @Date 2018/7/2
 * @Description 系统数据源配置类
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "com.vcredit.persistence.mapper")
public class DateSourceConfig {

    @Value("${datasource.url}")
    private String dbUrl;
    @Value("${datasource.username}")
    private String username;
    @Value("${datasource.password}")
    private String password;

    private static final String driverClassName = "com.mysql.jdbc.Driver";//"com.mysql.cj.jdbc.Driver";
    private static final String validationQuery = "SELECT 1 FROM DUAL";
    private static final int initialSize = 10;
    private static final int minIdle = 10;
    private static final int maxActive = 50;
    private static final int maxWait = 60000;
    private static final int timeBetweenEvictionRunsMillis = 60000;
    private static final int minEvictableIdleTimeMillis = 60000;
    private static final boolean testWhileIdle = true;
    private static final boolean testOnBorrow = false;
    private static final boolean testOnReturn = false;
    private static final boolean poolPreparedStatements = true;
    private static final int maxPoolPreparedStatementPerConnectionSize = 20;
    private static final String connectionProperties = "druid.stat.mergeSql=true;druid.stat.slowSqlMillis=500";
    private static final boolean useGlobalDataSourceStat = true;

    private String filters;

    @Bean
    @Primary
    public DataSource dataSource(){
        //基础配置
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(this.dbUrl);
        dataSource.setUsername(this.username);
        dataSource.setPassword(this.password);
        dataSource.setDriverClassName(driverClassName);
        //池配置
        dataSource.setInitialSize(initialSize);
        dataSource.setMinIdle(minIdle);
        dataSource.setMaxActive(maxActive);
        dataSource.setMaxWait(maxWait);
        dataSource.setTimeBetweenEvictionRunsMillis(this.timeBetweenEvictionRunsMillis);
        dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        dataSource.setValidationQuery(validationQuery);
        dataSource.setTestWhileIdle(testWhileIdle);
        dataSource.setTestOnBorrow(testOnBorrow);
        dataSource.setTestOnReturn(testOnReturn);
        dataSource.setPoolPreparedStatements(poolPreparedStatements);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        dataSource.setUseGlobalDataSourceStat(useGlobalDataSourceStat);
        dataSource.setConnectionProperties(connectionProperties);
        return dataSource;
    }

    //注释，以采用 MybatisAutoConfiguration
    /*@Autowired
    private ResourcePatternResolver resolver;
    @Autowired
    private ApplicationContext context;

    @Bean
    @Primary
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(context.getBean(DataSource.class));
        //ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setMapperLocations(resolver.getResources("classpath:com/vcredit/persistence/*Mapper.xml"));
        return sessionFactory.getObject();
    }*/
}
