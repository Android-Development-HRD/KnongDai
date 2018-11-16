package com.example.vechet.knongdai;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vechet.knongdai.Language.MultipleLagnuagesActivity;

public class AboutUsActivity extends MultipleLagnuagesActivity {

    private Toolbar toolbar;
    private ImageView ivAboutUsBack;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        setTitle(null);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ivAboutUsBack = findViewById(R.id.ivAboutUsBack);

        ivAboutUsBack.setOnClickListener(v->{
            Intent in = new Intent(AboutUsActivity.this, SettingFragment.class);
            in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            finish();
        });
    }
}
