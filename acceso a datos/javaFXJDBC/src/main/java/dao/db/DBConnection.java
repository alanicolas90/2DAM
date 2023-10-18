package dao.db;

import config.Configuration;
import jakarta.inject.Inject;

import java.sql.*;

public class DBConnection {

    private final Configuration configuration;

    @Inject
    public DBConnection(Configuration configuration) {
        this.configuration = configuration;
    }

    public Connection getConnection() throws SQLException{
        return DriverManager
                .getConnection(configuration.getProperty("urlDB"), configuration.getProperty("user_name"), configuration.getProperty("password"));

    }

    public void closeConnection(Connection connArg) {
        System.out.println("Releasing all open resources ...");
        try {
            if (connArg != null) {
                connArg.close();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    public void releaseResource(PreparedStatement pstmt) {
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    public void releaseResource(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    public void releaseResource(Statement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
}
