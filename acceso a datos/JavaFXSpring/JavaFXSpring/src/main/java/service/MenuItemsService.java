package service;

import dao.MenuItemsDao;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.ErrorC;
import model.MenuItem;
import ui.screens.order.model.MenuItemTable;

import java.util.List;

public class MenuItemsService {

    private final MenuItemsDao menuItemsDao;

    @Inject
    public MenuItemsService(MenuItemsDao menuItemsDao) {
        this.menuItemsDao = menuItemsDao;
    }



    public Either<ErrorC, List<String>> getAllNames() {
        Either<ErrorC, List<MenuItem>> menuItems = menuItemsDao.getAll();
        if(menuItems.isRight()){
            List<String> names = menuItems.get().stream().map(MenuItem::getName).toList();
            return Either.right(names);
        }else{
            return Either.left(menuItems.getLeft());
        }
    }

    public int getMenuItemIdByName(String value) {
        Either<ErrorC, List<MenuItem>> menuItems = menuItemsDao.getAll();
        return menuItems.get().stream().filter(menuItem -> menuItem.getName().equals(value)).findFirst().get().getId();


    }

    public Either<ErrorC, List<MenuItemTable>> getAllMenuItems(int id) {
        return menuItemsDao.get(id);
    }

    public Double getTotalAmount(int id) {
        Either<ErrorC,List<MenuItemTable>> menuItems = menuItemsDao.get(id);
        return menuItems.get().stream().mapToDouble(MenuItemTable::getPrice).sum();
    }
}
