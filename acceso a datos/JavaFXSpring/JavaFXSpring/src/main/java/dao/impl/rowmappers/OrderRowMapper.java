package dao.impl.rowmappers;

import model.Order;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRowMapper implements RowMapper<Order> {

    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        Order order = new Order();
        order.setId(rs.getInt("order_id"));
        order.setDate(rs.getTimestamp("order_date").toLocalDateTime());
        order.setCustomerId(rs.getInt("customer_id"));
        order.setTableNumber(rs.getInt("table_number"));
        return order;
    }
}
