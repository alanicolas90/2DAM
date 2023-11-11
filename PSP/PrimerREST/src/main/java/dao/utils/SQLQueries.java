package dao.utils;

public class SQLQueries {
    public static final String GET_ALL_CUSTOMERS = "select * from customers";
    public static final String GET_CUSTOMER_SPECIFIC_ID = "select * from customers where id = ?";
    public static final String UPDATE_CUSTOMER = "update customers set name = ? where id = ?";
    public static final String DELETE_CUSTOMER_WITHOUT_ORDERS = "delete from customers where id = ?";
    public static final String ADD_ORDER = "INSERT INTO orders (item_name,quantity,customerId) VALUES (?,?,?)";
    public static final String GET_ALL_ORDERS = "SELECT * FROM orders";
    public static final String DELETE_FROM_ORDERS_WHERE_ORDER_ID = "DELETE FROM orders WHERE id = ?";
    public static final String INSERT_INTO_CUSTOMERS_NAME_VALUES = "INSERT INTO customers (`name`,`birthDate`) VALUES (?,?);";
    public static final String UPDATE_ORDERS_SET_ITEM_NAME_QUANTITY_CUSTOMER_ID_WHERE_ID = "UPDATE orders SET item_name = ?, quantity = ?, customerId = ? WHERE id = ?";
    public static final String GET_ORDER = "SELECT * FROM orders WHERE id = ?";

    private SQLQueries() {
    }


}
