package dao.impl;


import io.vavr.control.Either;
import model.Coffee;
import java.util.ArrayList;
import java.util.List;


public class CoffeeDAOST implements dao.CoffeeDAO {


    @Override
    public Either<Integer, List<Coffee>> getAll() {

        Either<Integer, List<Coffee>> result;

        //Fill with data
        List<Coffee> coffeeList=new ArrayList<>();
        coffeeList.add(new Coffee("Colombian", 34, 3000, 97542));
        coffeeList.add(new Coffee("Ethiopia", 13, 15000, 67000));
        result = Either.right(coffeeList);  //readRS exception ignored!

        return result ;
    }
    @Override
    public Either<Integer, Coffee> get(int id) {
        return null;
    }

    @Override
    public int save(Coffee t) {
        return 0;
    }

    @Override
    public int update(Coffee t) {
        return 0;
    }

    @Override
    public int delete(Coffee t) {
        return 0;
    }

}
