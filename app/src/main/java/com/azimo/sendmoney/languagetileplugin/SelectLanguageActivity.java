package com.azimo.sendmoney.languagetileplugin;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

public class SelectLanguageActivity extends Activity {

    @BindView(R.id.lvLanguages)
    ListView lvLanguages;
    @BindView(R.id.tvNoLanguages)
    TextView tvNoLanguages;

    LanguagePrefs prefs = LanguagePrefs.getInstance(this);
    SelectLanguageActivityPresenter presenter = new SelectLanguageActivityPresenter(this);


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_language);

        ButterKnife.bind(this);

        presenter.init();
    }

    public void showSupportedLanguages(List<String> supportedLanguages) {
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, supportedLanguages);
        lvLanguages.setAdapter(adapter);
        lvLanguages.setVisibility(View.VISIBLE);
        tvNoLanguages.setVisibility(View.GONE);
    }

    @OnItemClick(R.id.lvLanguages)
    public void selectLanguage(int position) {
        presenter.selectLanguage(lvLanguages.getItemAtPosition(position).toString());
    }

    public void showWarning() {
        if (prefs.getTileWarning() != null) {
            Toast.makeText(this, prefs.getTileWarning(), Toast.LENGTH_LONG).show();
        }
    }
}
