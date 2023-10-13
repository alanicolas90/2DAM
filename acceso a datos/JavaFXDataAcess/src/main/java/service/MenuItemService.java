package service;

import dao.MenuItemsDao;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.ErrorC;

import java.util.List;

public class MenuItemService {

    private final MenuItemsDao menuItemsDao;

    @Inject
    public MenuItemService(MenuItemsDao menuItemsDao) {
        this.menuItemsDao = menuItemsDao;
    }

    public Either<ErrorC, List<String>> getAll() {
        List<String> allOptions = menuItemsDao.getAll().get();
        if(allOptions.isEmpty()){
            return Either.left(new ErrorC("No options found"));
        }
        return Either.right(allOptions);
    }
}
