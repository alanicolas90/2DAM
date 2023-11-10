package dao.impl;


import dao.CustomerDao;
import dao.DBConnection;
import dao.model.Customer;
import dao.model.ErrorC;
import dao.utils.DaoConstants;
import dao.utils.SQLQueries;
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
    public Either<ErrorC, Customer> get(int id) {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dbConnection.getDataSource());
            List<Customer> customers = jdbcTemplate.query(SQLQueries.GET_CUSTOMER_SPECIFIC_ID, new BeanPropertyRowMapper<>(Customer.class), id);
            if (customers.isEmpty()) {
                return Either.left(new ErrorC(DaoConstants.CUSTOMER_NOT_FOUND));
            } else {
                return Either.right(customers.get(0));
            }

        } catch (Exception e) {
            log.error(e.getMessage());
            return Either.left(new ErrorC("Database error"));
        }
    }

    @Override
    public Either<ErrorC, Integer> update(Customer customerUpdated) {
        try {
            JdbcTemplate jtm = new JdbcTemplate(dbConnection.getDataSource());
            return Either.right(jtm.update(SQLQueries.UPDATE_CUSTOMER,
                    customerUpdated.getName(),
                    customerUpdated.getId()));

        } catch (Exception e) {
            log.error(e.getMessage());
            return Either.left(new ErrorC("Database error"));
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
            throw new NotFoundException("No connection to db");
        }
    }

    @Override
    public Customer saveAutoIncrementalID(Customer customer) {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dbConnection.getDataSource());
            jdbcTemplate.update("INSERT INTO customers (`name`) VALUES (?);", customer.getName());

        } catch (Exception e) {
            log.error(e.getMessage());
            new ErrorC("Error adding customer");
        }
        return customer;
    }

    @Override
    public Integer delete(int id) {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dbConnection.getDataSource());
            int rows = jdbcTemplate.update(SQLQueries.DELETE_CUSTOMER_WITHOUT_ORDERS, id);
            if(rows == 0){
                throw new RuntimeException("Error deleting customer");
            }else{
                return rows;
            }
        } catch (DataIntegrityViolationException e) {
            log.error(e.getMessage());
            throw new RuntimeException("Error deleting customer");
        }
    }

}
