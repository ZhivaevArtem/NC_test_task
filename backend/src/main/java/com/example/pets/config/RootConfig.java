package com.example.pets.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.example.pets.repositories"})
@PropertySource(value = "classpath:/persistence.properties")
public class RootConfig {

    @Value("${datasource.username}")
    private String username;
    @Value("${datasource.password}")
    private String password;
    @Value("${datasource.url}")
    private String url;
    @Value("${datasource.driver_path}")
    private String driverPath;

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setUsername(username);
        ds.setPassword(password);
        ds.setUrl(url);
        ds.setDriverClassName(driverPath);
        return ds;
    }

    @Bean
    public HibernateJpaVendorAdapter jpaVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource());
        emf.setJpaVendorAdapter(jpaVendorAdapter());
        emf.setPackagesToScan("com.example.pets.models");

        return emf;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager tm = new JpaTransactionManager();
        LocalContainerEntityManagerFactoryBean emf = entityManagerFactory();
        tm.setEntityManagerFactory(emf.getObject());

        return tm;
    }

    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setPackagesToScan("com.example.pets.models");
        factoryBean.setDataSource(dataSource());

        Properties props = new Properties();
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("persistence.properties")) {
            if (is != null) {
                props.load(is);
                factoryBean.setHibernateProperties(filterProperties(props, "hibernate"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return factoryBean;
    }

    private Properties filterProperties(Properties properties, String prefix) {
        Properties props = new Properties();
        properties.stringPropertyNames().forEach(s -> {
            if (s.startsWith(prefix)) {
                props.put(s, properties.getProperty(s));
            }
        });
        return props;
    }
}
