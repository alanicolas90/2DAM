package dao;

import domain.modelo.berry.BerriesResponse;
import domain.modelo.berry.BerryResponse;
import domain.modelo.berry.ResultsItem;
import io.vavr.control.Either;

import java.util.List;

public interface DaoBerry {
    Either<String, List<ResultsItem>> getAllBerriesWithIdMod();

    Either<String, BerriesResponse> getAllBerriesNormal();

    Either<String, BerryResponse> getSpecificBerry(Integer idBerry);
}
