package br.com.spring_batch.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {

    private static final String DRIVERNAME  = "dataSource.driverClassName";
    private static final String URL  = "dataSource.url";
    private static final String USERNAME  = "dataSource.username";
    private static final String PASSWORD  = "dataSource.password";


    private final Environment environment;

    @Autowired
    public DataSourceConfiguration(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public DataSource dataSource (){
        DriverManagerDataSource datasource = new DriverManagerDataSource();
        datasource.setDriverClassName(environment.getProperty(DRIVERNAME));
        datasource.setUrl(environment.getProperty(URL));
        datasource.setUsername(environment.getProperty(USERNAME));
        datasource.setPassword(environment.getProperty(PASSWORD));

        return datasource;
    }
}
