package domain.service;

import common.constantes.Constantes;
import dao.DaoBerry;
import domain.modelo.berry.BerriesResponse;
import domain.modelo.berry.BerryResponse;
import domain.modelo.berry.ResultsItem;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.List;

public class BerriesService {

    private final DaoBerry daoBerryImpl;

    @Inject
    public BerriesService(DaoBerry daoBerryImpl) {
        this.daoBerryImpl = daoBerryImpl;
    }

    public Either<String, List<ResultsItem>> filteresBerriesByName(String nombreABuscar) {
        List<ResultsItem> listaEnteraBerries = daoBerryImpl.getAllBerriesWithIdMod().get();
        List<ResultsItem> listaConBerriesNombreSimilar = listaEnteraBerries.stream().filter(result -> result.getName().contains(nombreABuscar)).toList();
        if (listaConBerriesNombreSimilar.isEmpty()) {
            return Either.left(Constantes.NO_HAY_BERRIES);
        } else {
            return Either.right(listaConBerriesNombreSimilar);
        }
    }

    public Either<String, BerriesResponse> getAllBerriesResult() {
        if (daoBerryImpl.getAllBerriesNormal().isLeft()) {
            return Either.left(Constantes.NO_HAY_BERRIES);
        } else {
            return Either.right(daoBerryImpl.getAllBerriesNormal().get());
        }
    }

    public Either<String,BerryResponse> getSpecificBerry(int i) {
        if (daoBerryImpl.getSpecificBerry(i).isLeft()) {
            return Either.left(Constantes.NO_HAY_BERRIES);
        } else {
            return Either.right(daoBerryImpl.getSpecificBerry(i).get());
        }
    }
}

