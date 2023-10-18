package dao.utils;

public class SQLQueries {
    public static final String ADD_CUSTOMER = "insert into customer (name, surname, email, phone, date_of_birth) values (?,?,?,?,?)";
    public static final String GET_ALL_CUSTOMERS = "select * from customer";
    public static final String DELETE_CUSTOMER = "delete from customer where id = ?";
    public static final String UPDATE_NAME_CUSTOMER = "UPDATE `alanmikolajczyk_restaurant`.`customer` SET `name` = ? WHERE (`id` = ?);";
    public static final String UPDATE_SURNAME_CUSTOMER = "UPDATE `alanmikolajczyk_restaurant`.`customer` SET `surname` = ? WHERE (`id` = ?);";
    public static final String UPDATE_PHONE_CUSTOMER = "UPDATE `alanmikolajczyk_restaurant`.`customer` SET `phone` = ? WHERE (`id` = ?);";
    public static final String UPDATE_EMAIL_CUSTOMER = "UPDATE `alanmikolajczyk_restaurant`.`customer` SET `email` = ? WHERE (`id` = ?);";
    public static final String UPDATE_BIRTH_CUSTOMER = "UPDATE `alanmikolajczyk_restaurant`.`customer` SET `date_of_birth` = ? WHERE (`id` = ?);";

    private SQLQueries() {
    }


}
