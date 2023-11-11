package dao.impl;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import config.Configuration;
import dao.DBConnection;
import dao.utils.DaoConstants;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.extern.log4j.Log4j2;

import javax.sql.DataSource;

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
        HikariConfig hikariConfig = getHikariConfig();

        hikariConfig.addDataSourceProperty(DaoConstants.CACHE_PREP_STMTS, true);
        hikariConfig.addDataSourceProperty(DaoConstants.PREP_STMT_CACHE_SIZE, DaoConstants.CACHE_SIZE);
        hikariConfig.addDataSourceProperty(DaoConstants.PREP_STMT_CACHE_SQL_LIMIT, DaoConstants.CACHE_SQLLIMIT);

        return new HikariDataSource(hikariConfig);
    }

    @Override
    public DataSource getDataSource() {
        return hikariDataSource;
    }

    @Override
    public void loadPool() {
        hikariDataSource = getHikariPool();
    }

    @Override
    @PreDestroy
    public void closePool() {
        log.info(DaoConstants.CLOSING_CONNECTION_POOL);
        ((HikariDataSource) hikariDataSource).close();
    }

    private HikariConfig getHikariConfig() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(config.getRuta());
        hikariConfig.setUsername(config.getUser());
        hikariConfig.setPassword(config.getPassword());
        hikariConfig.setDriverClassName(config.getDriver());
        hikariConfig.setMaximumPoolSize(DaoConstants.MAX_POOL_SIZE);
        hikariConfig.setIdleTimeout(DaoConstants.IDLE_TIMEOUT_MS);
        hikariConfig.setConnectionTimeout(DaoConstants.IDLE_TIMEOUT_MS);
        hikariConfig.setMaxLifetime(DaoConstants.IDLE_TIMEOUT_MS);
        return hikariConfig;
    }
}
