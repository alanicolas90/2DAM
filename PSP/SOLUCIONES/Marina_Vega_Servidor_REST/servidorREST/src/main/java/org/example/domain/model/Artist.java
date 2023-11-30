package org.example.domain.model;

import lombok.Data;

import java.util.Objects;

@Data
public class Artist {
    private int id;
    private String name;
    private String country;
    private int debutYear;

    public Artist() {
        //Needed for json deserialization
    }

    public Artist(int id) {
        this.id = id;
    }

    public Artist(int id, String name, String country, int debutYear) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.debutYear = debutYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artist artist = (Artist) o;
        return id == artist.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}