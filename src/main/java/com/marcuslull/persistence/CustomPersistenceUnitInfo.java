package com.marcuslull.persistence;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.SharedCacheMode;
import jakarta.persistence.ValidationMode;
import jakarta.persistence.spi.ClassTransformer;
import jakarta.persistence.spi.PersistenceUnitInfo;
import jakarta.persistence.spi.PersistenceUnitTransactionType;

import javax.sql.DataSource;
import java.net.URL;
import java.util.List;
import java.util.Properties;

public class CustomPersistenceUnitInfo implements PersistenceUnitInfo {

    public static final String DIALECT = "org.hibernate.dialect.PostgreSQLDialect";
    public static final String PERSISTENCE_UNIT_NAME = "customPersistenceUnit";
    public static final String PERSISTENCE_PROVIDER = "org.hibernate.jpa.HibernatePersistenceProvider";
    public static final String DB_URL = "jdbc:postgresql://localhost:5432/Inventory_Manager";
    public static final String DB_USERNAME = System.getenv("DB_USER");
    public static final String DB_PASSWORD = System.getenv("DB_PASS");

    /**
     * Retrieves the name of the persistence unit used in the current configuration.
     *
     * @return the name of the persistence unit
     */
    @Override
    public String getPersistenceUnitName() {
        return PERSISTENCE_UNIT_NAME;
    }

    /**
     * Retrieves the class name of the persistence provider for the current
     * persistence unit configuration.
     *
     * @return the class name of the persistence provider
     */
    @Override
    public String getPersistenceProviderClassName() {
        return PERSISTENCE_PROVIDER;
    }

    /**
     * Retrieves the transaction type for the persistence unit, indicating whether
     * transactions are managed by the application server (JTA) or locally
     * (RESOURCE_LOCAL).
     *
     * @return the transaction type of the persistence unit
     */
    @Override
    public PersistenceUnitTransactionType getTransactionType() {
        return PersistenceUnitTransactionType.RESOURCE_LOCAL;
    }

    /**
     * Retrieves the JTA (Java Transaction API) DataSource instance configured for this persistence unit
     * with the necessary database connection details including URL, username, and password.
     *
     * @return a DataSource object configured for JTA transactions
     */
    @Override
    public DataSource getJtaDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(DB_URL);
        dataSource.setUsername(DB_USERNAME);
        dataSource.setPassword(DB_PASSWORD);
        return dataSource;
    }

    @Override
    public DataSource getNonJtaDataSource() {
        return null;
    }

    @Override
    public List<String> getMappingFileNames() {
        return List.of();
    }

    @Override
    public List<URL> getJarFileUrls() {
        return List.of();
    }

    @Override
    public URL getPersistenceUnitRootUrl() {
        return null;
    }

    @Override
    public List<String> getManagedClassNames() {
        return List.of("com.marcuslull.entities.Product");
    }

    @Override
    public boolean excludeUnlistedClasses() {
        return false;
    }

    @Override
    public SharedCacheMode getSharedCacheMode() {
        return null;
    }

    @Override
    public ValidationMode getValidationMode() {
        return null;
    }

    /**
     * Retrieves the Hibernate properties for the persistence unit configuration.
     *
     * @return a Properties object containing the JDBC driver settings
     */
    @Override
    public Properties getProperties() {
        Properties properties = new Properties();
        properties.setProperty("jakarta.persistence.jdbc.driver", DB_URL);
        return properties;
    }

    @Override
    public String getPersistenceXMLSchemaVersion() {
        return "";
    }

    @Override
    public ClassLoader getClassLoader() {
        return null;
    }

    @Override
    public void addTransformer(ClassTransformer transformer) {

    }

    @Override
    public ClassLoader getNewTempClassLoader() {
        return null;
    }
}
