package dao.impl;

import dao.DBConnection;
import dao.OrdersDao;
import dao.model.Order;
import dao.utils.DaoConstants;
import dao.utils.SQLQueries;
import dao.model.errores.BaseDatosCaidaException;
import jakarta.inject.Inject;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;


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
            throw new BaseDatosCaidaException(DaoConstants.DATABASE_ERROR);
        }

    }


    @Override
    public Order get(int orderId) {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dbConnection.getDataSource());
            return  jdbcTemplate.queryForObject(SQLQueries.GET_ORDER,
                    new BeanPropertyRowMapper<>(Order.class),
                    orderId);

        } catch (Exception e) {
            throw new BaseDatosCaidaException(DaoConstants.DATABASE_ERROR);
        }


    }

    @Override
    public Integer add(Order order) {

        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dbConnection.getDataSource());
            return jdbcTemplate.update(SQLQueries.ADD_ORDER, order.getItemName(), order.getQuantity(), order.getCustomerId());

        } catch (Exception e) {
            throw new BaseDatosCaidaException(DaoConstants.DATABASE_ERROR);
        }
    }

    @Override
    public Integer update(Order order) {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dbConnection.getDataSource());
            return jdbcTemplate.update(SQLQueries.UPDATE_ORDERS_SET_ITEM_NAME_QUANTITY_CUSTOMER_ID_WHERE_ID,
                    order.getItemName(),
                    order.getQuantity(),
                    order.getCustomerId(),
                    order.getId());


        }  catch (Exception e) {
            throw new BaseDatosCaidaException(DaoConstants.DATABASE_ERROR);
        }
    }

    @Override
    public Integer delete(int id) {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dbConnection.getDataSource());
            return jdbcTemplate.update(SQLQueries.DELETE_FROM_ORDERS_WHERE_ORDER_ID, id);

        } catch (Exception e) {
            throw new BaseDatosCaidaException(DaoConstants.DATABASE_ERROR);
        }
    }

    @Override
    public Integer deleteByCustomerId(Integer customerId) {
        try{
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dbConnection.getDataSource());
            return jdbcTemplate.update("DELETE FROM orders WHERE customerId = ?", customerId);
        }catch (Exception e){
            throw new BaseDatosCaidaException(DaoConstants.DATABASE_ERROR);
        }
    }


}
