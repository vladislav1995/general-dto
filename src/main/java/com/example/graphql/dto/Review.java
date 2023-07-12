package com.example.graphql.dto;

import com.google.common.base.Objects;

/**
 * @author Uladik
 */
public class Review {

    private Long id;
    private String text;
    private User user;
    private Film film;

    public Review() {
    }

    public Review(Long id, String text, User user, Film film) {
        this.id = id;
        this.text = text;
        this.user = user;
        this.film = film;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public String getFilmId() {
        return String.valueOf(film.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Review review = (Review) o;
        return Objects.equal(id, review.id)
                && Objects.equal(text, review.text)
                && Objects.equal(user, review.user)
                && Objects.equal(film, review.film);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, text, user, film);
    }
}
