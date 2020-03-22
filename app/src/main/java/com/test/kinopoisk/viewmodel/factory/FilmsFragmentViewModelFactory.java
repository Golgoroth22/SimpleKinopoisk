package com.test.kinopoisk.viewmodel.factory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.test.kinopoisk.repository.FilmsRepository;
import com.test.kinopoisk.viewmodel.FilmsFragmentViewModel;

import javax.inject.Inject;

/**
 * [ViewModelProvider.Factory] subclass for creating [FilmsFragmentViewModel].
 */
public class FilmsFragmentViewModelFactory implements ViewModelProvider.Factory {
    private FilmsRepository repository;

    @Inject
    public FilmsFragmentViewModelFactory(FilmsRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new FilmsFragmentViewModel(repository);
    }
}
