package com.azimo.sendmoney.languagetileplugin;

import android.content.Context;

import java.util.List;

class LanguageSwitcherTile {

    public static Builder builder(Context context) {
        return new Builder(context);
    }

    public static class Builder {
        private LanguagePrefs prefs;

        private String warning;
        private List<String> languages;
        private String defaultLanguage;

        private Builder(Context context) {
            prefs = LanguagePrefs.getInstance(context);
        }

        public Builder withWarning(String warning) {
            this.warning = warning;
            return this;
        }

        public Builder withOptions(List<String> languages) {
            boolean isCorrect = true;
            for (String language : languages) {
                if (language.length() != 2) {
                    isCorrect = false;
                }
            }
            if (isCorrect) {
                this.languages = languages;
            }
            return this;
        }

        public Builder withDefault(String defaultLanguage) {
            if (defaultLanguage.length() == 2 && languages.contains(defaultLanguage)) {
                this.defaultLanguage = defaultLanguage;
            }
            return this;
        }

        public void enable() {
            if (warning != null) {
                prefs.putTileWarning(warning);
            }
            if (languages != null) {
                prefs.putSupportedLanguages(languages);
            }
            if (defaultLanguage != null) {
                prefs.putDefaultLanguage(defaultLanguage);
            }
        }

        public void disable() {
            prefs.clear();
        }
    }
}
