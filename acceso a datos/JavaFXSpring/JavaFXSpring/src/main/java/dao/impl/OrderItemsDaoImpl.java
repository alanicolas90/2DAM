package dao.impl;

import dao.DBConnection;
import dao.OrderItemsDao;
import dao.impl.rowmappers.OrderItemRowMapper;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import model.ErrorC;
import model.OrderItem;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class OrderItemsDaoImpl implements OrderItemsDao {

    private final DBConnection dbConnection;

    @Inject
    public OrderItemsDaoImpl(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }


    @Override
    public Either<ErrorC, List<OrderItem>> get(int orderId) {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dbConnection.getDataSource());
            List<OrderItem> orderItems = jdbcTemplate
                    .query("SELECT * FROM order_items WHERE order_id = " + orderId,
                            new OrderItemRowMapper());

            if (orderItems.isEmpty())
                return Either.left(new ErrorC("No order items found"));
            else
                return Either.right(orderItems);

        } catch (Exception e) {
            log.error(e.getMessage());
            return Either.left(new ErrorC("Error getting order items"));
        }
    }

    @Override
    public Either<ErrorC, List<OrderItem>> getAll() {
        try{
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dbConnection.getDataSource());
            List<OrderItem>orderItems = jdbcTemplate.query("SELECT * FROM order_items", new OrderItemRowMapper());

            if(orderItems.isEmpty()){
                return Either.left(new ErrorC("No order items found"));
            }else{
                return Either.right(orderItems);
            }
        }catch(Exception e){
            log.error(e.getMessage());
            return Either.left(new ErrorC("Error in querry"));
        }
    }

}
