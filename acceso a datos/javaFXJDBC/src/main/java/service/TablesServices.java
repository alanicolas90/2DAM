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

//    public List<Integer> allTableNumbers(){
//        Either<ErrorC,List<Table>> eitherTables = tablesDao.getAll();
//        if(eitherTables.isLeft()){
//            return List.of();
//        }else{
//            return eitherTables.get().stream().map(Table::getId).toList();
//        }
//    }

    public boolean tableExists(int tableNumber) {
        Either<ErrorC, Table> eitherTableGet = tablesDao.get(tableNumber);
        return !eitherTableGet.isLeft();
    }
}
