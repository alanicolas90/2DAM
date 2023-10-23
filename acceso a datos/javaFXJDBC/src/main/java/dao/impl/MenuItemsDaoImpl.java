package dao.impl;

import dao.MenuItemsDao;
import dao.db.DBConnection;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import model.ErrorC;
import model.MenuItem;

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
        try {
            Connection connection = dbConnection.getConnection();
            Statement statement = connection.createStatement();
            statement.executeQuery("SELECT * FROM menu_items");
            ResultSet resultSet = statement.getResultSet();
            menuItems = readRS(resultSet);
        } catch (SQLException e) {
            log.error(e.getMessage());
            return Either.left(new ErrorC("SQL error getting menu items"));
        }
        if(menuItems.isEmpty()){
            return Either.left(new ErrorC("No menu items found"));
        }else{
            return Either.right(menuItems);
        }
    }

    private List<MenuItem> readRS(ResultSet resultSet) {
        List<MenuItem> menuItems = new ArrayList<>();
        try {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                double price = resultSet.getDouble("price");
                menuItems.add(new MenuItem(id, name, description, price));
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }

        return menuItems;
    }

}
