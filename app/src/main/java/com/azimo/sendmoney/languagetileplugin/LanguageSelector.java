package com.azimo.sendmoney.languagetileplugin;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Locale;

import io.reactivex.Observable;

public class LanguageSelector {

    private LanguagePrefs prefs;

    public LanguageSelector(LanguagePrefs prefs) {
        this.prefs = prefs;
    }

    public Observable<String> next() {
        String currentLocale = prefs.getLastLanguage();
        List<String> supportedLanguages = prefs.getSupportedLanguages();

        return select(supportedLanguages.get((supportedLanguages.indexOf(currentLocale) + 1) % supportedLanguages.size()));
    }

    public Observable<String> select(String language) {
        return Observable.just(language)
            .map(chosen -> {
                Locale locale = new Locale(chosen);
                try {
                    Class localePicker = Class.forName("com.android.internal.app.LocalePicker");
                    localePicker.getMethod("updateLocale", Locale.class).invoke(null, locale);
                } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
                prefs.putLastLanguage(chosen);
                return chosen;
            });
    }
}
