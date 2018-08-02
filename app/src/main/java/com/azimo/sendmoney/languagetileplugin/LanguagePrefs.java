package com.azimo.sendmoney.languagetileplugin;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class LanguagePrefs {

    private static final String LOCALE_NAMESPACE = "LOCALE";
    private static final String PREVIOUS_LOCALE = "previous_language";
    private static final String TILE_WARNING = "tile_warning";
    private static final String SUPPORTED_LANGUAGES = "supported_language";
    private static final String SEPARATOR = ",";

    private static LanguagePrefs instance;

    private final SharedPreferences preferences;

    public static LanguagePrefs getInstance(Context context) {
        if (instance == null) {
            instance = new LanguagePrefs(context);
        }
        return instance;
    }

    private LanguagePrefs(Context context) {
        preferences = context.getSharedPreferences(LOCALE_NAMESPACE, Context.MODE_PRIVATE);
        putLastLanguage(Locale.getDefault().getLanguage());
    }

    public void registerListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        preferences.registerOnSharedPreferenceChangeListener(listener);
    }

    public void unregisterListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        preferences.unregisterOnSharedPreferenceChangeListener(listener);
    }

    public void clear() {
        preferences.edit().remove(SUPPORTED_LANGUAGES).apply();
        preferences.edit().remove(TILE_WARNING).apply();
    }

    public void putLastLanguage(String language) {
        preferences.edit().putString(PREVIOUS_LOCALE, language).apply();
    }

    public String getLastLanguage() {
        return preferences.getString(PREVIOUS_LOCALE, null);
    }

    public void putTileWarning(String warning) {
        preferences.edit().putString(TILE_WARNING, warning).apply();
    }

    public String getTileWarning() {
        return preferences.getString(TILE_WARNING, null);
    }

    public void putSupportedLanguages(List<String> languages) {
        StringBuilder result = new StringBuilder();
        for (String language : languages) {
            result.append(language).append(SEPARATOR);
        }
        result.deleteCharAt(result.length() - 1);
        preferences.edit().putString(SUPPORTED_LANGUAGES, result.toString()).apply();
    }

    public List<String> getSupportedLanguages() {
        String languages = preferences.getString(SUPPORTED_LANGUAGES, null);
        List<String> result = null;
        if (languages != null) {
            result = new ArrayList<>();
            result.addAll(Arrays.asList(languages.split(SEPARATOR)));
        }
        return result;
    }
}
