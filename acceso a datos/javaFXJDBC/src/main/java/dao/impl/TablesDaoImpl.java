package dao.impl;

import dao.TablesDao;
import dao.db.DBConnection;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import model.ErrorC;
import model.Order;
import model.Table;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class TablesDaoImpl implements TablesDao {

    private final DBConnection dbConnection;

    @Inject
    public TablesDaoImpl(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public Either<ErrorC, List<Table>> getAll() {
        List<Table> tables;
        try {
            Connection connection = dbConnection.getConnection();
            Statement statement = connection.createStatement();
            statement.executeQuery("SELECT * FROM tables");
            ResultSet resultSet = statement.getResultSet();
            tables = readRS(resultSet);

        } catch (SQLException e) {
            log.error(e.getMessage());
            return Either.left(new ErrorC("Error getting orders"));
        }

        if (tables.isEmpty()) {
            return Either.left(new ErrorC("No orders found"));
        } else {
            return Either.right(tables);
        }
    }

    @Override
    public Either<ErrorC, Table> get(int tableNumber) {
        Table table;
        try {
            Connection connection = dbConnection.getConnection();
            Statement statement = connection.createStatement();
            statement.executeQuery("SELECT * FROM tables where table_number = " + tableNumber);
            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                table = new Table(resultSet.getInt("table_number"), resultSet.getInt("seats"));
            } else {
                return Either.left(new ErrorC("No table found"));
            }

        } catch (SQLException e) {
            log.error(e.getMessage());
            return Either.left(new ErrorC("Error getting orders"));
        }

        return Either.right(table);

    }

    private List<Table> readRS(ResultSet resultSet) {
        List<Table> tables = new ArrayList<>();
        try {
            while (resultSet.next()) {
                int tableId = resultSet.getInt("table_number");
                int tableSeats = resultSet.getInt("seats");
                Table table = new Table(tableId, tableSeats);
                tables.add(table);

            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return tables;
    }
}
