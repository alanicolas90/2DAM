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
import java.util.logging.Level;
import java.util.logging.Logger;

@Log4j2
@Singleton
public class DBConnectionImpl implements DBConnection {

    private final Configuration config;
    private DataSource hikariDataSource = null;

    @Inject
    public DBConnectionImpl(Configuration config) {
        this.config = config;
    }

    private DataSource getHikariPool() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(config.getRuta());
        hikariConfig.setUsername(config.getUser());
        hikariConfig.setPassword(config.getPassword());
        hikariConfig.setDriverClassName(config.getDriver());
        hikariConfig.setMaximumPoolSize(2);

        hikariConfig.setIdleTimeout(3000);
        hikariConfig.setConnectionTimeout(3000);
        hikariConfig.setMaxLifetime(3000);
        hikariConfig.addDataSourceProperty("cachePrepStmts", true);
        hikariConfig.addDataSourceProperty("prepStmtCacheSize", 250);
        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);

        return new HikariDataSource(hikariConfig);
    }


    @Override
    public Connection getConnection() throws SQLException {
        Connection conn = hikariDataSource.getConnection();
        log.info("Connection to database established");
        return conn;
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
                connArg.setAutoCommit(true);
                connArg.close();
            }
        } catch (SQLException sqle) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, sqle);
        }
    }

    @Override
    public void loadPool() {
        hikariDataSource = getHikariPool();
    }

    @Override
    @PreDestroy
    public void closePool() {
        log.info("Closing connection pool");
        ((HikariDataSource) hikariDataSource).close();
    }

}
