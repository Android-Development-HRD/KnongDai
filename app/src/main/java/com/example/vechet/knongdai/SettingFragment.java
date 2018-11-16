package com.example.vechet.knongdai;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.vechet.knongdai.R;
import com.example.vechet.knongdai.callback.OnFacebookLoginInfo;
import com.facebook.login.LoginManager;

import cn.pedant.SweetAlert.SweetAlertDialog;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment {

    public SettingFragment() {
        // Required empty public constructor
    }

    private static final String TAG = "SettingFragment";
    private LinearLayout lnChooseLanguage, lnAboutUs, lnLogout;
    private String dialog_title = String.valueOf(R.string.dialog_title);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_setting, container, false);

        initView(v);
        initEvent();


        return  v;
    }

    private void initEvent(){
        lnChooseLanguage.setOnClickListener(v->{
            startActivity(new Intent(getContext(), LanguagesActivity.class));
        });

        lnAboutUs.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), AboutUsActivity.class));
        });

        lnLogout.setOnClickListener(v->{
            SweetAlertDialog dialog = new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE);
            dialog.setTitleText("Logout")
                    .setContentText("Do you want to logout?")
                    .setCancelButton("No", new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            dialog.dismissWithAnimation();
                        }
                    })
                    .setConfirmButton("Yes", new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            LoginManager.getInstance().logOut();
                            Intent in = new Intent(getContext(), LoginActivity.class);
                            in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(in);
                            getActivity().finish();
                        }
                    })
                    .show();
        });

    }

    private void initView(View v) {
        lnChooseLanguage = v.findViewById(R.id.lnChooseLanguage);
        lnAboutUs = v.findViewById(R.id.lnAboutUs);
        lnLogout = v.findViewById(R.id.lnLogout);
    }

}
