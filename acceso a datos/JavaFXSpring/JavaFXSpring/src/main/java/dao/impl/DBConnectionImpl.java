package dao.impl;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import config.Configuration;
import dao.DBConnection;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.extern.log4j.Log4j2;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Log4j2
@Singleton
public class DBConnectionImpl implements DBConnection {

    private final Configuration config;
    private final DataSource hikariDataSource;

    @Inject
    public DBConnectionImpl(Configuration config) {
        this.config = config;
        hikariDataSource = getHikariPool();
    }

    private DataSource getHikariPool() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(config.getProperty("urlDB"));
        hikariConfig.setUsername(config.getProperty("user_name"));
        hikariConfig.setPassword(config.getProperty("password"));
        hikariConfig.setDriverClassName(config.getProperty("driver"));
        hikariConfig.setMaximumPoolSize(4);

        hikariConfig.setIdleTimeout(3000);
        hikariConfig.setConnectionTimeout(3000);
        hikariConfig.setMaxLifetime(3000);
        hikariConfig.addDataSourceProperty("cachePrepStmts", true);
        hikariConfig.addDataSourceProperty("prepStmtCacheSize", 250);
        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);

        return new HikariDataSource(hikariConfig);
    }


    @Override
    public DataSource getDataSource() {
        return hikariDataSource;
    }

    @Override
    public void closeConnection(Connection connArg) {
        log.info("Closing connection to database");
        try {
            if (connArg != null) {
                connArg.close();
            }
        } catch (SQLException sqle) {
           log.error("Error closing connection to database", sqle);
        }
    }

    @Override
    @PreDestroy
    public void closePool() {
        log.info("Closing connection pool");
        ((HikariDataSource) hikariDataSource).close();
    }

}
