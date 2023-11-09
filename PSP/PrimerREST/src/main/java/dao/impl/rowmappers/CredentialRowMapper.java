package dao.impl.rowmappers;


import dao.model.Credential;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CredentialRowMapper implements RowMapper<Credential> {
    @Override
    public Credential mapRow(ResultSet rs, int rowNum) throws SQLException {
        Credential credential = new Credential();
        credential.setPrivilege(rs.getInt("id") < 0);
        credential.setIdCustomer(rs.getInt("id"));
        credential.setUsername(rs.getString("username"));
        return credential;
    }
}
