package dao.impl;


import dao.CustomerDao;
import dao.DBConnection;
import dao.model.Customer;
import dao.model.ErrorC;
import dao.utils.DaoConstants;
import dao.utils.SQLQueries;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;


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
                    customerUpdated.getSurname(),
                    customerUpdated.getEmail(),
                    customerUpdated.getPhone(),
                    customerUpdated.getBirthDate(),
                    customerUpdated.getId()));

        } catch (Exception e) {
            log.error(e.getMessage());
            return Either.left(new ErrorC("Database error"));
        }
    }

    @Override
    public Either<ErrorC, List<Customer>> getAll() {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dbConnection.getDataSource());
            List<Customer> customers = jdbcTemplate.query(SQLQueries.GET_ALL_CUSTOMERS, new BeanPropertyRowMapper<>(Customer.class));

            if (customers.isEmpty()) {
                return Either.left(new ErrorC("No customers were found"));
            } else {
                return Either.right(customers);
            }
        } catch (DataAccessException e) {
            Logger.getLogger(CustomerDaoImpl.class.getName()).severe(e.getMessage());
            return Either.left(new ErrorC("Error in data base"));
        }
    }

    @Override
    public Either<ErrorC, Integer> saveAutoIncrementalID(Customer customer) {
        try {
            SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(dbConnection.getDataSource())
                    .withTableName("credentials")
                    .usingGeneratedKeyColumns("id")
                    .usingColumns("username", "password");

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("username", customer.getCredential().getUsername());
            parameters.put("password", customer.getCredential().getPassword());
            int customerId = (int) jdbcInsert.executeAndReturnKey(parameters).longValue();


            JdbcTemplate jdbcTemplate = new JdbcTemplate(dbConnection.getDataSource());
            return Either.right(jdbcTemplate
                    .update(SQLQueries.INSERT_NEW_CUSTOMER,
                            customerId,
                            customer.getName(),
                            customer.getSurname(),
                            customer.getEmail(),
                            customer.getPhone(),
                            customer.getBirthDate()));

        } catch (Exception e) {
            log.error(e.getMessage());
            return Either.left(new ErrorC("Error adding customer"));
        }
    }

    @Override
    public Either<ErrorC, Integer> delete(int id, boolean confirm) {
        TransactionDefinition txDef = new DefaultTransactionDefinition();
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dbConnection.getDataSource());
        TransactionStatus txStatus = transactionManager.getTransaction(txDef);
        int rowsAffected = 0;
        //delete customer that has no orders, if its has orders it will send back an error
        if (!confirm) {
            try {
                JdbcTemplate jtm = new JdbcTemplate(dbConnection.getDataSource());
                jtm.update(SQLQueries.DELETE_CUSTOMER_WITHOUT_ORDERS, id);
                jtm.update(SQLQueries.DELETE_CREDENTIALS_THAT_HAS_SPECIFIC_CUSTOMER_ID, id);
                rowsAffected = 1;
                transactionManager.commit(txStatus);

            } catch (Exception e) {
                transactionManager.rollback(txStatus);
                log.error(e.getMessage());
            }
        }
        //delete Customer accepts deleting orders
        else {
            try {
                JdbcTemplate jtm = new JdbcTemplate(dbConnection.getDataSource());
                jtm.update(SQLQueries.DELETE_FROM_ORDER_ITEMS_WHERE_ORDER_ID_IN_SELECT_ORDER_ID_FROM_ORDERS_WHERE_CUSTOMER_ID, id);
                jtm.update(SQLQueries.DELETE_ORDERS_OF_SPECIFIC_CUSTOMER_ID, id);
                jtm.update(SQLQueries.DELETE_CUSTOMER_WITHOUT_ORDERS, id);
                jtm.update(SQLQueries.DELETE_CREDENTIALS_THAT_HAS_SPECIFIC_CUSTOMER_ID, id);
                rowsAffected = 1;
                transactionManager.commit(txStatus);
            } catch (DataAccessException e) {
                if(e instanceof DataIntegrityViolationException){
                    rowsAffected = 0;
                }else{
                    rowsAffected = -1;
                }
                transactionManager.rollback(txStatus);
                log.error(e.getMessage());
            }
        }
        if (rowsAffected > 0) {
            return Either.right(rowsAffected);
        } else {
            return Either.left(new ErrorC(DaoConstants.ERROR_DELETING_CUSTOMER));
        }
    }

}
