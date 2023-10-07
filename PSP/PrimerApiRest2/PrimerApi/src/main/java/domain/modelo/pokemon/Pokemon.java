package domain.modelo.pokemon;

import lombok.Getter;

import java.util.List;


@Getter
public class Pokemon {
    private int id;
    private String name;
    private int height;
    private int weight;
    private List<StatsItem> stats;
    private Sprites sprites;

    @Override
    public String toString() {
        return "Pokemon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", stats=" + stats +
                ", sprites=" + sprites +
                '}';
    }
}

