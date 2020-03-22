package com.test.kinopoisk.network.service;

import com.test.kinopoisk.network.pojo.Films;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Interface for taken [Films] from server.
 */
public interface FilmsService {
    /**
     * This method can be called for taken [Films] wrapped in [Call].
     */
    @GET("sequeniatesttask/films.json")
    Call<Films> listRepos();
}
