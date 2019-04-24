package com.biscuit.mo3en;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sharedprefrens1 {

    public static final String PREFS_NAME = "GIFT_APP";
    public static final String FAVORITES  = "GIFTS";
    public Sharedprefrens1() {
        super();
    }
    // THIS FOUR METHODS ARE USED FOR MAINTAINING FAVORITES.
    public void saveFavorite(Context context, List<Time> time) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(time);
        editor.putString(FAVORITES, jsonFavorites);
        editor.apply();
    }

    public void addFavorite(Context context, Time time) {
        List<Time> favorites = getFavorites(context);
        if (favorites == null) {
            favorites = new ArrayList<>();
            favorites.add(time);
            saveFavorite(context, favorites);
        }
        else {
            favorites.add(time);
            saveFavorite(context,favorites);
        }
    }

    public void removeFavorite(Context context, int index) {
        ArrayList<Time> favorites = getFavorites(context);
        if (favorites != null) {
            //favorites = new ArrayList<>();
            favorites.remove(index);
            saveFavorite(context, favorites);
        }
    }
    public ArrayList<Time> getFavorites(Context context) {
        SharedPreferences settings;
        List<Time> favorites;
        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        if (settings.contains(FAVORITES)) {
            String jsonFavorites = settings.getString(FAVORITES, null);
            Gson gson = new Gson();
            Time[] favoriteItems = gson.fromJson(jsonFavorites,
                    Time[].class);
            favorites = Arrays.asList(favoriteItems);
            favorites = new ArrayList<>(favorites);
        } else {
            return new ArrayList<>();
        }
        return (ArrayList<Time>) favorites;
    }
}