package com.petproject.config;

import jakarta.annotation.Resource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories("com.petproject.repository.")
@PropertySource("application.properties")
@EnableTransactionManagement
@ComponentScan("com.petproject.")
public class MyConfiguration {
    private static final String PROP_DATABASE_DRIVER = "spring.datasource.driver-class-name";
    public static final String PROP_DATABASE_URL = "spring.datasource.url";
    public static final String PROP_DATABASE_USERNAME = "spring.datasource.username";
    public static final String PROP_DATABASE_PASSWORD = "spring.datasource.password";

    public static final String PROP_HIBERNATE_DIALECT = "spring.jpa.properties.hibenate.dialect";
    public static final String PROP_HIBERNATE_SHOW_SQL = "spring.jpa.show-sql";
    public static final String PROP_HIBERNATE_HBM2DDL_AUTO = "spring.jpa.hibernate.ddl-auto";
    public static final String PROP_HIBERNATE_FORMAT_SQL = "spring.jpa.properties.hibernate.format-sql";

    public static final String PROP_ENTITYMANAGER_PACKAGES_TO_SCAN = "spring.jpa.entitymanager.packages-to-scan";
    public static final String PROP_WEB_APPLICATION_TYPE = "spring.main.web-application-type";

    @Resource
    Environment environment;

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(environment.getRequiredProperty(PROP_DATABASE_DRIVER));
        dataSource.setUrl(environment.getRequiredProperty(PROP_DATABASE_URL));
        dataSource.setUsername(environment.getRequiredProperty(PROP_DATABASE_USERNAME));
        dataSource.setPassword(environment.getRequiredProperty(PROP_DATABASE_PASSWORD));

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();

        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactoryBean.setPackagesToScan(environment.getRequiredProperty(PROP_ENTITYMANAGER_PACKAGES_TO_SCAN));
        entityManagerFactoryBean.setJpaProperties(getHibernateProperties());

        return entityManagerFactoryBean;
    }

    @Bean
    public JpaTransactionManager transactionManager(){
        JpaTransactionManager transactionManager = new JpaTransactionManager();

        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }

    @Bean
    public Properties getHibernateProperties(){
        Properties properties = new Properties();

        properties.put(PROP_HIBERNATE_DIALECT, environment.getRequiredProperty(PROP_HIBERNATE_DIALECT));
        properties.put(PROP_HIBERNATE_FORMAT_SQL, environment.getRequiredProperty(PROP_HIBERNATE_FORMAT_SQL));
        properties.put(PROP_HIBERNATE_HBM2DDL_AUTO, environment.getRequiredProperty(PROP_HIBERNATE_HBM2DDL_AUTO));
        properties.put(PROP_HIBERNATE_SHOW_SQL, environment.getRequiredProperty(PROP_HIBERNATE_SHOW_SQL));

        return properties;
    }
}
