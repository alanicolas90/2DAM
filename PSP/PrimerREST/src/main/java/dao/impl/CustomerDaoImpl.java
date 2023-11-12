package dao.impl;


import dao.CustomerDao;
import dao.DBConnection;
import dao.model.Customer;
import dao.model.Order;
import dao.utils.DaoConstants;
import dao.utils.SQLQueries;
import dao.model.errores.BaseDatosCaidaException;
import jakarta.inject.Inject;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;


class CustomerDaoImpl implements CustomerDao {


    private final DBConnection dbConnection;

    @Inject
    public CustomerDaoImpl(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }


    @Override
    public Customer get(int id) {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dbConnection.getDataSource());
            return jdbcTemplate.queryForObject(SQLQueries.GET_CUSTOMER_SPECIFIC_ID,
                    new BeanPropertyRowMapper<>(Customer.class),
                    id);

        } catch (Exception e) {
            throw new BaseDatosCaidaException(DaoConstants.DATABASE_ERROR);
        }
    }

    @Override
    public Integer update(Customer customerUpdated) {
        try {
            JdbcTemplate jtm = new JdbcTemplate(dbConnection.getDataSource());
            return jtm.update(SQLQueries.UPDATE_CUSTOMER,
                    customerUpdated.getName(),
                    customerUpdated.getId());

        } catch (Exception e) {
            throw new BaseDatosCaidaException(DaoConstants.DATABASE_ERROR);
        }
    }

    @Override
    public List<Customer> getAll() {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dbConnection.getDataSource());
            return jdbcTemplate.query(SQLQueries.GET_ALL_CUSTOMERS, new BeanPropertyRowMapper<>(Customer.class));

        } catch (Exception e) {
            throw new BaseDatosCaidaException(DaoConstants.DATABASE_ERROR);
        }
    }

    @Override
    public Integer save(Customer customer) {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dbConnection.getDataSource());
            return jdbcTemplate.update(SQLQueries.INSERT_INTO_CUSTOMERS_NAME_VALUES, customer.getName(), customer.getBirthDate());

        } catch (Exception e) {
            throw new BaseDatosCaidaException(DaoConstants.DATABASE_ERROR);
        }
    }

    @Override
    public Integer delete(int id) {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dbConnection.getDataSource());
            return jdbcTemplate.update(SQLQueries.DELETE_CUSTOMER_WITHOUT_ORDERS, id);

        } catch (Exception e) {
            throw new BaseDatosCaidaException(DaoConstants.DATABASE_ERROR);
        }
    }

    @Override
    public List<Order> customerHasOrders(Integer id) {
        try{
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dbConnection.getDataSource());
            return jdbcTemplate.query(SQLQueries.SELECT_FROM_ORDERS_WHERE_CUSTOMER_ID,
                    new BeanPropertyRowMapper<>(Order.class),
                    id);
        }catch (Exception e){
            throw new BaseDatosCaidaException(DaoConstants.DATABASE_ERROR);
        }

    }

}
