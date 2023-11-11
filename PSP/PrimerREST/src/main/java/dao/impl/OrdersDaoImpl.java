package dao.impl;

import dao.DBConnection;
import dao.OrdersDao;
import dao.model.Order;
import dao.utils.DaoConstants;
import dao.utils.SQLQueries;
import domain.modelo.errores.BaseDatosCaidaException;
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
            List<Order> orders = jdbcTemplate.query(SQLQueries.GET_ALL_ORDERS, new BeanPropertyRowMapper<>(Order.class));
            if(orders.isEmpty()){
                throw new NotFoundException(DaoConstants.NO_ORDERS_FOUND);
            }else{
                return orders;
            }
        } catch (Exception e) {
            throw new BaseDatosCaidaException(DaoConstants.DATABASE_ERROR);
        }

    }


    @Override
    public List<Order> get(int idCustomer) {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dbConnection.getDataSource());
            List<Order> orders = jdbcTemplate.query(SQLQueries.GET_ORDERS_SPECIFIC_CUSTOMER, new BeanPropertyRowMapper<>(Order.class), idCustomer);
            if(orders.isEmpty()){
                throw new NotFoundException(DaoConstants.NO_ORDERS_FOUND);
            }else{
                return orders;
            }

        } catch (Exception e) {
            throw new BaseDatosCaidaException(DaoConstants.DATABASE_ERROR);
        }


    }

    @Override
    public Integer add(Order order) {

        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dbConnection.getDataSource());
            int rowsAffected = jdbcTemplate.update(SQLQueries.ADD_ORDER, order.getItemName(), order.getQuantity(), order.getCustomerId());
            if(rowsAffected == 0){
                throw new NotFoundException(DaoConstants.ERROR_ADDING_ORDER);
            }else{
                return rowsAffected;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
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
            log.error(e.getMessage());
            throw new BaseDatosCaidaException(DaoConstants.DATABASE_ERROR);
        }
    }

    @Override
    public Integer delete(int id) {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dbConnection.getDataSource());
            int rowsAffected = jdbcTemplate.update(SQLQueries.DELETE_FROM_ORDERS_WHERE_ORDER_ID, id);

            if (rowsAffected == 0) {
                throw new NotFoundException(DaoConstants.ERROR_DELETING_ORDER_ORDER_DOES_NOT_EXIST);
            } else {
                return rowsAffected;
            }
        } catch (Exception e) {
            throw new BaseDatosCaidaException(DaoConstants.DATABASE_ERROR);
        }
    }


}
