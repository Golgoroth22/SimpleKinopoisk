package com.test.kinopoisk.ui.fragments.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.test.kinopoisk.R;
import com.test.kinopoisk.model.pojo.GenreButton;

import java.util.ArrayList;
import java.util.List;

/**
 * [RecyclerView.Adapter] subclass for config and display [GenreButton] elements.
 */
public class GenresAdapter extends RecyclerView.Adapter<GenresAdapter.GenreViewHolder> {
    private List<GenreButton> genresList = new ArrayList<>();
    private GenreClickListener genreClickListener;

    public GenresAdapter(GenreClickListener listener) {
        genreClickListener = listener;
    }

    @NonNull
    @Override
    public GenreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GenreViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_genres_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GenreViewHolder holder, int position) {
        holder.bind(genresList.get(position));
    }

    @Override
    public int getItemCount() {
        return genresList.size();
    }

    /**
     * This method can be called for update adapter elements list.
     */
    public void updateList(List<String> list) {
        genresList = new ArrayList<>();
        for (String str : list) {
            genresList.add(new GenreButton(str, false));
        }
        notifyDataSetChanged();
    }

    private void clearButtons() {
        for (GenreButton gb : genresList) {
            gb.setSelected(false);
        }
        notifyDataSetChanged();
    }

    /**
     * [RecyclerView.ViewHolder] subclass for config and display single [GenreButton] element.
     */
    class GenreViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private CardView background;

        GenreViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_genres_titleButton);
            background = itemView.findViewById(R.id.item_genres_backgroundCard);
        }

        /**
         * This method can be called for bind views.
         */
        void bind(GenreButton genreButton) {
            title.setText(String.format(genreButton.getTitle().substring(0, 1).toUpperCase() + genreButton.getTitle().substring(1)));
            title.setOnClickListener(v -> {
                if (genreButton.isSelected()) {
                    clearButtons();
                } else {
                    clearButtons();
                    genreButton.setSelected(true);
                }
                genreClickListener.genreSelected(genreButton);
            });
            if (genreButton.isSelected()) {
                background.setCardBackgroundColor(Color.LTGRAY);
            } else {
                background.setCardBackgroundColor(Color.WHITE);
            }
        }
    }
}
