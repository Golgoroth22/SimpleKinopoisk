package com.test.kinopoisk;

import android.app.Application;

import toothpick.Toothpick;
import toothpick.configuration.Configuration;

/**
 * Main project application.
 */
public class ProjectApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initTootpick();
    }

    private void initTootpick() {
        Toothpick.setConfiguration(Configuration.forDevelopment());
    }
}
