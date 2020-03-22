package com.test.kinopoisk.network.pojo;

import com.squareup.moshi.Json;

import java.io.Serializable;
import java.util.List;

/**
 * POJO class for display Film.
 */
public class Film implements Serializable {
    @Json(name = "id")
    private Integer id;
    @Json(name = "localized_name")
    private String localizedName;
    @Json(name = "name")
    private String name;
    @Json(name = "year")
    private Integer year;
    @Json(name = "rating")
    private Double rating;
    @Json(name = "image_url")
    private String imageUrl;
    @Json(name = "description")
    private String description;
    @Json(name = "genres")
    private List<String> genres;

    public Integer getId() {
        return id;
    }

    public String getLocalizedName() {
        return localizedName;
    }

    public String getName() {
        return name;
    }

    public Integer getYear() {
        return year;
    }

    public Double getRating() {
        return rating;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getGenres() {
        return genres;
    }
}
