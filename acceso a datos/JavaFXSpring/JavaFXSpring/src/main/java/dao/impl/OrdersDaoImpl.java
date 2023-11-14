package dao.impl;

import dao.DBConnection;
import dao.OrdersDao;
import dao.impl.rowmappers.OrderRowMapper;
import dao.utils.DaoConstants;
import dao.utils.SQLQueries;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import model.ErrorC;
import model.Order;
import model.OrderItem;
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
    public Either<ErrorC, List<Order>> getAll() {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dbConnection.getDataSource());
            List<Order> orders = jdbcTemplate.query(SQLQueries.GET_ALL_ORDERS, new OrderRowMapper());

            if (orders.isEmpty()) {
                return Either.left(new ErrorC(DaoConstants.NO_ORDERS_FOUND));
            } else {
                return Either.right(orders);
            }

        } catch (Exception e) {
            log.error(e.getMessage());
            return Either.left(new ErrorC(DaoConstants.ERROR_GETTING_ORDERS));
        }


    }

    @Override
    public Either<ErrorC, List<Order>> get(int idUserLogged) {
        try{
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dbConnection.getDataSource());
            List<Order>orders = jdbcTemplate.query(SQLQueries.GET_ORDERS_SPECIFIC_CUSTOMER + idUserLogged, new OrderRowMapper());

            if(orders.isEmpty()){
                return Either.left(new ErrorC(DaoConstants.NO_ORDERS_FOUND));
            }else{

                List<Integer> orderIds = orders.stream().map(Order::getId).toList();
                List<OrderItem> orderItems = new ArrayList<>();
                for (Integer orderId : orderIds) {
                    orderItems = jdbcTemplate.query(SQLQueries.SELECT_FROM_ORDER_ITEMS_WHERE_ORDER_ID + orderId, new BeanPropertyRowMapper<>(OrderItem.class));
                }
                for (Order order : orders) {
                    order.setOrderItems(orderItems);
                }
                return Either.right(orders);
            }

        } catch (Exception e) {
            log.error(e.getMessage());
            return Either.left(new ErrorC(DaoConstants.ERROR_GETTING_ORDERS));
        }
    }

    @Override
    public Either<ErrorC, Integer> save(Order order) {
        int rowsAffected;
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
                PreparedStatement preparedStatementOrderItemsAdd = connection.prepareStatement(SQLQueries.INSERT_INTO_ORDER_ITEMS_ORDER_ID_MENU_ITEM_ID_QUANTITY_VALUES);
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
            return Either.left(new ErrorC(DaoConstants.ERROR_ADDING_ORDER));
        } catch (Exception e) {
            log.error(e.getMessage());
            return Either.left(new ErrorC(DaoConstants.ERROR_ADDING_ORDER_32323));
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
                        return Either.left(new ErrorC(DaoConstants.ORDER_HAS_ORDER_ITEMS));
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

                    PreparedStatement preparedStatementDeleteOrderItems = connection.prepareStatement(SQLQueries.DELETE_FROM_ORDER_ITEMS_WHERE_ORDER_ID_EQUALS + id);

                    PreparedStatement preparedStatementDeleteOrders = connection.prepareStatement(SQLQueries.DELETE_FROM_ORDERS_WHERE_ORDER_ID);
                    preparedStatementDeleteOrders.setInt(1, id);

                    rowsAffected += preparedStatementDeleteOrderItems.executeUpdate();
                    rowsAffected += preparedStatementDeleteOrders.executeUpdate();

                    connection.commit();
                } catch (SQLException e) {
                    tryCatchRollbak(connection);
                    if (e.getErrorCode() == 1451) {
                        return Either.left(new ErrorC(DaoConstants.ORDER_HAS_ORDER_ITEMS));
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
                return Either.left(new ErrorC(DaoConstants.ERROR_DELETING_ORDER));
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
}
