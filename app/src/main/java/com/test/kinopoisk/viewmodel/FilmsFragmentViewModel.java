package com.test.kinopoisk.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.test.kinopoisk.network.pojo.Films;
import com.test.kinopoisk.repository.FilmsRepository;

/**
 * [ViewModel] subclass for work with [FilmsFragment].
 */
public class FilmsFragmentViewModel extends ViewModel {
    private FilmsRepository repository;
    private MutableLiveData<Films> filmsLifeData;

    public MutableLiveData<Films> getFilmsLifeData() {
        return filmsLifeData;
    }

    public FilmsFragmentViewModel(FilmsRepository repository) {
        this.repository = repository;
        filmsLifeData = this.repository.getFilmsLifeData();
    }
}
