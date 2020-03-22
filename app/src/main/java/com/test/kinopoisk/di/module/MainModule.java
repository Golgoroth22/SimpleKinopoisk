package com.test.kinopoisk.di.module;

import com.squareup.moshi.Moshi;
import com.test.kinopoisk.network.service.FilmsService;
import com.test.kinopoisk.repository.FilmsRepository;
import com.test.kinopoisk.ui.activities.MainActivity;
import com.test.kinopoisk.ui.fragments.adapter.AddFragmentClickListener;
import com.test.kinopoisk.viewmodel.factory.FilmsFragmentViewModelFactory;

import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;
import toothpick.config.Module;

/**
 * [Module] subclass for prepare main app dependency injection.
 */
public class MainModule extends Module {
    public MainModule(MainActivity activity) {
        bind(AddFragmentClickListener.class).toInstance(activity);
        bind(FilmsFragmentViewModelFactory.class)
                .toInstance(new FilmsFragmentViewModelFactory(new FilmsRepository(configRetrofit().create(FilmsService.class))));
    }

    private Retrofit configRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://s3-eu-west-1.amazonaws.com/")
                .addConverterFactory(MoshiConverterFactory.create(new Moshi.Builder().build()))
                .build();
    }
}
