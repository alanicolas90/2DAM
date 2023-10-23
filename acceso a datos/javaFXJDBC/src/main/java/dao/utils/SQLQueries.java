package dao.utils;

public class SQLQueries {
    public static final String GET_ALL_CUSTOMERS = "select * from customers";
    public static final String GET_CUSTOMER_SPECIFIC_ID = "select * from customers where id = ?";
    public static final String UPDATE_CUSTOMER = "update customers set name = ?, surname = ?, email = ?, phone = ?, date_of_birth = ? where id = ?";
    public static final String GET_CREDENTIALS = "SELECT * FROM credentials WHERE username = ? AND password = ?";
    public static final String DELETE_CUSTOMER_WITHOUT_ORDERS = "delete from customers where id = ?";
    public static final String GET_LAST_ID_CREATED_IN_CREDENTIALS = "select id from credentials order by id desc limit 1";
    public static final String INSERT_NEW_CREDENTIAL = "insert into credentials (username,password) values (?,?)";
    public static final String INSERT_NEW_CUSTOMER_AUTO_INCREMENTAL = "INSERT INTO customers (`id`, `name`, `surname`, `email`, `phone`, `date_of_birth`) VALUES (?, ?, ?, ?, ?, ?);";
    public static final String DELETE_CREDENTIALS_THAT_HAS_SPECIFIC_CUSTOMER_ID = "delete from credentials where id = ?";
    public static final String DELETE_ORDERS_OF_SPECIFIC_CUSTOMER_ID = "delete from orders where customer_id = ?";
    public static final String ADD_ORDER = "INSERT INTO orders (order_date,customer_id,table_number) VALUES (?,?,?)";
    public static final String GET_ORDERS_SPECIFIC_CUSTOMER = "SELECT * FROM orders where customer_id = ";
    public static final String GET_ALL_ORDERS = "SELECT * FROM orders";

    private SQLQueries() {
    }


}
