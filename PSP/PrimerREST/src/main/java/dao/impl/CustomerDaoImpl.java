package dao.impl;


import dao.CustomerDao;
import dao.DBConnection;
import dao.model.Customer;
import dao.model.ErrorC;
import dao.utils.DaoConstants;
import dao.utils.SQLQueries;
import domain.modelo.errores.BaseDatosCaidaException;
import domain.modelo.errores.NotFoundException;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;


@Log4j2
public class CustomerDaoImpl implements CustomerDao {

    private final DBConnection dbConnection;

    @Inject
    public CustomerDaoImpl(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }


    @Override
    public Customer get(int id) {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dbConnection.getDataSource());
            Customer customers = jdbcTemplate.queryForObject(SQLQueries.GET_CUSTOMER_SPECIFIC_ID,
                    new BeanPropertyRowMapper<>(Customer.class),
                    id);

            if (customers == null) {
                throw new NotFoundException("No customer found");
            } else {
                return customers;
            }

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new BaseDatosCaidaException("Database error");
        }
    }

    @Override
    public void update(Customer customerUpdated) {
        try {
            JdbcTemplate jtm = new JdbcTemplate(dbConnection.getDataSource());
            int rows = jtm.update(SQLQueries.UPDATE_CUSTOMER,
                    customerUpdated.getName(),
                    customerUpdated.getId());
            if(rows == 0){
                throw new NotFoundException("No customer found");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new BaseDatosCaidaException("Database error");
        }
    }

    @Override
    public List<Customer> getAll() {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dbConnection.getDataSource());
            List<Customer> customers = jdbcTemplate.query(SQLQueries.GET_ALL_CUSTOMERS, new BeanPropertyRowMapper<>(Customer.class));

            if (customers.isEmpty()) {
                throw new NotFoundException("No customers found");
            } else {
                return customers;
            }
        } catch (DataAccessException e) {
            throw new BaseDatosCaidaException("Database error");
        }
    }

    @Override
    public void save(Customer customer) {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dbConnection.getDataSource());
            jdbcTemplate.update(SQLQueries.INSERT_INTO_CUSTOMERS_NAME_VALUES, customer.getName());

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new NotFoundException("Error adding customer");
        }
    }

    @Override
    public Integer delete(int id) {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dbConnection.getDataSource());
            int rows = jdbcTemplate.update(SQLQueries.DELETE_CUSTOMER_WITHOUT_ORDERS, id);
            if(rows == 0){
                throw new NotFoundException("Error deleting customer");
            }else{
                return rows;
            }
        } catch (DataIntegrityViolationException e) {
            log.error(e.getMessage());
            throw new NotFoundException("Error deleting customer");
        }
    }

}
