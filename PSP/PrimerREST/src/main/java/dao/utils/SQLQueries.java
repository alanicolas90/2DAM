package dao.utils;

public class SQLQueries {
    public static final String GET_ALL_CUSTOMERS = "select * from customers";
    public static final String GET_CUSTOMER_SPECIFIC_ID = "select * from customers where id = ?";
    public static final String UPDATE_CUSTOMER = "update customers set name = ?, surname = ?, email = ?, phone = ?, date_of_birth = ? where id = ?";
    public static final String GET_CREDENTIALS = "SELECT * FROM credentials WHERE username = ? AND password = ?";
    public static final String DELETE_CUSTOMER_WITHOUT_ORDERS = "delete from customers where id = ?";
    public static final String GET_LAST_ID_CREATED_IN_CREDENTIALS = "select id from credentials order by id desc limit 1";
    public static final String INSERT_NEW_CREDENTIAL = "insert into credentials (username,password) values (?,?)";
    public static final String  INSERT_NEW_CUSTOMER = "INSERT INTO customers (`id`, `name`, `surname`, `email`, `phone`, `date_of_birth`) VALUES (?, ?, ?, ?, ?, ?);";
    public static final String DELETE_CREDENTIALS_THAT_HAS_SPECIFIC_CUSTOMER_ID = "delete from credentials where id = ?";
    public static final String DELETE_ORDERS_OF_SPECIFIC_CUSTOMER_ID = "delete from orders where customer_id = ?";
    public static final String ADD_ORDER = "INSERT INTO orders (order_date,customer_id,table_number) VALUES (?,?,?)";
    public static final String GET_ORDERS_SPECIFIC_CUSTOMER = "SELECT * FROM orders where customer_id = ";
    public static final String GET_ALL_ORDERS = "SELECT * FROM orders";
    public static final String DELETE_FROM_ORDERS_WHERE_ORDER_ID = "DELETE FROM orders WHERE order_id = ?";
    public static final String UPDATE_ORDERS = "UPDATE orders SET order_date = ?, table_number = ? WHERE order_id = ?";
    public static final String SELECT_ALL_FROM_MENU_ITEMS = "SELECT * FROM menu_items";
    public static final String SELECT_FROM_TABLES_WHERE_TABLE_NUMBER = "SELECT * FROM tables where table_number = ";
    public static final String SELECT_ALL_FROM_TABLES = "SELECT * FROM tables";
    public static final String DELETE_FROM_ORDER_ITEMS_WHERE_ORDER_ID = "DELETE FROM order_items WHERE order_id = ?";
    public static final String DELETE_FROM_ORDER_ITEMS_WHERE_ORDER_ID_IN_SELECT_ORDER_ID_FROM_ORDERS_WHERE_CUSTOMER_ID = "delete from order_items where order_id in(select order_id from orders where customer_id = ?)";
    public static final String QUERY_GET_CREDENTIALS_WHERE_USERNAME_IS = "SELECT * FROM credentials WHERE username = ?";

    private SQLQueries() {
    }


}
