package dao.impl;

import dao.DBConnection;
import dao.MenuItemsDao;
import dao.utils.DaoConstants;
import dao.utils.SQLQueries;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import model.Customer;
import model.ErrorC;
import model.MenuItem;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import ui.screens.order.model.MenuItemTable;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class MenuItemsDaoImpl implements MenuItemsDao {

    private final DBConnection dbConnection;

    @Inject
    public MenuItemsDaoImpl(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public Either<ErrorC, List<MenuItem>> getAll() {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dbConnection.getDataSource());
            List<MenuItem> menuItems = jdbcTemplate.query(SQLQueries.SELECT_ALL_FROM_MENU_ITEMS,
                    new BeanPropertyRowMapper<>(MenuItem.class));
            if (menuItems.isEmpty()) {
                return Either.left(new ErrorC(DaoConstants.NO_MENU_ITEMS_FOUND));
            } else {
                return Either.right(menuItems);
            }

        } catch (Exception e) {
            log.error(e.getMessage());
            return Either.left(new ErrorC(DaoConstants.SQL_ERROR_GETTING_MENU_ITEMS));
        }
    }

    @Override
    public Either<ErrorC, List<MenuItemTable>> get(int idOrder) {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dbConnection.getDataSource());
            List<MenuItemTable> menuItems = jdbcTemplate
                    .query("Select o.order_id, mi.name, mi.price, oi.quantity from menu_items as mi\n" +
                                    "    join order_items as oi on mi.id = oi.menu_item_id\n" +
                                    "    join orders as o on oi.order_id = o.order_id\n" +
                                    "    where oi.order_id = ?",
                    new BeanPropertyRowMapper<>(MenuItemTable.class),
                    idOrder);

            menuItems.forEach(menuItemTable -> menuItemTable.setTotal(menuItemTable.getPrice() * menuItemTable.getQuantity()));

            return Either.right(menuItems);

        }catch (Exception e){
            return Either.left(new ErrorC(DaoConstants.SQL_ERROR_GETTING_MENU_ITEMS));
        }
    }

    @Override
    public Either<ErrorC, MenuItem> get(String nameMenuItem) {
        try{
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dbConnection.getDataSource());
            MenuItem menuItem = jdbcTemplate
                    .queryForObject("Select * from menu_items where name = ?",
                            new BeanPropertyRowMapper<>(MenuItem.class),
                            nameMenuItem);

            if(menuItem == null){
                return Either.left(new ErrorC(DaoConstants.NO_MENU_ITEMS_FOUND));
            }else{
                return Either.right(menuItem);
            }
        }catch(Exception e){
            return Either.left(new ErrorC(DaoConstants.SQL_ERROR_GETTING_MENU_ITEMS));
        }
    }

    private List<MenuItem> readRS(ResultSet resultSet) {
        List<MenuItem> menuItems = new ArrayList<>();
        try {
            while (resultSet.next()) {
                int id = resultSet.getInt(DaoConstants.ID);
                String name = resultSet.getString(DaoConstants.NAME);
                String description = resultSet.getString(DaoConstants.DESCRIPTION);
                double price = resultSet.getDouble(DaoConstants.PRICE);
                menuItems.add(new MenuItem(id, name, description, price));
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }

        return menuItems;
    }

}
