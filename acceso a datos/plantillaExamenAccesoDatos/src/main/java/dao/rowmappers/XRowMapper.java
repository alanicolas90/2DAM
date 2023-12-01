package dao.rowmappers;

import model.X;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class XRowMapper implements RowMapper<X> {
    @Override
    public X mapRow(ResultSet rs, int rowNum) throws SQLException {
        X x = new X();
        x.setId(rs.getInt("id"));
        x.setName(rs.getString("name"));
        return x;
    }


}
