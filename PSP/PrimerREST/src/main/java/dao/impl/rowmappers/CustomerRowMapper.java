package dao.impl.rowmappers;


import dao.model.Customer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRowMapper implements RowMapper<Customer>{
    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Customer customer = new Customer();
        customer.setId(rs.getInt("id"));
        customer.setName(rs.getString("name"));
        customer.setSurname(rs.getString("surname"));
        customer.setPhone(rs.getInt("phone"));
        customer.setEmail(rs.getString("email"));
        customer.setBirthDate(rs.getDate("date_of_birth").toLocalDate());
        return customer;
    }
}
