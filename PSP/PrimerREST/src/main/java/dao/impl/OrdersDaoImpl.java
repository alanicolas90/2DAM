package dao.impl;

import dao.DBConnection;
import dao.OrdersDao;
import dao.model.Order;
import dao.utils.SQLQueries;
import domain.modelo.errores.NotFoundException;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@Log4j2
public class OrdersDaoImpl implements OrdersDao {

    private final DBConnection dbConnection;

    @Inject
    public OrdersDaoImpl(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }


    @Override
    public List<Order> getAll() {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dbConnection.getDataSource());
            return jdbcTemplate.query(SQLQueries.GET_ALL_ORDERS, new BeanPropertyRowMapper<>(Order.class));

        } catch (Exception e) {
            throw new NotFoundException("Error adding customer");
        }

    }

    @Override
    public List<Order> get(int idCustomer) {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dbConnection.getDataSource());
            return jdbcTemplate.query(SQLQueries.GET_ORDERS_SPECIFIC_CUSTOMER, new BeanPropertyRowMapper<>(Order.class), idCustomer);

        } catch (Exception e) {
            throw new NotFoundException("Error getting customer");
        }


    }

    @Override
    public Integer add(Order order) {

        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dbConnection.getDataSource());
            return jdbcTemplate.update(SQLQueries.ADD_ORDER, order.getItemName(), order.getQuantity(), order.getCustomerId());

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new NotFoundException("Error adding order");
        }
    }

    @Override
    public void update(Order order) {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dbConnection.getDataSource());
            int rowsAffected = jdbcTemplate.update("UPDATE orders SET item_name = ?, quantity = ?, customerId = ? WHERE id = ?",
                    order.getItemName(),
                    order.getQuantity(),
                    order.getCustomerId(),
                    order.getId());

            if (rowsAffected == 0) {
                throw new NotFoundException("Error updating order, order does not exist");
            } else {
            }

        }  catch (Exception e) {
            log.error(e.getMessage());
            throw new NotFoundException("Error with db connection or query");
        }
    }

    @Override
    public Integer delete(int id) {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dbConnection.getDataSource());
            int rowsAffected = jdbcTemplate.update(SQLQueries.DELETE_FROM_ORDERS_WHERE_ORDER_ID, id);

            if (rowsAffected == 0) {
                throw new NotFoundException("Error deleting order, order does not exist");
            } else {
                return rowsAffected;
            }
        } catch (Exception e) {
            throw new NotFoundException("Querry error or connection error");
        }
    }


}
