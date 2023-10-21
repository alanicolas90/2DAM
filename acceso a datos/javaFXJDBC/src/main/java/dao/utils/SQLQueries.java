package dao.utils;

public class SQLQueries {
    public static final String GET_ALL_CUSTOMERS = "select * from customers";
    public static final String GET_CUSTOMER_SPECIFIC_ID = "select * from customer where id = ?";
    public static final String UPDATE_CUSTOMER = "update customers set first_name = ?, last_name = ?, email = ?, phone = ?, date_of_birth = ? where id = ?";

    private SQLQueries() {
    }


}
