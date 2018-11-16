package com.example.vechet.knongdai;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.vechet.knongdai.Language.MultipleLagnuagesActivity;
import com.example.vechet.knongdai.adapter.ViewPagerAdapter;

import de.hdodenhof.circleimageview.CircleImageView;

public class KnongDaiTabActivity extends MultipleLagnuagesActivity {

    private static final String TAG = "KnongDaiTabActivity";
    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ViewPagerAdapter adapter;
    private int tabIcons[] = {R.drawable.home_selector, R.drawable.notification_selector,R.drawable.more_selector};
    private CircleImageView ivSmallProfile;
    private String profileUrl = "";
    private TextView tvMainCateActionBarTitle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_knong_dai_tab);
        setTitle(null);

        initView();
        initFragmentIcon();
        initEvent();
    }

    private void initEvent() {
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.e(TAG, "onPageSelected: " + position );
                switch (position){
                    case 0:
                        tvMainCateActionBarTitle.setText(R.string.home);
                        break;
                    case 1:
                        tvMainCateActionBarTitle.setText(R.string.notification);
                        break;
                    case 2:
                        tvMainCateActionBarTitle.setText(R.string.setting);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initFragmentIcon() {
        for(int i = 0; i < tabLayout.getTabCount(); i++){
            tabLayout.getTabAt(i).setIcon(tabIcons[i]);
        }
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tvMainCateActionBarTitle = findViewById(R.id.tvMainCateActionBarTitle);
        viewPager = findViewById(R.id.container);
        tabLayout = findViewById(R.id.tabLayout);
        ivSmallProfile = findViewById(R.id.ivSmallProfile);
        //initIvSmallProfile();

        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new HomeFragment(), R.drawable.home_black);
        adapter.addFragment(new NotificationFragment(), R.drawable.notification_black);
        adapter.addFragment(new SettingFragment(), R.drawable.more_black);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu_knongdai, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuKnongDaiSearch:
                Intent in = new Intent(KnongDaiTabActivity.this, QuerySearchActivity.class);
                in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(in);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /*private void initIvSmallProfile() {
        Picasso.get()
                .load(profileUrl)
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .into(ivSmallProfile);
    }*/

    /*@Override
    public void onBackPressed() {
        SweetAlertDialog dialog = new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE);
        dialog.setTitleText("Knong Dai")
                .setContentText("Do you want to exit app?")
                .setCancelButton("No", new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        dialog.dismissWithAnimation();
                    }
                })
                .setConfirmButton("Yes", new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        finish();
                    }
                })
                .show();
    }*/

}
