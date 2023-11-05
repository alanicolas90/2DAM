package dao.impl;

import dao.CredentialsDao;
import dao.db.DBConnection;
import dao.utils.SQLQueries;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import model.ErrorC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Log4j2
public class CredentialsDaoImpl implements CredentialsDao {

    private final DBConnection dbConnection;

    @Inject
    public CredentialsDaoImpl(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public Either<ErrorC, Boolean> usernameExists(String username) {
        boolean exists = false;
        try {
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.QUERY_GET_CREDENTIALS_WHERE_USERNAME_IS);
            preparedStatement.setString(1, username);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            if (resultSet.next() && resultSet.getString("username").equals(username))
                exists = true;

        } catch (SQLException e) {
            log.error(e.getMessage());
            return Either.left(new ErrorC(e.getMessage()));
        }

        if (exists) {
            return Either.right(true);
        } else {
            return Either.left(new ErrorC("Username does not exist"));
        }
    }
}
