package com.test.kinopoisk.model.pojo;

/**
 * POJO class for display Genre Button.
 */
public class GenreButton {
    private String title;
    private Boolean isSelected;

    public GenreButton(String title, Boolean isSelected) {
        this.title = title;
        this.isSelected = isSelected;
    }

    public String getTitle() {
        return title;
    }

    public Boolean isSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }
}
