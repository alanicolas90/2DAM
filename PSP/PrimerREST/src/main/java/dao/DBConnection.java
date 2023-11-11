package dao;

import jakarta.annotation.PreDestroy;

import javax.sql.DataSource;

public interface DBConnection {
    DataSource getDataSource();

    void loadPool();

    @PreDestroy
    void closePool();
}
