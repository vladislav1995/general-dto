package com.example.graphql.dto;

import com.google.common.base.Objects;

/**
 * @author Uladik
 */
public class Film {
    private Long id;
    private String name;

    public Film() {
    }

    public Film(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Film film = (Film) o;
        return Objects.equal(id, film.id) && Objects.equal(name, film.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, name);
    }
}
