package com.test.kinopoisk.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.test.kinopoisk.R;
import com.test.kinopoisk.di.Scopes;
import com.test.kinopoisk.network.pojo.Film;
import com.test.kinopoisk.ui.fragments.adapter.FilmsAdapter;
import com.test.kinopoisk.ui.fragments.adapter.GenresAdapter;
import com.test.kinopoisk.viewmodel.FilmsFragmentViewModel;
import com.test.kinopoisk.viewmodel.factory.FilmsFragmentViewModelFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import javax.inject.Inject;

import toothpick.Toothpick;

/**
 * [Fragment] subclass for display [Films] and [GenreButton]`s.
 */
public class FilmsFragment extends Fragment {
    private ProgressBar filmsProgress;
    private ProgressBar genresProgress;

    private RecyclerView genresRecycler;
    private GenresAdapter genresAdapter;
    private RecyclerView.LayoutManager genresLayoutManager;

    private RecyclerView filmsRecycler;
    private FilmsAdapter filmsAdapter;
    private RecyclerView.LayoutManager filmsLayoutManager;

    @Inject
    FilmsFragmentViewModelFactory factory;
    private FilmsFragmentViewModel viewModel;

    public FilmsFragment() {
    }

    public static FilmsFragment newInstance() {
        return new FilmsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toothpick.inject(this, Toothpick.openScope(Scopes.SCREEN));
        viewModel = ViewModelProviders.of(this, factory).get(FilmsFragmentViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_films, container, false);
        initViews(rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.getFilmsLifeData().observe(this, films -> {
            if (films.getFilms().size() > 0) {
                filmsProgress.setVisibility(View.GONE);
                genresProgress.setVisibility(View.GONE);
                HashSet<String> genres = new HashSet<>();
                for (Film list : films.getFilms()) {
                    genres.addAll(list.getGenres());
                }
                genresAdapter.updateList(new ArrayList<>(genres));
                Collections.sort(films.getFilms(), (film1, film2) -> film1.getLocalizedName().compareTo(film2.getLocalizedName()));
                filmsAdapter.updateList(films.getFilms());
            }
        });
    }

    private void initViews(View rootView) {
        genresRecycler = rootView.findViewById(R.id.fragment_films_genresRecycler);
        filmsRecycler = rootView.findViewById(R.id.fragment_films_filmsRecycler);
        filmsProgress = rootView.findViewById(R.id.fragment_films_filmsProgress);
        genresProgress = rootView.findViewById(R.id.fragment_films_genresProgress);

        filmsLayoutManager = new GridLayoutManager(getContext(), 2);
        filmsAdapter = new FilmsAdapter();
        filmsRecycler.setLayoutManager(filmsLayoutManager);
        filmsRecycler.setAdapter(filmsAdapter);

        genresLayoutManager = new GridLayoutManager(getContext(), 3, GridLayoutManager.HORIZONTAL, false);
        genresAdapter = new GenresAdapter(filmsAdapter);
        genresRecycler.setLayoutManager(genresLayoutManager);
        genresRecycler.setAdapter(genresAdapter);
    }
}
