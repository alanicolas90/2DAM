package dao.deprecated.impl;


import dao.DBConnection;

import dao.deprecated.OrdersDao;
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
        try(Connection connection = dbConnection.getDataSource().getConnection();
            Statement statement = connection.createStatement();) {

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
        try(Connection connection = dbConnection.getDataSource().getConnection();
            Statement statement = connection.createStatement();) {

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
        }
        return orders;
    }
}
