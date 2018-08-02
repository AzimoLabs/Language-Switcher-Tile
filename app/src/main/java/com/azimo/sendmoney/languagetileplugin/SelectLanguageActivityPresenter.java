package com.azimo.sendmoney.languagetileplugin;

import android.content.Intent;
import android.provider.Settings;

import java.util.List;

import static android.provider.Settings.ACTION_MANAGE_WRITE_SETTINGS;

public class SelectLanguageActivityPresenter {

    private SelectLanguageActivity view;
    private LanguagePrefs prefs;
    private LanguageSelector languageSelector;

    private List<String> supportedLanguages;

    public SelectLanguageActivityPresenter(SelectLanguageActivity view) {
        this.view = view;
        this.prefs = LanguagePrefs.getInstance(view);
        this.languageSelector = new LanguageSelector(prefs);
        this.supportedLanguages = prefs.getSupportedLanguages();
    }

    public void init() {
        if (!Settings.System.canWrite(view)) {
            openWriteSettingsManager();
        }
        if (prefs.getSupportedLanguages() != null) {
            view.showSupportedLanguages(supportedLanguages);
        } else {
            view.showNoLanguagesMessage();
        }
    }

    private void openWriteSettingsManager() {
        Intent intent = new Intent(ACTION_MANAGE_WRITE_SETTINGS);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        view.startActivity(intent);
    }

    public void selectLanguage(String language) {
        languageSelector
            .select(language)
            .doOnTerminate(view::showWarning)
            .doOnTerminate(view::finish)
            .subscribe();
    }
}
