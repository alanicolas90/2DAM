package domain.modelo.pokemon;

import lombok.Getter;

import java.util.List;


@Getter
public class Pokemon {
    private int id;
    private String name;
    private int height;
    private int weight;
    private int baseExperience;
    private List<StatsItem> stats;
    private Sprites sprites;
}

