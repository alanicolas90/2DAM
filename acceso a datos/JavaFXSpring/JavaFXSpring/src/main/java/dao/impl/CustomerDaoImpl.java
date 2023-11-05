package dao.impl;


import dao.CustomerDao;
import dao.db.DBConnection;
import dao.impl.rowmappers.CustomerRowMapper;
import dao.utils.DaoConstants;
import dao.utils.SQLQueries;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import model.Customer;
import model.ErrorC;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.sql.*;
import java.sql.Date;
import java.util.*;
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
            Customer customer = jdbcTemplate.queryForObject(SQLQueries.GET_CUSTOMER_SPECIFIC_ID, new CustomerRowMapper());
            if (customer == null) {
                return Either.left(new ErrorC(DaoConstants.CUSTOMER_NOT_FOUND));
            } else {
                return Either.right(customer);
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
            List<Customer> customers = jdbcTemplate.query(SQLQueries.GET_ALL_CUSTOMERS, new CustomerRowMapper());

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
            SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(dbConnection.getDataSource()).withTableName("credentials").usingGeneratedKeyColumns("id").usingColumns("username", "password");
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
                log.error(e.getMessage());
            }
            if (rowsAffected > 0) {
                return Either.right(rowsAffected);
            } else {
                return Either.left(new ErrorC(DaoConstants.ERROR_DELETING_CUSTOMER));
            }
        }
        //delete Customer accepts deleting orders
        else {
            try {
                Connection connection = dbConnection.getDataSource().getConnection();
                try {
                    connection.setAutoCommit(false);
                    PreparedStatement preparedStatementDeleteOrderItems = connection.prepareStatement(SQLQueries.DELETE_FROM_ORDER_ITEMS_WHERE_ORDER_ID_IN_SELECT_ORDER_ID_FROM_ORDERS_WHERE_CUSTOMER_ID);
                    preparedStatementDeleteOrderItems.setInt(1, id);
                    rowsAffected += preparedStatementDeleteOrderItems.executeUpdate();

                    PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.DELETE_ORDERS_OF_SPECIFIC_CUSTOMER_ID);
                    preparedStatement.setInt(1, id);
                    rowsAffected += preparedStatement.executeUpdate();

                    PreparedStatement preparedStatementDeleteCustomer = connection.prepareStatement(SQLQueries.DELETE_CUSTOMER_WITHOUT_ORDERS);
                    preparedStatementDeleteCustomer.setInt(1, id);
                    rowsAffected += preparedStatementDeleteCustomer.executeUpdate();

                    PreparedStatement preparedStatementDeleteCredentials = connection.prepareStatement(SQLQueries.DELETE_CREDENTIALS_THAT_HAS_SPECIFIC_CUSTOMER_ID);
                    preparedStatementDeleteCredentials.setInt(1, id);
                    rowsAffected += preparedStatementDeleteCredentials.executeUpdate();

                    connection.commit();
                } catch (SQLException e) {
                    tryCatchRollback(connection);
                    if (e.getErrorCode() == 1451) {
                        return Either.left(new ErrorC(DaoConstants.CUSTOMER_HAS_ORDERS_ARE_YOU_SURE_YOU_WANT_TO_DELETE_IT));
                    } else {
                        log.error(e.getMessage());
                    }
                } finally {
                    connection.setAutoCommit(true);
                }
            } catch (SQLException e) {
                log.error(e.getMessage());
            }
            if (rowsAffected < 1) {
                return Either.left(new ErrorC(DaoConstants.ERROR_DELETING_CUSTOMER));
            } else {
                return Either.right(rowsAffected);
            }
        }
    }


    //Utility methods
    private void tryCatchRollback(Connection connection) {
        try {
            connection.rollback();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
