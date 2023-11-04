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
@Singleton
public class DBConnection {

    private final Configuration configuration;

    @Inject
    public DBConnection(Configuration configuration) {
        this.configuration = configuration;
    }

    public Connection getConnection() throws SQLException {

        return DriverManager
                .getConnection(configuration.getProperty("urlDB"), configuration.getProperty("user_name"), configuration.getProperty("password"));
    }

    /**
     * Closes connection
     */
    public void closeConnection(Connection connArg) {
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


    //TODO Lucia este codigo es de hikari que no me funciona, bueno mas bien funciona un rato
    // pero al cabo de un apr de cambios de pantalla se me queda pillado y se me apaga la aplicacion
    // por ello te lo dejo comentado por si acaso  y te entrego con la conexion que funciona, basta con que descomentes
    // el codigo de abajo y comentes el de arriba

//    private final DataSource hikariDataSource;
//
//    @Inject
//    public DBConnection(Configuration configuration) {
//        this.configuration = configuration;
//        hikariDataSource = getHikariPool();
//    }
//
//
//    private DataSource getHikariPool() {
//        HikariConfig hikariConfig = new HikariConfig();
//        hikariConfig.setJdbcUrl(configuration.getProperty("urlDB"));
//        hikariConfig.setUsername(configuration.getProperty("user_name"));
//        hikariConfig.setPassword(configuration.getProperty("password"));
//        hikariConfig.setMaximumPoolSize(4);
//
//        hikariConfig.addDataSourceProperty("cachePrepStmts", true);
//        hikariConfig.addDataSourceProperty("prepStmtCacheSize", 250);
//        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
//
//        return new HikariDataSource(hikariConfig);
//    }
//
//    public Connection getConnection() {
//        Connection con = null;
//        try {
//            con = hikariDataSource.getConnection();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return con;
//    }
//
//    public void closeConnection(Connection con) {
//        try {
//            con.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @PreDestroy
//    public void closePool() {
//        ((HikariDataSource) hikariDataSource).close();
//    }
}
