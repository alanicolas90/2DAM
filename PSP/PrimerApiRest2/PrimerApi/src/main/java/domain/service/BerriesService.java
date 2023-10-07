package domain.service;

import dao.DaoBerry;
import domain.modelo.berry.BerriesResponse;
import domain.modelo.berry.BerryResponse;
import domain.modelo.berry.Flavor;
import domain.modelo.berry.ResultsItem;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.List;

public class BerriesService {

    private final DaoBerry daoBerry;

    @Inject
    public BerriesService(DaoBerry daoBerry) {
        this.daoBerry = daoBerry;
    }

    public Either<String, List<ResultsItem>> filteresBerriesByName(String nombreABuscar) {
        List<ResultsItem> listaEnteraBerries = daoBerry.getAllBerriesWithIdMod().get();
        List<ResultsItem> listaConBerriesNombreSimilar = listaEnteraBerries.stream().filter(result -> result.getName().contains(nombreABuscar)).toList();
        if (listaConBerriesNombreSimilar.isEmpty()) {
            return Either.left("No hay Berries");
        } else {
            return Either.right(listaConBerriesNombreSimilar);
        }
    }

    public Either<String, BerriesResponse> getAllBerriesResult() {
        if (daoBerry.getAllBerriesNormal().isLeft()) {
            return Either.left("No hay Berries");
        } else {
            return Either.right(daoBerry.getAllBerriesNormal().get());
        }
    }

    public Either<String,BerryResponse> getSpecificBerry(int i) {
        if (daoBerry.getSpecificBerry(i).isLeft()) {
            return Either.left("No hay Berries");
        } else {
            return Either.right(daoBerry.getSpecificBerry(i).get());
        }
    }
}

