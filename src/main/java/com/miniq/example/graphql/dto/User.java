package com.miniq.example.graphql.dto;

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

}
