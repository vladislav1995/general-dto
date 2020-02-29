package com.miniq.example.graphql.dto;

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

}
