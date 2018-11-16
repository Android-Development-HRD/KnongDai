package com.example.vechet.knongdai;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        /*FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);*/

        getKeyHash();

    }

    //for generate KeyHash
    public void getKeyHash(){
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.example.vechet.knongdai",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    //for generate KeyHash
    //+B8K5l7X5jR+kw0UZVSDj1I6Ghg=
}
