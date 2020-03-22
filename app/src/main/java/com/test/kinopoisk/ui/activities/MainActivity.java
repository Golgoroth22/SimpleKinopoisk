package com.test.kinopoisk.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.test.kinopoisk.R;
import com.test.kinopoisk.di.Scopes;
import com.test.kinopoisk.di.module.MainModule;
import com.test.kinopoisk.ui.fragments.FilmsFragment;
import com.test.kinopoisk.ui.fragments.adapter.AddFragmentClickListener;

import toothpick.Toothpick;

/**
 * Main app activity.
 */
public class MainActivity extends AppCompatActivity implements AddFragmentClickListener {
    private ImageButton toolbarImageButton;
    private TextView toolbarText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toothpick.openScope(Scopes.SCREEN).installModules(new MainModule(this));
        initViews();
        addFragment(FilmsFragment.newInstance(), false);
    }

    @Override
    public void addFragment(Fragment fragment, Boolean addToBackStack) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_main_containerLayout, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    @Override
    public void setUpToolbar(Integer toolbarIconId, String toolbarTitleText) {
        toolbarText.setText(toolbarTitleText);
        if (toolbarIconId == null) {
            toolbarImageButton.setImageResource(0);
            toolbarImageButton.setOnClickListener(null);
            toolbarImageButton.setVisibility(View.INVISIBLE);
        } else {
            toolbarImageButton.setImageResource(toolbarIconId);
            toolbarImageButton.setOnClickListener(v -> onBackPressed());
            toolbarImageButton.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (getSupportFragmentManager().findFragmentById(R.id.activity_main_containerLayout) instanceof FilmsFragment) {
            setUpToolbar(null, getString(R.string.fragment_films_title_text));
        }
    }

    private void initViews() {
        toolbarImageButton = findViewById(R.id.toolbar_leftImage);
        toolbarText = findViewById(R.id.toolbar_titleText);
        setUpToolbar(null, getString(R.string.fragment_films_title_text));
    }
}
