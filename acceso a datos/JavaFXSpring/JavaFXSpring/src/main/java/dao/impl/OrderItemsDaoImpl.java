package dao.impl;

import dao.DBConnection;
import dao.OrderItemsDao;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import model.ErrorC;
import model.OrderItem;

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
        List<OrderItem> orderItems;
        try(Connection connection = dbConnection.getDataSource().getConnection();
            Statement statement = connection.createStatement()){

            ResultSet rs = statement.executeQuery("SELECT * FROM order_items WHERE order_id = " + orderId);
            orderItems = readRS(rs);

        }catch (SQLException e){
            log.error(e.getMessage());
            return Either.left(new ErrorC("Error getting order items"));
        }
        if(orderItems.isEmpty())
            return Either.left(new ErrorC("No order items found"));
        else
            return Either.right(orderItems);
    }

    private List<OrderItem> readRS(ResultSet rs) {
        List<OrderItem> orderItems = new ArrayList<>();
        try{
            while (rs.next()){
                OrderItem orderItem = new OrderItem();
                orderItem.setId(rs.getInt("id"));
                orderItem.setOrderId(rs.getInt("order_id"));
                orderItem.setMenuItemId(rs.getInt("menu_item_id"));
                orderItem.setQuantity(rs.getInt("quantity"));
                orderItems.add(orderItem);
            }
        }catch (SQLException e){
            log.error(e.getMessage());
        }
        return orderItems;
    }
}
