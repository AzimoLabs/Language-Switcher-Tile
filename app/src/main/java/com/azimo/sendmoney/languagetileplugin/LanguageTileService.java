package com.azimo.sendmoney.languagetileplugin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.provider.Settings;
import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;
import android.widget.Toast;

import java.util.List;

import static android.provider.Settings.ACTION_MANAGE_WRITE_SETTINGS;

public class LanguageTileService extends TileService {

    private LanguagePrefs prefs;
    private SharedPreferences.OnSharedPreferenceChangeListener listener;
    private LanguageSelector selector;

    private String warning;

    @Override
    public IBinder onBind(Intent intent) {

        prefs = LanguagePrefs.getInstance(this);
        selector = new LanguageSelector(prefs);
        listener = (sharedPreferences, key) -> configureService(prefs.getSupportedLanguages(), prefs.getDefaultLanguage(), prefs.getTileWarning());

        return super.onBind(intent);
    }

    @Override
    public void onStartListening() {
        configureService(prefs.getSupportedLanguages(), prefs.getDefaultLanguage(), prefs.getTileWarning());
        prefs.registerListener(listener);
    }

    @Override
    public void onStopListening() {
        prefs.unregisterListener(listener);
    }

    @Override
    public void onClick() {
        if (!Settings.System.canWrite(this)) {
            openWriteSettingsManager();
        } else {
            selectNextLanguage();
        }
    }

    private void configureService(List<String> supportedLanguages, String defaultLanguage, String warning) {
        if (supportedLanguages != null && defaultLanguage != null) {
            enable();
            if (warning != null) {
                this.warning = warning;
            }
        } else {
            disable();
        }
    }

    public void disable() {
        getQsTile().setState(Tile.STATE_UNAVAILABLE);
        getQsTile().updateTile();
    }

    public void enable() {
        getQsTile().setState(Tile.STATE_ACTIVE);
        getQsTile().updateTile();
    }

    private void openWriteSettingsManager() {
        Intent intent = new Intent(ACTION_MANAGE_WRITE_SETTINGS);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(intent);
    }

    private void selectNextLanguage() {
        selector
            .next()
            .doOnTerminate(this::showWarning)
            .subscribe();
    }

    private void showWarning() {
        if (warning != null) {
            Toast.makeText(this, prefs.getTileWarning(), Toast.LENGTH_LONG).show();
        }
    }
}
