package com.example.vechet.knongdai;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        if(CheckInternet()){
            new Handler().postDelayed(
                    new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(SplashScreenActivity.this,LoginActivity.class));
                            finish();
                        }
                    },1000 //1000
            );

        }else{
            new Handler().postDelayed(
                    new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(SplashScreenActivity.this,CheckNetworkConnectionActivity.class));
                            finish();
                        }
                    },1000 //1000
            );

        }


    }

    //Check Network Connection
    public boolean CheckInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            return true;
        }
        return false;
    }

}
