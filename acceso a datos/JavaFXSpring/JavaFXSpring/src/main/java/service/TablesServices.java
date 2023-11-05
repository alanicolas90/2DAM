package service;

import dao.TablesDao;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.ErrorC;
import model.Table;

import java.util.List;

public class TablesServices {

    private final TablesDao tablesDao;

    @Inject
    public TablesServices(TablesDao tablesDao) {
        this.tablesDao = tablesDao;
    }

    public boolean tableExists(int tableNumber) {
        Either<ErrorC, Table> eitherTableGet = tablesDao.get(tableNumber);
        return !eitherTableGet.isLeft();
    }

    public List<Integer> getAllTableNumbers() {
        Either<ErrorC, List<Table>> getAllTables = tablesDao.getAll();
        if(getAllTables.isLeft())
            return List.of();
        else
            return getAllTables.get().stream().map(Table::getId).toList();
    }
}
