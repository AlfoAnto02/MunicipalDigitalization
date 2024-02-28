package it.cs.unicam.MunicipalDigitalization.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;

/**
 * This class represent the initial DBConfig for the SQLite Database.
 */

@Configuration
@EnableJpaRepositories(basePackages = "it.cs.unicam.MunicipalDigitalization.db.Repository")
public class DbConfig {

    /**
     * Environment of the Application. With this object is possible to access to the application.properties file
     */
    @Autowired
    private Environment env;

    /**
     * This Method will return the Datasource with the properties that have been set in the application.properties
     * file in resources.
     *
     * @return the DataSource
     */
    @Bean
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Objects.requireNonNull(env.getProperty("driverClassName")));
        dataSource.setUrl(env.getProperty("url"));
        dataSource.setUsername(env.getProperty("user"));
        dataSource.setPassword(env.getProperty("password"));
        return dataSource;
    }

    /**
     * This method is used to set the EntityContainer of the Project. It needs a Package to scan that is the Package
     * where all Models entities are stored.
     *
     * @return the EntityContainer
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("it.cs.unicam.MunicipalDigitalization.api.model");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.setJpaProperties(additionalProperties());
        return em;
    }

    /**
     * This method is used the set more Properties about the Database such as the dialect and showsql
     *
     * @return the New properties
     */
    final Properties additionalProperties() {
        final Properties hibernateProperties = new Properties();
        if (env.getProperty("hibernate.hbm2ddl.auto") != null) {
            hibernateProperties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        }
        if (env.getProperty("hibernate.dialect") != null) {
            hibernateProperties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
        }
        if (env.getProperty("hibernate.show_sql") != null) {
            hibernateProperties.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        }
        return hibernateProperties;
    }
}

