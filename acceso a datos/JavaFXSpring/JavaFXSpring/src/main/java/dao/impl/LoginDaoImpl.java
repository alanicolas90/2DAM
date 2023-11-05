package dao.impl;

import dao.LoginDao;
import dao.db.DBConnection;
import dao.impl.rowmappers.CredentialRowMapper;
import dao.utils.DaoConstants;
import dao.utils.SQLQueries;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import model.Credential;
import model.ErrorC;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@Log4j2
public class LoginDaoImpl implements LoginDao {

    private final DBConnection dbConnection;

    @Inject
    public LoginDaoImpl(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }


    @Override
    public Either<ErrorC, Credential> get(Credential credential) {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dbConnection.getDataSource());
            Credential credentialDB = jdbcTemplate.queryForObject(SQLQueries.GET_CREDENTIALS, new CredentialRowMapper(), credential.getUsername(), credential.getPassword());

            if(credentialDB == null){
                return Either.left(new ErrorC(DaoConstants.USER_NOT_FOUND));
            }else{
                return Either.right(credentialDB);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return Either.left(new ErrorC("Database error"));
        }
    }
}
