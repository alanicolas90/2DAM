package dao.impl;

import dao.CredentialsDao;
import dao.DBConnection;
import dao.utils.SQLQueries;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.JdbcTemplate;

@Log4j2
public class CredentialsDaoImpl implements CredentialsDao {

    private final DBConnection dbConnection;

    @Inject
    public CredentialsDaoImpl(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public Either<Integer, Boolean> usernameExists(String username) {
        try {
            JdbcTemplate jtm = new JdbcTemplate(dbConnection.getDataSource());
            boolean exists = Boolean.TRUE.equals(jtm.queryForObject(SQLQueries.QUERY_GET_CREDENTIALS_WHERE_USERNAME_IS,
                    new Object[]{username}, (rs, rowNum) ->
                            rs.getString("username").equals(username)));
            if(exists){
                return Either.right(true);
            }else{
                return Either.left(1);
            }

        } catch (Exception e) {
            log.error(e.getMessage());
            return Either.left(1);
        }
    }
}
