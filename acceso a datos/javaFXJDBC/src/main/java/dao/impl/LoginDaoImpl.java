package dao.impl;

import dao.LoginDao;
import dao.db.DBConnection;
import dao.utils.DaoConstants;
import dao.utils.SQLQueries;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import model.Credential;
import model.ErrorC;

import java.sql.*;


@Log4j2
public class LoginDaoImpl implements LoginDao {

    private final DBConnection dbConnection;

    @Inject
    public LoginDaoImpl(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }


    @Override
    public Either<ErrorC, Credential> login(String username, String password) {
        Credential credential = null;
        try {
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.GET_CREDENTIALS);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            credential = readRS(resultSet);

            if (credential == null) {
                return Either.left(new ErrorC(DaoConstants.USER_NOT_FOUND));
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return Either.right(credential);
    }

    private Credential readRS(ResultSet resultSet) {
        Credential credential = null;
        try {
            if (resultSet.next()) {
                int id = resultSet.getInt(DaoConstants.ID);
                String username = resultSet.getString(DaoConstants.USERNAME);
                String password = resultSet.getString(DaoConstants.PASSWORD);
                boolean priviledged = id < 0;
                credential = new Credential(id, username, password, priviledged);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return credential;
    }
}
