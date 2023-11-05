package dao.impl;

import dao.DBConnection;
import dao.OrdersDao;
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
            Statement statement = connection.createStatement()) {

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
            Statement statement = connection.createStatement()){

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
    public Either<ErrorC, Integer> add(Order order) {
        int rowsAffected = 0;
        Connection connection = null;
        try {
            connection = dbConnection.getDataSource().getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatementOrderAdd = connection.prepareStatement(SQLQueries.ADD_ORDER, Statement.RETURN_GENERATED_KEYS);
            preparedStatementOrderAdd.setTimestamp(1, Timestamp.valueOf(order.getDate()));
            preparedStatementOrderAdd.setInt(2, order.getCustomerId());
            preparedStatementOrderAdd.setInt(3, order.getTableNumber());
            rowsAffected = preparedStatementOrderAdd.executeUpdate();
            ResultSet rs = preparedStatementOrderAdd.getGeneratedKeys();
            int orderId = -1;
            if (rs.next()) {
                orderId = rs.getInt(1);
            }
            if (!order.getOrderItems().isEmpty() && orderId != -1) {
                PreparedStatement preparedStatementOrderItemsAdd = connection.prepareStatement("INSERT INTO order_items (order_id, menu_item_id, quantity) VALUES (?,?,?)");
                for (int i = 0; i < order.getOrderItems().size(); i++) {
                    preparedStatementOrderItemsAdd.setInt(1, orderId);
                    preparedStatementOrderItemsAdd.setInt(2, order.getOrderItems().get(i).getMenuItemId());
                    preparedStatementOrderItemsAdd.setInt(3, order.getOrderItems().get(i).getQuantity());
                    preparedStatementOrderItemsAdd.addBatch();
                }
                preparedStatementOrderItemsAdd.executeBatch();
            }

            connection.commit();
        } catch (SQLException e) {
            tryCatchRollbak(connection);
            log.error(e.getMessage());
            return Either.left(new ErrorC("Error adding order"));
        } catch (Exception e) {
            log.error(e.getMessage());
            return Either.left(new ErrorC("Error adding order 32323"));
        }
        return Either.right(rowsAffected);
    }

    @Override
    public Either<ErrorC, Integer> update(Order order) {
        int rowsAffected = 0;
        try(Connection connection = dbConnection.getDataSource().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.UPDATE_ORDERS)){

            preparedStatement.setTimestamp(1, Timestamp.valueOf(order.getDate()));
            preparedStatement.setInt(2, order.getTableNumber());
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
