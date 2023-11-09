package dao;

import jakarta.annotation.PreDestroy;

import javax.sql.DataSource;
import java.sql.Connection;

public interface DBConnection {
//    Connection getConnection() throws SQLException;

    DataSource getDataSource();

    void closeConnection(Connection connArg);

    void loadPool();

    Connection getConnection() throws Exception;

    @PreDestroy
    void closePool();
}
