/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;

import io.vavr.control.Either;
import model.Coffee;

/**
 *
 * @author Lucia
 */

public interface CoffeeDAO {

    Either<Integer, List<Coffee>> getAll();

    Either<Integer, Coffee> get(int id);
     
    int save(Coffee t);
     
    int update(Coffee t);
     
    int delete(Coffee t);
}
