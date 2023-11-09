package dao.impl;

import dao.DBConnection;
import dao.MenuItemsDao;
import dao.model.ErrorC;
import dao.model.MenuItem;
import dao.utils.DaoConstants;
import dao.utils.SQLQueries;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
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
        List<MenuItem> menuItems;
        try(Connection connection = dbConnection.getDataSource().getConnection();
            Statement statement = connection.createStatement()) {

            statement.executeQuery(SQLQueries.SELECT_ALL_FROM_MENU_ITEMS);
            ResultSet resultSet = statement.getResultSet();
            menuItems = readRS(resultSet);
        } catch (SQLException e) {
            log.error(e.getMessage());
            return Either.left(new ErrorC(DaoConstants.SQL_ERROR_GETTING_MENU_ITEMS));
        }
        if (menuItems.isEmpty()) {
            return Either.left(new ErrorC(DaoConstants.NO_MENU_ITEMS_FOUND));
        } else {
            return Either.right(menuItems);
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
