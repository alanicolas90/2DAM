package domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Artist {
    private String id;
    private String name;
    private String country;
    private Integer debutYear;

    public Artist(String id) {
        this.id = id;
    }

    public Artist(String name, String country, Integer debutYear) {
        this.name = name;
        this.country = country;
        this.debutYear = debutYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artist artist = (Artist) o;
        return Objects.equals(id, artist.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}