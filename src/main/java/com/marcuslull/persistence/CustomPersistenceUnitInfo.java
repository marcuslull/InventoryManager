package com.marcuslull.persistence;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.SharedCacheMode;
import jakarta.persistence.ValidationMode;
import jakarta.persistence.spi.ClassTransformer;
import jakarta.persistence.spi.PersistenceUnitInfo;
import jakarta.persistence.spi.PersistenceUnitTransactionType;
import org.hibernate.cfg.AvailableSettings;

import javax.sql.DataSource;
import java.net.URL;
import java.util.List;
import java.util.Properties;

/**
 * Custom implementation of the {@link PersistenceUnitInfo} interface used to provide
 * metadata about a persistence unit.
 * <p>
 * This class specifies configuration details for the JPA persistence unit such as the
 * persistence unit name, persistence provider class, data source class, server details,
 * authentication information, and database connection properties.
 * </p>
 */
public class CustomPersistenceUnitInfo implements PersistenceUnitInfo {

    /**
     * The name of the persistence unit used in the current configuration.
     * This variable holds the identifier for the specific persistence unit defined
     * within the application, enabling the entity manager to locate and manage
     * the corresponding set of entity classes and database connections.
     */
    public static final String PERSISTENCE_UNIT_NAME = "customPersistenceUnit";

    /**
     * Defines the class name of the persistence provider for the current
     * persistence unit configuration. This is used to specify which
     * JPA (Java Persistence API) implementation is to be utilized.
     * In this configuration, "org.hibernate.jpa.HibernatePersistenceProvider"
     * is set, which indicates the usage of Hibernate as the JPA provider.
     */
    public static final String PERSISTENCE_PROVIDER = "org.hibernate.jpa.HibernatePersistenceProvider";

    /**
     * Represents the fully-qualified class name of the DataSource implementation
     * used for establishing connections to the PostgreSQL database in the
     * persistence unit configuration.
     * This is typically required when configuring a custom DataSource for JPA.
     */
    public static final String DATASOURCE_CLASS = "org.postgresql.ds.PGSimpleDataSource";

    /**
     * Specifies the name of the server where the database is hosted.
     * This is used in configuration settings to establish a connection
     * to the database server.
     */
    public static final String SERVER_NAME = "localhost";

    /**
     * Specifies the default port number on which the database server is expected to be running.
     * This value is used in the configuration of the data source for connecting to the database.
     */
    public static final String PORT_NUMBER = "5432";

    /**
     * The name of the database used for the inventory management system.
     * This constant holds the identifier for the target database
     * that the application interacts with.
     */
    public static final String DB_NAME = "Inventory_Manager";

    /**
     * The database username for accessing the configured data source.
     * This value is retrieved from the environment variable "DB_USER".
     */
    public static final String DB_USERNAME = System.getenv("DB_USER");

    /**
     * The password used for database authentication.
     * This variable is loaded from the environment variable "DB_PASS".
     */
    public static final String DB_PASSWORD = System.getenv("DB_PASS");

    /**
     * Specifies the maximum lifetime in milliseconds for a connection in the connection pool.
     * This value determines how long a connection can remain open before being closed
     * and retired from the pool. A connection that exceeds this lifetime will be removed,
     * ensuring that stale or long-lived connections do not linger and potentially cause
     * resource issues.
     */
    public static final Integer DB_CONNECTION_MAX_LIFETIME = 300000; // 5 Minutes

    /**
     * A constant list of fully-qualified class names representing the entity classes
     * managed by the persistence unit.
     */
    public static final List<String> MANAGED_CLASSES = List.of("com.marcuslull.entities.Product");

    /**
     * A configuration setting to enable or disable the display of SQL statements
     * in the console or logs for debugging purposes.
     * When set to "true", SQL statements executed by the persistence provider are
     * output to the console or logs, which can be useful for diagnostic monitoring.
     * Note: This setting should be used with caution in production environments
     * as it may expose sensitive information and affect performance.
     */
    public static final String HIBERNATE_SHOW_SQL = "true";


    /**
     * This constant determines whether SQL statements should be formatted.
     * When set to "true", SQL statements will be formatted for better readability.
     */
    public static final String HIBERNATE_FORMAT_SQL = "true";


    /**
     * A constant indicating whether SQL highlighting is enabled for logging and debugging purposes.
     * Set to "true" to enable syntax highlighting for SQL statements.
     */
    public static final String HIBERNATE_HIGHLIGHT_SQL = "true";


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
     * Retrieves the JTA-enabled DataSource for the current persistence unit configuration.
     *
     * @return a DataSource object configured for JTA transactions
     */
    @Override
    public DataSource getJtaDataSource() {
        Properties properties = new Properties(); // properties for the Hikari connection
        properties.setProperty("dataSourceClassName", DATASOURCE_CLASS);
        properties.setProperty("dataSource.serverName", SERVER_NAME);
        properties.setProperty("dataSource.portNumber", PORT_NUMBER);
        properties.setProperty("dataSource.databaseName", DB_NAME);
        properties.setProperty("dataSource.user", DB_USERNAME);
        properties.setProperty("dataSource.password", DB_PASSWORD);

        HikariConfig hikariConfig = new HikariConfig(properties); // properties for the Hikari pool
        hikariConfig.setMaxLifetime(DB_CONNECTION_MAX_LIFETIME);

        return new HikariDataSource(hikariConfig);
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

    /**
     * Retrieves the list of managed class names for the persistence unit.
     *
     * @return a list of class names managed by the persistence unit
     */
    @Override
    public List<String> getManagedClassNames() {
        return MANAGED_CLASSES;
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
     * Retrieves the properties for Hibernate configuration including SQL display, formatting,
     * and highlighting settings. Available settings can be found in the {@link AvailableSettings} class.
     *
     * @return a Properties object containing Hibernate configuration properties
     */
    @Override
    public Properties getProperties() {
        Properties properties = new Properties(); // properties for Hibernate
        properties.setProperty(AvailableSettings.SHOW_SQL, HIBERNATE_SHOW_SQL);
        properties.setProperty(AvailableSettings.FORMAT_SQL, HIBERNATE_FORMAT_SQL);
        properties.setProperty(AvailableSettings.HIGHLIGHT_SQL, HIBERNATE_HIGHLIGHT_SQL);
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
