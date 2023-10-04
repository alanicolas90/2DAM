package domain.modelo.pokemon;

import lombok.Getter;

import java.util.List;

@Getter
public class PokemonResponse {
    int count;
    String next;
    String previous;
    List<Result> results;
}
