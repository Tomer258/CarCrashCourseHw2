package com.example.carcrashcoursehw2.Utilities;

import android.content.Context;
import android.content.SharedPreferences;

public class sharedPref {
    private static final String DB_FILE = "DB_FILE";

    private static sharedPref instance = null;
    private SharedPreferences sharedPreferences;

    private sharedPref(Context context) {
        sharedPreferences = context.getSharedPreferences(DB_FILE, Context.MODE_PRIVATE);
    }

    public static void init(Context context){
        if (instance == null){
            instance = new sharedPref(context);
        }
    }

    public static sharedPref getInstance() {
        return instance;
    }

    public String getString(String key, String value) {

        return sharedPreferences.getString(key, value);
    }

    public void putString(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

}

