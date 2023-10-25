package service;

import dao.MenuItemsDao;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.ErrorC;
import model.MenuItem;
import service.utils.ServiceConstants;

import java.util.List;

public class MenuItemsService {

    private final MenuItemsDao menuItemsDao;

    @Inject
    public MenuItemsService(MenuItemsDao menuItemsDao) {
        this.menuItemsDao = menuItemsDao;
    }


    public Either<ErrorC, List<String>> getAllNames() {
        Either<ErrorC, List<MenuItem>> menuItems = menuItemsDao.getAll();
        List<String> names = menuItems.get().stream().map(MenuItem::getItemName).toList();
        return Either.right(names);
    }
}
