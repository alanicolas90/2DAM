package dao.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import config.Configuration;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.extern.log4j.Log4j2;

import javax.sql.DataSource;
import java.sql.*;

@Log4j2
public class DBConnection {

    private final Configuration configuration;
    private final DataSource hikariDataSource;

    @Inject
    public DBConnection(Configuration configuration) {
        this.configuration = configuration;
        hikariDataSource = getHikariPool();
    }


    private DataSource getHikariPool() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(configuration.getProperty("urlDB"));
        hikariConfig.setUsername(configuration.getProperty("user_name"));
        hikariConfig.setPassword(configuration.getProperty("password"));
        hikariConfig.setMaximumPoolSize(10);

        hikariConfig.addDataSourceProperty("cachePrepStmts", true);
        hikariConfig.addDataSourceProperty("prepStmtCacheSize", 250);
        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);

        return new HikariDataSource(hikariConfig);
    }

    public Connection getConnection() {
        Connection con = null;
        try {
            con = hikariDataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return con;
    }

    public void closeConnection(Connection con) {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void closePool() {
        ((HikariDataSource) hikariDataSource).close();
    }
}
