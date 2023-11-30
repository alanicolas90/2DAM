package org.example.domain.model;

import lombok.Data;

import java.util.Objects;

@Data
public class Song {
    private int id;
    private String name;
    private int length;
    private Artist artist;

    public Song() {
        //Needed for json deserialization
    }

    public Song(int id, String name, int length, Artist artist) {
        this.id = id;
        this.name = name;
        this.length = length;
        this.artist = artist;
    }

    public Song(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return id == song.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}