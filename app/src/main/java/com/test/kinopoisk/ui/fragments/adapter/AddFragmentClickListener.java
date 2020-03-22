package com.test.kinopoisk.ui.fragments.adapter;

import androidx.fragment.app.Fragment;

/**
 * Interface for working with responses when replacing fragment.
 */
public interface AddFragmentClickListener {
    /**
     * This method can be called when need add new fragment.
     */
    void addFragment(Fragment fragment, Boolean addToBackStack);

    /**
     * This method can be called for update project toolbar.
     */
    void setUpToolbar(Integer toolbarIconId, String toolbarTitleText);
}
