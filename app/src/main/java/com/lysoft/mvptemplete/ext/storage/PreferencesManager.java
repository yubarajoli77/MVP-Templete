package com.lysoft.mvptemplete.ext.storage;


import android.content.Context;
import android.content.SharedPreferences;

import com.lysoft.mvptemplete.ext.Constants;


public class PreferencesManager {

    private SharedPreferences sharedPreferences;

    public PreferencesManager(Context context) {
        sharedPreferences = context.getSharedPreferences(Constants.StorageConstants.PREFERENCE_NAME, Context.MODE_PRIVATE);
    }


    public void save(String key, String value) {
        sharedPreferences.edit().putString(key, value).apply();
    }

    public void saveInt(String key, Integer value) {
        sharedPreferences.edit().putInt(key, value).apply();
    }

    public String get(String key) {
        return sharedPreferences.getString(key, "");
    }

    public void clear() {
        sharedPreferences.edit().clear().apply();
    }


    public boolean isEmpty(String key) {
        return (sharedPreferences.getString(key, null) == null);
    }

    public int getInt(String key) {
        return sharedPreferences.getInt(key, 0);
    }

 }
