package com.azimo.sendmoney.languageswitchertile;

import android.content.Context;

import java.util.List;

public class LanguageSwitcherTile {

    public static Builder builder(Context context) {
        return new Builder(context);
    }

    public static class Builder {
        private LanguagePrefs prefs;

        private String warning;
        private List<String> languages;

        private Builder(Context context) {
            prefs = LanguagePrefs.getInstance(context);
        }

        public Builder withWarning(String warning) {
            this.warning = warning;
            return this;
        }

        public Builder withOptions(List<String> languages) {
            if (languages == null || languages.isEmpty()) {
                throw new IllegalArgumentException("Languages loading failed: list is empty or not initialized.");
            }
            for (String language : languages) {
                if (language.length() != 2) {
                    throw new IllegalArgumentException("Languages loading failed: wrong language shortcut format. (" + language + ")");
                }
            }

            this.languages = languages;
            return this;
        }

        public void enable() {
            if (warning != null) {
                prefs.putTileWarning(warning);
            }
            if (languages != null) {
                prefs.putSupportedLanguages(languages);
            } else {
                throw new IllegalArgumentException("Enabling failed: languages missing");
            }
        }

        public void disable() {
            prefs.clear();
        }
    }
}
