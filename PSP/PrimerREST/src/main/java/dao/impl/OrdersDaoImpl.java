package dao.impl;

import dao.DBConnection;
import dao.OrdersDao;
import dao.model.ErrorC;
import dao.model.Order;
import dao.utils.SQLQueries;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;
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
    public  List<Order> getAll() {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dbConnection.getDataSource());
            return jdbcTemplate.query(SQLQueries.GET_ALL_ORDERS, new BeanPropertyRowMapper<>(Order.class));

        } catch (Exception e) {
            log.error(e.getMessage());
            new ErrorC("Error adding customer");
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public List<Order> get(int idCustomer) {
        try{
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dbConnection.getDataSource());
            return jdbcTemplate.query(SQLQueries.GET_ORDERS_SPECIFIC_CUSTOMER, new BeanPropertyRowMapper<>(Order.class), idCustomer);

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }


    }

    @Override
    public Integer add(Order order) {

        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dbConnection.getDataSource());
            return jdbcTemplate.update(SQLQueries.ADD_ORDER, order.getItemName(), order.getQuantity(), order.getCustomerId());

        }  catch (Exception e) {
            log.error(e.getMessage());
            throw new NotFoundException("Error adding order");
        }
    }

    @Override
    public Either<ErrorC, Integer> update(Order order) {
        int rowsAffected = 0;
        try(Connection connection = dbConnection.getDataSource().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.UPDATE_ORDERS)){

            preparedStatement.setString(1, order.getItemName());
            preparedStatement.setInt(2, order.getQuantity());
            preparedStatement.setInt(3, order.getId());

            rowsAffected = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return Either.right(rowsAffected);
    }

    @Override
    public Either<ErrorC, Integer> delete(int id, boolean delete) {
        int rowsAffected = 0;
        if (!delete) {
            try {
                Connection connection = dbConnection.getDataSource().getConnection();
                try {
                    connection.setAutoCommit(false);
                    PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.DELETE_FROM_ORDERS_WHERE_ORDER_ID);
                    preparedStatement.setInt(1, id);
                    rowsAffected = preparedStatement.executeUpdate();
                    connection.commit();
                } catch (SQLException e) {
                    tryCatchRollbak(connection);
                    if (e.getErrorCode() == 1451) {
                        return Either.left(new ErrorC("Order has order items"));
                    } else {
                        log.error(e.getMessage());
                    }
                } finally {
                    connection.setAutoCommit(true);
                }
            } catch (SQLException e) {
                log.error(e.getMessage());
            }
            return Either.right(rowsAffected);
        }
        //delete Order with order items accepted by Customer
        else {
            try {
                Connection connection = dbConnection.getDataSource().getConnection();
                try {
                    connection.setAutoCommit(false);

                    PreparedStatement preparedStatementDeleteOrderItems = connection.prepareStatement("delete from order_items where order_id =" + id);

                    PreparedStatement preparedStatementDeleteOrders = connection.prepareStatement(SQLQueries.DELETE_FROM_ORDERS_WHERE_ORDER_ID);
                    preparedStatementDeleteOrders.setInt(1, id);

                    rowsAffected += preparedStatementDeleteOrderItems.executeUpdate();
                    rowsAffected += preparedStatementDeleteOrders.executeUpdate();

                    connection.commit();
                } catch (SQLException e) {
                    tryCatchRollbak(connection);
                    if (e.getErrorCode() == 1451) {
                        return Either.left(new ErrorC("Order has order items"));
                    } else {
                        log.error(e.getMessage());
                    }
                } finally {
                    connection.setAutoCommit(true);
                }

            } catch (Exception e) {
                log.error(e.getMessage());
            }
            if (rowsAffected < 1) {
                return Either.left(new ErrorC("Error deleting order"));
            } else {
                return Either.right(rowsAffected);
            }
        }

    }


    //Utility methods
    private void tryCatchRollbak(Connection connection) {
        try {
            connection.rollback();
            connection.setAutoCommit(true);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    private List<Order> readRS(ResultSet resultSet) {
        List<Order> orders = new ArrayList<>();
        try {
            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String itemName = resultSet.getString("item_name");
                int quantity = resultSet.getInt("quantity");
                int customerId = resultSet.getInt("customer_id");

                Order order = new Order(id, itemName, quantity,customerId);
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
