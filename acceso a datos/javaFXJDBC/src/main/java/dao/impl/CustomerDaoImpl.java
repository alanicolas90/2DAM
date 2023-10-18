package dao.impl;


import dao.CustomerDao;
import dao.db.DBConnection;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import model.Customer;
import model.ErrorC;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


@Log4j2
public class CustomerDaoImpl implements CustomerDao {

    private DBConnection dbConnection;

    @Inject
    public CustomerDaoImpl(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public Either<ErrorC, List<Customer>> getAll() {
        List<Customer> customers = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        try {
            connection = dbConnection.getConnection();
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = statement.executeQuery("select * from customer");
            customers.addAll(readRS(resultSet));
        } catch (SQLException e) {
            Logger.getLogger(CustomerDaoImpl.class.getName()).severe(e.getMessage());
        } finally {
            dbConnection.closeConnection(connection);
            dbConnection.releaseResource(statement);
        }
        return Either.right(customers);
    }

    @Override
    public int saveAutoIncrementalID(String name, String surname, String email, int phone, LocalDate dateOfBirth){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int rowsAffected=0;
        try{
            connection = dbConnection.getConnection();
            preparedStatement = connection.prepareStatement("insert into customer (name, surname, email, phone, date_of_birth) values (?,?,?,?,?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            preparedStatement.setString(3, email);
            preparedStatement.setInt(4, phone);
            if(dateOfBirth == null){
                preparedStatement.setNull(5, Types.DATE);
            }else{
                preparedStatement.setDate(5, Date.valueOf(dateOfBirth));
            }
            rowsAffected = preparedStatement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            dbConnection.closeConnection(connection);
            dbConnection.releaseResource(preparedStatement);
        }
        return rowsAffected;
    }

    @Override
    public int delete(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int rowsAffected=0;
        try{
            connection = dbConnection.getConnection();
            preparedStatement = connection.prepareStatement("delete from customer where id = ?");
            preparedStatement.setInt(1, id);
            rowsAffected = preparedStatement.executeUpdate();

        }catch(SQLException e){
            log.error(e.getMessage());
        }
        return rowsAffected;
    }

    private List<Customer> readRS(ResultSet resultSet) {
        List<Customer> customers = new ArrayList<>();
        try {
            while (resultSet.next()) {

                int customerID = resultSet.getInt("id");
                String customerName = resultSet.getString("name");
                String customerSurname = resultSet.getString("surname");
                String customerEmail = resultSet.getString("email");
                int customerPhone = resultSet.getInt("phone");
                LocalDate customerBirthDate;
                if (resultSet.getTimestamp("date_of_birth") != null) {
                    customerBirthDate = resultSet.getTimestamp("date_of_birth").toLocalDateTime().toLocalDate();
                } else {
                    customerBirthDate = null;
                }
                Customer customer = new Customer(customerID, customerName, customerSurname, customerEmail, customerPhone, customerBirthDate);
                customers.add(customer);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }catch (Exception expection){
            log.error(expection.getMessage());
            log.error(expection.getStackTrace());
        }
        return customers;
    }
}
