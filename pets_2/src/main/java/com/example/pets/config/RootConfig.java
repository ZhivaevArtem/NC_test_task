package com.example.pets.config;

import com.example.pets.models.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class RootConfig {

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setPassword("3664");
        ds.setUsername("postgres");
        ds.setUrl("jdbc:postgresql://localhost:5432/nc_project_db");

        ds.setDriverClassName("org.postgresql.Driver");
        return  ds;
    }

    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setPackagesToScan("com.example.pets.models");
        factoryBean.setDataSource(dataSource());
        Properties props = new Properties();
        props.setProperty("hibernate.show_sql", "true");
        factoryBean.setHibernateProperties(props);
        return factoryBean;
    }
}
