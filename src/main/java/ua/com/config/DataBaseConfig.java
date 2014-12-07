package ua.com.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:hibernate.properties")
public class DataBaseConfig {

    @Value( "${hibernate.connection.driver_class}")
    private String driverClass;

    @Value( "${hibernate.connection.url}")
    private String url;

    @Value( "${hibernate.connection.password}")
    private String password;

    @Value( "${hibernate.connection.username}")
    private String username;

    @Bean(name="dataSource")
    public DataSource dataSource() {
        DataSource ds = new DataSource();
        ds.setDriverClassName(driverClass);
        ds.setUsername(username);
        ds.setPassword(password);
        ds.setUrl(url);
        return ds;
    }

}
