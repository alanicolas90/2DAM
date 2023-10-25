package dao.impl;

import dao.TablesDao;
import dao.db.DBConnection;
import dao.utils.DaoConstants;
import dao.utils.SQLQueries;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import model.ErrorC;
import model.Table;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
            statement.executeQuery(SQLQueries.SELECT_ALL_FROM_TABLES);
            ResultSet resultSet = statement.getResultSet();
            tables = readRS(resultSet);

        } catch (SQLException e) {
            log.error(e.getMessage());
            return Either.left(new ErrorC(DaoConstants.ERROR_GETTING_TABLES));
        }finally {
            if (dbConnection != null) dbConnection.closeConnection(dbConnection.getConnection());
        }

        if (tables.isEmpty()) {
            return Either.left(new ErrorC(DaoConstants.NO_TABLES_WERE_FOUND));
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
            statement.executeQuery(SQLQueries.SELECT_FROM_TABLES_WHERE_TABLE_NUMBER + tableNumber);
            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                table = new Table(resultSet.getInt(DaoConstants.TABLE_NUMBER), resultSet.getInt(DaoConstants.SEATS));
            } else {
                return Either.left(new ErrorC(DaoConstants.NO_TABLE_FOUND));
            }

        } catch (SQLException e) {
            log.error(e.getMessage());
            return Either.left(new ErrorC(DaoConstants.ERROR_GETTING_TABLES));
        }finally {
            if (dbConnection != null) dbConnection.closeConnection(dbConnection.getConnection());
        }

        return Either.right(table);

    }

    private List<Table> readRS(ResultSet resultSet) {
        List<Table> tables = new ArrayList<>();
        try {
            while (resultSet.next()) {
                int tableId = resultSet.getInt(DaoConstants.TABLE_NUMBER);
                int tableSeats = resultSet.getInt(DaoConstants.SEATS);
                Table table = new Table(tableId, tableSeats);
                tables.add(table);

            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
        }finally {
            if (dbConnection != null) dbConnection.closeConnection(dbConnection.getConnection());
        }
        return tables;
    }
}
