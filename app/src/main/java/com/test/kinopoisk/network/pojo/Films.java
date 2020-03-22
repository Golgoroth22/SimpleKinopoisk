package com.test.kinopoisk.network.pojo;

import com.squareup.moshi.Json;

import java.util.ArrayList;
import java.util.List;

/**
 * POJO class for display Films.
 */
public class Films {
    @Json(name = "films")
    private List<Film> films;

    public List<Film> getFilms() {
        return films;
    }

    public Films() {
        this.films = new ArrayList<>();
    }
}
