package com.example.graphql.dto;

import com.google.common.base.Objects;

/**
 * @author Uladik
 */
public class User {

    private Long id;
    private String name;
    private Double rating;

    public User() {
    }

    public User(Long id, String name, Double rating) {
        this.id = id;
        this.name = name;
        this.rating = rating;
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

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equal(id, user.id)
                && Objects.equal(name, user.name)
                && Objects.equal(rating, user.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, name, rating);
    }
}
