package dao.impl;

import dao.DBConnection;
import dao.OrderItemsDao;
import dao.impl.rowmappers.OrderItemRowMapper;
import dao.utils.DaoConstants;
import dao.utils.SQLQueries;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import model.ErrorC;
import model.OrderItem;
import org.springframework.jdbc.core.JdbcTemplate;

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
                    .query(SQLQueries.SQL_QUERRY_GET_BY_NAME + orderId,
                            new OrderItemRowMapper());

            if (orderItems.isEmpty())
                return Either.left(new ErrorC(DaoConstants.NO_ORDER_ITEMS_FOUND));
            else
                return Either.right(orderItems);

        } catch (Exception e) {
            log.error(e.getMessage());
            return Either.left(new ErrorC(DaoConstants.ERROR_GETTING_ORDER_ITEMS));
        }
    }

    @Override
    public Either<ErrorC, List<OrderItem>> getAll() {
        try{
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dbConnection.getDataSource());
            List<OrderItem>orderItems = jdbcTemplate.query(SQLQueries.SELECT_FROM_ORDER_ITEMS, new OrderItemRowMapper());

            if(orderItems.isEmpty()){
                return Either.left(new ErrorC(DaoConstants.NO_ORDER_ITEMS_FOUND));
            }else{
                return Either.right(orderItems);
            }
        }catch(Exception e){
            log.error(e.getMessage());
            return Either.left(new ErrorC(DaoConstants.ERROR_IN_QUERRY));
        }
    }

}
