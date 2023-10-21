package dao.impl;

import dao.OrdersDao;
import dao.db.DBConnection;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import model.ErrorC;
import model.Order;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
            statement.executeQuery("SELECT * FROM orders");
            ResultSet resultSet = statement.getResultSet();
            orders = readRS(resultSet);

        } catch (SQLException e) {
            log.error(e.getMessage());
            return Either.left(new ErrorC("Error getting orders"));
        }

        if (orders.isEmpty()) {
            return Either.left(new ErrorC("No orders found"));
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
