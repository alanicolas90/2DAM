package dao.deprecated.impl;



import dao.DBConnection;
import dao.deprecated.CustomerDao;
import dao.utils.DaoConstants;
import dao.utils.SQLQueries;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import model.Customer;
import model.ErrorC;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
        Customer customer = null;
        try (Connection connection = dbConnection.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.GET_CUSTOMER_SPECIFIC_ID);) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            customer = readRS(resultSet).get(0);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        if (customer == null) {
            return Either.left(new ErrorC(DaoConstants.CUSTOMER_NOT_FOUND));
        } else {
            return Either.right(customer);
        }
    }

    @Override
    public Either<ErrorC, Integer> update(Customer customerUpdated) {
        Either<ErrorC, Integer> result = null;
        try (Connection connection = dbConnection.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.UPDATE_CUSTOMER);) {

            preparedStatement.setString(1, customerUpdated.getName());
            preparedStatement.setString(2, customerUpdated.getSurname());
            preparedStatement.setString(3, customerUpdated.getEmail());
            preparedStatement.setInt(4, customerUpdated.getPhone());
            if (customerUpdated.getBirthDate() == null) {
                preparedStatement.setNull(5, Types.DATE);
            } else {
                preparedStatement.setDate(5, Date.valueOf(customerUpdated.getBirthDate()));
            }
            preparedStatement.setInt(6, customerUpdated.getId());
            int rowsAffected = preparedStatement.executeUpdate();
            result = Either.right(rowsAffected);

        } catch (SQLException e) {
            log.error(e.getMessage());
        }

        return Objects.requireNonNullElseGet(result, () -> Either.left(new ErrorC(DaoConstants.CUSTOMER_NOT_FOUND)));
    }

    @Override
    public Either<ErrorC, List<Customer>> getAll() {
        List<Customer> customers = new ArrayList<>();
        try (Connection connection = dbConnection.getDataSource().getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(SQLQueries.GET_ALL_CUSTOMERS);
            customers.addAll(readRS(resultSet));

        } catch (SQLException e) {
            Logger.getLogger(CustomerDaoImpl.class.getName()).severe(e.getMessage());
        }
        return Either.right(customers);
    }

    @Override
    public Either<ErrorC, Integer> save(Customer customer) {
        int rowsAffected = 0;
        try(Connection connection = dbConnection.getDataSource().getConnection()) {
            rowsAffected = tryCatchAddCredentialAndCustomer(connection, rowsAffected, customer);
            if (rowsAffected == 0) {
                return Either.left(new ErrorC("Error adding customer"));
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return Either.left(new ErrorC("Error adding customer"));
        }
        return Either.right(rowsAffected);
    }

    @Override
    public Either<ErrorC, Integer> delete(int id, boolean confirm) {
        int rowsAffected = 0;
        //delete customer that has no orders, if its has orders it will send back an error
        if (!confirm) {
            try(Connection connection = dbConnection.getDataSource().getConnection()) {
                connection.setAutoCommit(false);

                try(PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.DELETE_CUSTOMER_WITHOUT_ORDERS);
                    PreparedStatement preparedStatement2 = connection.prepareStatement(SQLQueries.DELETE_CREDENTIALS_THAT_HAS_SPECIFIC_CUSTOMER_ID);) {

                    preparedStatement.setInt(1, id);
                    preparedStatement2.setInt(1, id);

                    rowsAffected += preparedStatement.executeUpdate();
                    rowsAffected += preparedStatement2.executeUpdate();

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
            if (rowsAffected > 0) {
                return Either.right(rowsAffected);
            } else {
                return Either.left(new ErrorC(DaoConstants.ERROR_DELETING_CUSTOMER));
            }
        }
        //delete Customer accepts deleting orders
        else {
            try(Connection connection = dbConnection.getDataSource().getConnection()) {
                connection.setAutoCommit(false);
                try(PreparedStatement preparedStatementDeleteOrderItems = connection.prepareStatement(SQLQueries.DELETE_FROM_ORDER_ITEMS_WHERE_ORDER_ID_IN_SELECT_ORDER_ID_FROM_ORDERS_WHERE_CUSTOMER_ID);
                    PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.DELETE_ORDERS_OF_SPECIFIC_CUSTOMER_ID);
                    PreparedStatement preparedStatementDeleteCustomer = connection.prepareStatement(SQLQueries.DELETE_CUSTOMER_WITHOUT_ORDERS);
                    PreparedStatement preparedStatementDeleteCredentials = connection.prepareStatement(SQLQueries.DELETE_CREDENTIALS_THAT_HAS_SPECIFIC_CUSTOMER_ID);
                ) {
                     preparedStatementDeleteOrderItems.setInt(1, id);
                    rowsAffected += preparedStatementDeleteOrderItems.executeUpdate();

                    preparedStatement.setInt(1, id);
                    rowsAffected += preparedStatement.executeUpdate();

                    preparedStatementDeleteCustomer.setInt(1, id);
                    rowsAffected += preparedStatementDeleteCustomer.executeUpdate();

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


    //Try catch methods
    private int tryCatchAddCredentialAndCustomer(Connection connection, int rowsAffected, Customer customer) throws SQLException {
        try(PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.INSERT_NEW_CREDENTIAL, Statement.RETURN_GENERATED_KEYS);
        ) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, customer.getCredential().getUsername());
            preparedStatement.setString(2, customer.getCredential().getPassword());

            rowsAffected += preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            int generatedKey = 0;
            if (rs.next()) {
                generatedKey = rs.getInt(1);
            }

            rowsAffected += preparedStatementNewCustomer(generatedKey, customer, connection);

            //commit all changes done
            connection.commit();
        } catch (SQLException sqle) {
            log.error(sqle.getMessage());
            tryCatchRollback(connection);
        } finally {
            connection.setAutoCommit(true);
        }
        return rowsAffected;
    }


    //Prepared statements
    private int preparedStatementNewCustomer(int idCustomer, Customer customer, Connection connection) throws SQLException {

        try (PreparedStatement preparedStatement = connection
                .prepareStatement("INSERT INTO customers (`id`, `name`, `surname`, `email`, `phone`, `date_of_birth`) VALUES (?, ?, ?, ?, ?, ?);")) {
            preparedStatement.setInt(1, idCustomer);
            preparedStatement.setString(2, customer.getName());
            preparedStatement.setString(3, customer.getSurname());
            preparedStatement.setString(4, customer.getEmail());
            preparedStatement.setInt(5, customer.getPhone());
            if (customer.getBirthDate() == null) {
                preparedStatement.setNull(6, Types.DATE);
            } else {
                preparedStatement.setDate(6, Date.valueOf(customer.getBirthDate()));
            }
            return preparedStatement.executeUpdate();
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

    private List<Customer> readRS(ResultSet resultSet) {
        List<Customer> customers = new ArrayList<>();
        try {
            while (resultSet.next()) {

                int customerID = resultSet.getInt(DaoConstants.ID);
                String customerName = resultSet.getString(DaoConstants.NAME);
                String customerSurname = resultSet.getString(DaoConstants.SURNAME);
                String customerEmail = resultSet.getString(DaoConstants.EMAIL);
                int customerPhone = resultSet.getInt(DaoConstants.PHONE);
                LocalDate customerBirthDate;
                if (resultSet.getTimestamp(DaoConstants.DATE_OF_BIRTH) != null) {
                    customerBirthDate = resultSet.getTimestamp(DaoConstants.DATE_OF_BIRTH).toLocalDateTime().toLocalDate();
                } else {
                    customerBirthDate = null;
                }
                Customer customer = new Customer(customerID, customerName, customerSurname, customerEmail, customerPhone, customerBirthDate);
                customers.add(customer);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            log.error(e.getStackTrace());
        }
        return customers;
    }
}
