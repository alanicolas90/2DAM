package dao.impl;


import dao.CustomerDao;
import dao.db.DBConnection;
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
        Connection connection = null;
        PreparedStatement preparedStatement;
        try {
            connection = dbConnection.getConnection();
            preparedStatement = connection.prepareStatement(SQLQueries.GET_CUSTOMER_SPECIFIC_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            customer = readRS(resultSet).get(0);
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            if (connection != null) {
                dbConnection.closeConnection(connection);
            }
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
        Connection connection = null;
        try {
            connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.UPDATE_CUSTOMER);
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
        } finally {
            if (connection != null) {
                dbConnection.closeConnection(connection);
            }
        }

        return Objects.requireNonNullElseGet(result, () -> Either.left(new ErrorC(DaoConstants.CUSTOMER_NOT_FOUND)));
    }

    @Override
    public Either<ErrorC, List<Customer>> getAll() {
        List<Customer> customers = new ArrayList<>();
        Connection connection = null;
        try {
            connection = dbConnection.getConnection();
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(SQLQueries.GET_ALL_CUSTOMERS);
                customers.addAll(readRS(resultSet));
            }
        } catch (SQLException e) {
            Logger.getLogger(CustomerDaoImpl.class.getName()).severe(e.getMessage());
        } finally {
            if (connection != null) {
                dbConnection.closeConnection(connection);
            }
        }
        return Either.right(customers);
    }

    @Override
    public int saveAutoIncrementalID(String name, String surname, String email, int phone, LocalDate dateOfBirth) {
        int rowsAffected = 0;
        Connection connection = null;
        try {
            connection = dbConnection.getConnection();
            rowsAffected = tryCatchAddCredentialAndCustomer(connection, rowsAffected, name, surname, email, phone, dateOfBirth);

        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            if (connection != null) {
                dbConnection.closeConnection(connection);
            }
        }
        return rowsAffected;
    }

    @Override
    public Either<ErrorC, Integer> delete(int id, boolean confirm) {
        int rowsAffected = 0;
        Connection connection = null;
        //delete customer that has no orders, if its has orders it will send back an error
        if (!confirm) {
            try {
                connection = dbConnection.getConnection();
                if (tryCatchDeleteCustomersWithoutOrders(connection, id).isLeft()) {
                    return Either.left(new ErrorC(DaoConstants.ERROR_DELETING_CUSTOMER_IN_CUSTOMER_DAO_IMPL));
                } else {
                    rowsAffected = tryCatchDeleteCustomersWithoutOrders(connection, id).get();
                }
            } catch (SQLException e) {
                log.error(e.getMessage());
            } finally {
                if (connection != null) {
                    dbConnection.closeConnection(connection);
                }
            }
            return Either.right(rowsAffected);
        }

        //delete Customer accepts deleting orders
        else {
            try {
                connection = dbConnection.getConnection();
                rowsAffected = tryCatchDeleteCustomersWithOrders(connection, id).get();
            } catch (SQLException e) {
                log.error(e.getMessage());
            } finally {
                if (connection != null) {
                    dbConnection.closeConnection(connection);
                }
            }
            if (rowsAffected < 2) {
                return Either.left(new ErrorC(DaoConstants.ERROR_DELETING_CUSTOMER));
            } else {
                return Either.right(rowsAffected);
            }
        }
    }


    //Try catch methods
    private int tryCatchAddCredentialAndCustomer(Connection connection, int rowsAffected, String name, String surname, String email, int phone, LocalDate dateOfBirth) throws SQLException {
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.INSERT_NEW_CREDENTIAL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            rowsAffected += preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            int generatedKey = 0;
            if (rs.next()) {
                generatedKey = rs.getInt(1);
            }


            rowsAffected += preparedStatementNewCustomer(generatedKey, name, surname, email, phone, dateOfBirth, connection);

            //commit all changes done
            connection.commit();
        } catch (SQLException sqle) {
            log.error(sqle.getMessage());
            tryCatchRollback(connection);
        } finally {
            if (connection != null) {
                dbConnection.closeConnection(connection);
            }
            connection.setAutoCommit(true);
        }
        return rowsAffected;
    }

    private Either<ErrorC, Integer> tryCatchDeleteCustomersWithoutOrders(Connection connection, int idCustomer) throws SQLException {
        int rowsAffected = 0;
        try {
            connection.setAutoCommit(false);

            rowsAffected += preparedStatementDeleteCustomer(idCustomer, connection);
            rowsAffected += preparedStatementDeleteCredentials(idCustomer, connection);

            connection.commit();
        } catch (SQLException e) {
            tryCatchRollback(connection);
            if (e.getErrorCode() == 1451) {
                return Either.left(new ErrorC(DaoConstants.CUSTOMER_HAS_ORDERS_ARE_YOU_SURE_YOU_WANT_TO_DELETE_IT));
            } else {
                log.error(e.getMessage());
            }
        } finally {
            if (connection != null) {
                dbConnection.closeConnection(connection);
            }
            connection.setAutoCommit(true);
        }
        return Either.right(rowsAffected);
    }

    private Either<ErrorC, Integer> tryCatchDeleteCustomersWithOrders(Connection connection, int idCustomer) throws SQLException {
        int rowsAffected = 0;
        try {
            connection.setAutoCommit(false);

            rowsAffected += preparedStatementDeleteOrdersOfSpecificCustomer(idCustomer, connection);
            rowsAffected += preparedStatementDeleteCustomer(idCustomer, connection);
            rowsAffected += preparedStatementDeleteCredentials(idCustomer, connection);

            connection.commit();
        } catch (SQLException e) {
            tryCatchRollback(connection);
            if (e.getErrorCode() == 1451) {
                return Either.left(new ErrorC(DaoConstants.CUSTOMER_HAS_ORDERS_ARE_YOU_SURE_YOU_WANT_TO_DELETE_IT));
            } else {
                log.error(e.getMessage());
            }
        } finally {
            if (connection != null) {
                dbConnection.closeConnection(connection);
            }
            connection.setAutoCommit(true);
        }
        return Either.right(rowsAffected);
    }


    //Prepared statements
    private int preparedStatementNewCustomer(int idCustomer, String name, String surname, String email, int phone, LocalDate dateOfBirth, Connection connection) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.INSERT_NEW_CUSTOMER_AUTO_INCREMENTAL);
        preparedStatement.setInt(1, idCustomer);
        preparedStatement.setString(2, name);
        preparedStatement.setString(3, surname);
        preparedStatement.setString(4, email);
        preparedStatement.setInt(5, phone);
        if (dateOfBirth == null) {
            preparedStatement.setNull(6, Types.DATE);
        } else {
            preparedStatement.setDate(6, Date.valueOf(dateOfBirth));
        }
        return preparedStatement.executeUpdate();
    }

    private int preparedStatementDeleteCustomer(int id, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.DELETE_CUSTOMER_WITHOUT_ORDERS)) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate();
        }
    }

    private int preparedStatementDeleteCredentials(int id, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.DELETE_CREDENTIALS_THAT_HAS_SPECIFIC_CUSTOMER_ID)) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate();
        }
    }

    private int preparedStatementDeleteOrdersOfSpecificCustomer(int id, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.DELETE_ORDERS_OF_SPECIFIC_CUSTOMER_ID)) {
            preparedStatement.setInt(1, id);
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
