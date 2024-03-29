package dao;

import jakarta.annotation.PreDestroy;

import javax.sql.DataSource;
import java.sql.Connection;

public interface DBConnection {
//    Connection getConnection() throws SQLException;

    DataSource getDataSource();

    void closeConnection(Connection connArg);

    @PreDestroy
    void closePool();
}
