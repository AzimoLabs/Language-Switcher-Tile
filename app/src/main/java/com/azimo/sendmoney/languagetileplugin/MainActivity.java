package com.azimo.sendmoney.languagetileplugin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.Arrays;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.enableWithWarning)
    public void enableWithWarning() {
        LanguageSwitcherTile.builder(this).withOptions(Arrays.asList("en", "de", "es", "fr", "it", "pl", "pt", "ro", "ru")).withWarning("My warning").enable();
    }

    @OnClick(R.id.enableWithoutWarning)
    public void enableWithoutWarning() {
        LanguageSwitcherTile.builder(this).withOptions(Arrays.asList("en", "de", "es", "fr", "it", "pl", "pt", "ro", "ru")).enable();
    }

    @OnClick(R.id.disable)
    public void disable() {
        LanguageSwitcherTile.builder(this).disable();
    }
}
