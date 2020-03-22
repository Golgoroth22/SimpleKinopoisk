package com.test.kinopoisk.repository;

import androidx.lifecycle.MutableLiveData;

import com.test.kinopoisk.network.pojo.Films;
import com.test.kinopoisk.network.service.FilmsService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Repository class for work [Films] data.
 */
public class FilmsRepository {
    private FilmsService filmsService;
    private MutableLiveData<Films> filmsLifeData = new MutableLiveData<>();

    public MutableLiveData<Films> getFilmsLifeData() {
        return filmsLifeData;
    }

    public FilmsRepository(FilmsService service) {
        filmsService = service;
        updateFilms();
    }

    private void updateFilms() {
        Call<Films> filmsResponse = filmsService.listRepos();
        filmsResponse.enqueue(new Callback<Films>() {
            @Override
            public void onResponse(Call<Films> call, Response<Films> response) {
                filmsLifeData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<Films> call, Throwable t) {
            }
        });
    }
}
