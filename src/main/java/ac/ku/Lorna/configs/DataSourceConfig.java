package ac.ku.Lorna.configs;



import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class DataSourceConfig {

    private static final HikariConfig hikariConfig = new HikariConfig();
private static final Logger LOGGER  =Logger.getLogger(DataSourceConfig.class.getName());
    static {
        try (InputStream input = DataSourceConfig.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (input == null) {
       LOGGER.severe("Unable to find db.properties");
                throw new RuntimeException("db.properties not found");
            }

            Properties prop = new Properties();
            prop.load(input);

            hikariConfig.setJdbcUrl(prop.getProperty("db.url"));
            hikariConfig.setUsername(prop.getProperty("db.username"));
            hikariConfig.setPassword(prop.getProperty("db.password"));
            hikariConfig.setMaximumPoolSize(Integer.parseInt(prop.getProperty("maximumPoolSize")));
            hikariConfig.setMinimumIdle(Integer.parseInt(prop.getProperty("minimumIdle")));
            hikariConfig.setIdleTimeout(Long.parseLong(prop.getProperty("idleTimeout")));
            hikariConfig.setConnectionTimeout(Long.parseLong(prop.getProperty("connectionTimeout")));
            hikariConfig.setPoolName(prop.getProperty("poolName"));
        } catch (Exception ex) {
            LOGGER.severe("Error loading database configuration: " + ex.getMessage());
            throw new RuntimeException("Failed to configure data source", ex);
        }
    }

    public static DataSource getDataSource() {
        return new HikariDataSource(hikariConfig);
}
}
