package com.test.kinopoisk.ui.fragments.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.test.kinopoisk.R;
import com.test.kinopoisk.di.Scopes;
import com.test.kinopoisk.model.pojo.GenreButton;
import com.test.kinopoisk.network.pojo.Film;
import com.test.kinopoisk.ui.fragments.FilmInfoFragment;

import java.util.ArrayList;
import java.util.List;

import toothpick.Toothpick;

/**
 * [RecyclerView.Adapter] subclass for config and display [Film] elements.
 */
public class FilmsAdapter extends RecyclerView.Adapter<FilmsAdapter.FilmViewHolder> implements GenreClickListener {
    private List<Film> films = new ArrayList<>();
    private List<Film> allFilms = new ArrayList<>();

    private AddFragmentClickListener filmSelected = Toothpick.openScope(Scopes.SCREEN).getInstance(AddFragmentClickListener.class);

    @NonNull
    @Override
    public FilmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FilmViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_films_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FilmViewHolder holder, int position) {
        holder.bind(films.get(position));
    }

    @Override
    public int getItemCount() {
        return films.size();
    }

    /**
     * This method can be called for update adapter elements list.
     */
    public void updateList(List<Film> list) {
        films = list;
        allFilms = list;
        notifyDataSetChanged();
    }

    @Override
    public void genreSelected(GenreButton genreButton) {
        if (genreButton.isSelected()) {
            List<Film> newList = new ArrayList<>();
            for (Film film : allFilms) {
                if (film.getGenres().contains(genreButton.getTitle())) {
                    newList.add(film);
                }
            }
            films = newList;
        } else {
            films = allFilms;
        }
        notifyDataSetChanged();
    }

    /**
     * [RecyclerView.ViewHolder] subclass for config and display single [Film] element.
     */
    class FilmViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private ImageView background;

        FilmViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_films_titleText);
            background = itemView.findViewById(R.id.item_films_backgroundImage);
        }

        /**
         * This method can be called for bind views.
         */
        void bind(Film film) {
            title.setText(film.getLocalizedName());
            background.setOnClickListener(v -> {
                filmSelected.addFragment(FilmInfoFragment.newInstance(film), true);
                filmSelected.setUpToolbar(FilmInfoFragment.FRAGMENT_TOOLBAR_IMAGE_ID, film.getLocalizedName());
            });
            Glide.with(background.getContext())
                    .load(film.getImageUrl())
                    .error(R.drawable.ic_no_image_24dp)
                    .thumbnail(0.25f)
                    .into(background);
        }
    }
}
