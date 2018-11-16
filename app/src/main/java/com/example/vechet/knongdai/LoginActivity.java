package com.example.vechet.knongdai;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.vechet.knongdai.Language.MultipleLagnuagesActivity;
import com.example.vechet.knongdai.callback.OnFacebookLoginInfo;
import com.example.vechet.knongdai.database.User;
import com.example.vechet.knongdai.database.UserDao;
import com.example.vechet.knongdai.database.UserDatabase;
import com.example.vechet.knongdai.entity.FacebookLoginInfo;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoginActivity extends MultipleLagnuagesActivity {

    private static final String TAG = "LoginActivity";
    private static final String EMAIL = "email";
    private CallbackManager callbackManager;
    private Button btnLogin;
    private AccessToken accessToken;
    private FacebookLoginInfo facebookLoginInfo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        checkFacebookLogin();
        initEvent();

    }

    private void initEvent() {
        // Callback registration
        actionLoginButton();
    }

    private void actionLoginButton() {
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.e(TAG, "onSuccess: " );
                getUserInfo(loginResult.getAccessToken());

            }

            @Override
            public void onCancel() {
                Log.e(TAG, "onCancel: " );
            }

            @Override
            public void onError(FacebookException error) {
                Log.e(TAG, "onError: " + error.getMessage() );
            }
        });
        btnLogin.setOnClickListener(v -> {
            LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, Arrays.asList(EMAIL));
        });
    }

    private void getUserInfo(AccessToken accessToken) {
        GraphRequest request = GraphRequest.newMeRequest(
                accessToken,
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {

                        try {
                            String id = object.getString("id");
                            String name = object.getString("name");
                            String email = object.getString("email");
                            String profileUrl = "https://graph.facebook.com/"+
                                    object.getString("id")+"/picture?type=large";

                            Log.e(TAG, "AccessToken "+" "+id+" " +name+" "+email+" "+profileUrl );

                            Intent in = new Intent(LoginActivity.this, KnongDaiTabActivity.class);
                            startActivity(in);
                            finish();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email,picture.type(large)");
        request.setParameters(parameters);
        request.executeAsync();
    }

    private void initView() {
        btnLogin = findViewById(R.id.btnLogin);
        callbackManager = CallbackManager.Factory.create();
    }

    private void checkFacebookLogin() {
        accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        Log.e(TAG, "Status: " + isLoggedIn );
        if(isLoggedIn == true){
            startActivity(new Intent(LoginActivity.this, KnongDaiTabActivity.class));
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }


}
