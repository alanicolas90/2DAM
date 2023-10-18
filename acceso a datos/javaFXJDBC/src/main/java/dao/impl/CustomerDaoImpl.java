package dao.impl;


import dao.CustomerDao;
import dao.db.DBConnection;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Customer;
import model.ErrorC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

public class CustomerDaoImpl implements CustomerDao {

    private DBConnection dbConnection;

    @Inject
    public CustomerDaoImpl(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public Either<ErrorC, List<Customer>> getAll() {
        try (Connection connection = dbConnection.getConnection()) {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = statement.executeQuery("select * from customer");
            readRS(resultSet);
            return null;
        } catch (SQLException e) {
            Logger.getLogger(CustomerDaoImpl.class.getName()).severe(e.getMessage());
        } finally {
            dbConnection.closeConnection(null);
        }

        return null;
    }

    private void readRS(ResultSet resultSet) {
        try {
            while (resultSet.next()) {
                int customerID = resultSet.getInt("id");
                String customerName = resultSet.getString("name");
                String customerSurname = resultSet.getString("surname");
                LocalDate customerBirthDate;
                if(resultSet.getTimestamp("dateofbirth") != null) {
                    customerBirthDate = resultSet.getTimestamp("dateofbirth").toLocalDateTime().toLocalDate();
                }else{
                    customerBirthDate = null;
                }
                String customerEmail = resultSet.getString("email");
                int customerPhone = resultSet.getInt("phone");
                System.out.println(customerID + ", " + customerName + ", " + customerSurname + ", " + customerBirthDate +
                        ", " + customerEmail + ", " + customerPhone);

            }
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}
