package dao.impl;

import dao.DBConnection;
import dao.MenuItemsDao;
import dao.utils.DaoConstants;
import dao.utils.SQLQueries;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import model.ErrorC;
import model.MenuItem;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import ui.screens.order.model.MenuItemTable;

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
                    .query(SQLQueries.QUERRY_GET_MENU_ITEMS_FOR_TABLE,
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
                    .queryForObject(SQLQueries.SELECT_FROM_MENU_ITEMS_WHERE_NAME,
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


}
