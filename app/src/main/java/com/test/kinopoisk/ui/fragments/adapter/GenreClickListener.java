package com.test.kinopoisk.ui.fragments.adapter;

import com.test.kinopoisk.model.pojo.GenreButton;

/**
 * Interface for working with responses when genre selected.
 */
public interface GenreClickListener {
    /**
     * This method can be called when genre selected.
     */
    void genreSelected(GenreButton genreButton);
}
