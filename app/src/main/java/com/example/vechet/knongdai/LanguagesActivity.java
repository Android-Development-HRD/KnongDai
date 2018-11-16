package com.example.vechet.knongdai;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vechet.knongdai.Language.MultipleLagnuagesActivity;

public class LanguagesActivity extends MultipleLagnuagesActivity {

    private static final String TAG = "LanguageActivity";
    private Toolbar toolbar;
    private ImageView ivChooseLanguageBack;
    private TextView tvKhmer, tvEnglish;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_languages);
        setTitle(null);

        toolbar = findViewById(R.id.chooseLanguageToolbar);
        setSupportActionBar(toolbar);

        ivChooseLanguageBack = findViewById(R.id.ivChooseLanguageBack);
        tvEnglish = findViewById(R.id.tvEnglish);
        tvKhmer = findViewById(R.id.tvKhmer);

        ivChooseLanguageBack.setOnClickListener(v->{
            Intent in = new Intent(LanguagesActivity.this, SettingFragment.class);
            in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            finish();
        });


        tvKhmer.setOnClickListener(v->{
            setLanguage("km");
        });
        tvEnglish.setOnClickListener(v->{
            setLanguage("en");
        });

    }
}
