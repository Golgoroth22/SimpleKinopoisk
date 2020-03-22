package com.test.kinopoisk.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.test.kinopoisk.R;
import com.test.kinopoisk.network.pojo.Film;

/**
 * [Fragment] subclass for display info about [Film].
 */
public class FilmInfoFragment extends Fragment {
    private static final String FILM_TAG = "com.test.kinopoisk.ui.fragments.FILM_TAG";
    public static final Integer FRAGMENT_TOOLBAR_IMAGE_ID = R.drawable.ic_arrow_back_24dp;

    private Film film;

    private TextView titleText;
    private ImageView image;
    private TextView yearText;
    private TextView ratingText;
    private TextView descriptionText;

    public FilmInfoFragment() {
    }

    public static FilmInfoFragment newInstance(Film film) {
        FilmInfoFragment fragment = new FilmInfoFragment();
        Bundle args = new Bundle();
        args.putSerializable(FILM_TAG, film);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            film = (Film) getArguments().getSerializable(FILM_TAG);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_film_info, container, false);
        initViews(rootView);
        return rootView;
    }

    private void initViews(View rootView) {
        titleText = rootView.findViewById(R.id.fragment_film_info_titleText);
        image = rootView.findViewById(R.id.fragment_film_info_filmImage);
        yearText = rootView.findViewById(R.id.fragment_film_info_yearText);
        ratingText = rootView.findViewById(R.id.fragment_film_info_ratingText);
        descriptionText = rootView.findViewById(R.id.fragment_film_info_descriptionText);

        titleText.setText(film.getName());
        yearText.append(String.format(film.getYear().toString()));
        String rating = film.getRating() == null ? "?.???" : film.getRating().toString();
        ratingText.setText(rating);
        String description = film.getDescription() == null ? getString(R.string.fragment_film_info_description_empty_text) : film.getDescription();
        descriptionText.setText(description);
        Glide.with(getContext())
                .load(film.getImageUrl())
                .error(R.drawable.ic_no_image_24dp)
                .thumbnail(0.25f)
                .into(image);
    }
}
