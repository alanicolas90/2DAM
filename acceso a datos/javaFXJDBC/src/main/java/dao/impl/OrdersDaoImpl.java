package dao.impl;

import dao.OrdersDao;
import dao.db.DBConnection;
import dao.utils.DaoConstants;
import dao.utils.SQLQueries;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import model.ErrorC;
import model.Order;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class OrdersDaoImpl implements OrdersDao {

    private final DBConnection dbConnection;

    @Inject
    public OrdersDaoImpl(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }


    @Override
    public Either<ErrorC, List<Order>> getAll() {
        List<Order> orders;
        try {
            Connection connection = dbConnection.getConnection();
            Statement statement = connection.createStatement();
            statement.executeQuery(SQLQueries.GET_ALL_ORDERS);
            ResultSet resultSet = statement.getResultSet();
            orders = readRS(resultSet);

        } catch (SQLException e) {
            log.error(e.getMessage());
            return Either.left(new ErrorC(DaoConstants.ERROR_GETTING_ORDERS));
        }

        if (orders.isEmpty()) {
            return Either.left(new ErrorC(DaoConstants.NO_ORDERS_FOUND));
        } else {
            return Either.right(orders);
        }

    }

    @Override
    public Either<ErrorC, List<Order>> get(int idUserLogged) {
        List<Order> orders;
        try {
            Connection connection = dbConnection.getConnection();
            Statement statement = connection.createStatement();
            statement.executeQuery(SQLQueries.GET_ORDERS_SPECIFIC_CUSTOMER + idUserLogged);
            ResultSet resultSet = statement.getResultSet();
            orders = readRS(resultSet);

        } catch (SQLException e) {
            log.error(e.getMessage());
            return Either.left(new ErrorC(DaoConstants.ERROR_GETTING_ORDERS));
        }

        if (orders.isEmpty()) {
            return Either.left(new ErrorC(DaoConstants.NO_ORDERS_FOUND));
        } else {
            return Either.right(orders);
        }
    }

    @Override
    public Either<ErrorC, Integer> add(LocalDateTime date, int customerId, int tableNumber) {
        int rowsAffected = 0;
        try {
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.ADD_ORDER);
            preparedStatement.setTimestamp(1, Timestamp.valueOf(date));
            preparedStatement.setInt(2, customerId);
            preparedStatement.setInt(3, tableNumber);
            rowsAffected = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            log.error(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return Either.right(rowsAffected);
    }

    @Override
    public Either<ErrorC, Integer> update(LocalDateTime date, int tableNumber, int orderID) {
        int rowsAffected = 0;
        try {
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.UPDATE_ORDERS);
            preparedStatement.setTimestamp(1, Timestamp.valueOf(date));
            preparedStatement.setInt(2, tableNumber);
            preparedStatement.setInt(3, orderID);
            rowsAffected = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return Either.right(rowsAffected);
    }

    @Override
    public Either<ErrorC, Integer> delete(int id) {
        int rowsAffected = 0;
        Connection connection = null;
        try {
            connection = dbConnection.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.DELETE_FROM_ORDERS_WHERE_ORDER_ID);
            preparedStatement.setInt(1, id);
            rowsAffected = preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            log.error(e.getMessage());
            tryCatchRollbak(connection);
        }
        if (rowsAffected == 0) {
            return Either.left(new ErrorC(DaoConstants.NO_ORDER_FOUND));
        } else {
            return Either.right(rowsAffected);
        }
    }


    //Utility methods
    private void tryCatchRollbak(Connection connection) {
        try {
            connection.rollback();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    private List<Order> readRS(ResultSet resultSet) {
        List<Order> orders = new ArrayList<>();
        try {
            while (resultSet.next()) {

                int id = resultSet.getInt("order_id");
                LocalDateTime date = resultSet.getTimestamp("order_date").toLocalDateTime();
                int customerId = resultSet.getInt("customer_id");
                int tableNumber = resultSet.getInt("table_number");
                Order order = new Order(id, date, customerId, tableNumber);
                orders.add(order);

            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
        }finally {
            if (dbConnection != null) dbConnection.closeConnection(dbConnection.getConnection());
        }
        return orders;
    }
}
