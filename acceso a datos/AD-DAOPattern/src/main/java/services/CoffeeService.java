package services;

import java.util.List;

import dao.CoffeeDAO;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Coffee;

/**
 *
 * @author lucia
 */



public class CoffeeService {

    @Inject
    private CoffeeDAO dao;

    public Either<Integer, List<Coffee>> getAll() {
        return dao.getAll();
    }

    public Either<Integer, Coffee> get(int id) {
        return dao.get(id);
    }

    public int save(Coffee t) { return 0; }

    public int update(Coffee t) {return 0;}

    public int delete(Coffee t) {return 0;}

}