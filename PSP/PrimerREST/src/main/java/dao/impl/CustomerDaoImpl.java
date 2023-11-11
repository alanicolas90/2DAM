package dao.impl;


import dao.CustomerDao;
import dao.DBConnection;
import dao.model.Customer;
import dao.utils.DaoConstants;
import dao.utils.SQLQueries;
import domain.modelo.errores.BaseDatosCaidaException;
import domain.modelo.errores.NotFoundException;
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
                throw new NotFoundException(DaoConstants.NO_CUSTOMER_FOUND);
            } else {
                return customers;
            }

        } catch (Exception e) {
            log.error(e.getMessage());
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
            log.error(e.getMessage());
            throw new BaseDatosCaidaException(DaoConstants.DATABASE_ERROR);
        }
    }

    @Override
    public List<Customer> getAll() {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dbConnection.getDataSource());
            return jdbcTemplate.query(SQLQueries.GET_ALL_CUSTOMERS, new BeanPropertyRowMapper<>(Customer.class));

        } catch (DataAccessException e) {
            throw new BaseDatosCaidaException(DaoConstants.DATABASE_ERROR);
        }
    }

    @Override
    public Integer save(Customer customer) {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dbConnection.getDataSource());
            return jdbcTemplate.update(SQLQueries.INSERT_INTO_CUSTOMERS_NAME_VALUES, customer.getName());

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new BaseDatosCaidaException(DaoConstants.DATABASE_ERROR);
        }
    }

    @Override
    public Integer delete(int id) {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dbConnection.getDataSource());
            int rows = jdbcTemplate.update(SQLQueries.DELETE_CUSTOMER_WITHOUT_ORDERS, id);
            if(rows == 0){
                throw new NotFoundException(DaoConstants.ERROR_DELETING_CUSTOMER);
            }else{
                return rows;
            }
        } catch (DataIntegrityViolationException e) {
            log.error(e.getMessage());
            throw new BaseDatosCaidaException(DaoConstants.DATABASE_ERROR);
        }
    }

}
