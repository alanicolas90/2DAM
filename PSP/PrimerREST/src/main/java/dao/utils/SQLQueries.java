package dao.utils;

public class SQLQueries {
    public static final String GET_ALL_CUSTOMERS = "select * from customers";
    public static final String GET_CUSTOMER_SPECIFIC_ID = "select * from customers where id = ?";
    public static final String UPDATE_CUSTOMER = "update customers set name = ? where id = ?";
    public static final String DELETE_CUSTOMER_WITHOUT_ORDERS = "delete from customers where id = ?";
    public static final String ADD_ORDER = "INSERT INTO orders (item_name,quantity,customerId) VALUES (?,?,?)";
    public static final String GET_ORDERS_SPECIFIC_CUSTOMER = "SELECT * FROM orders where customerId = ?";
    public static final String GET_ALL_ORDERS = "SELECT * FROM orders";
    public static final String DELETE_FROM_ORDERS_WHERE_ORDER_ID = "DELETE FROM orders WHERE order_id = ?";
    public static final String UPDATE_ORDERS = "UPDATE orders SET item_name = , quantity = ? WHERE order_id = ?";

    private SQLQueries() {
    }


}
