package com.example.vechet.knongdai;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vechet.knongdai.Language.MultipleLagnuagesActivity;
import com.example.vechet.knongdai.adapter.MainCategoryAdapter;
import com.example.vechet.knongdai.callback.OnSubCateItemClickListener;
import com.example.vechet.knongdai.callback.OnMainCateClickItemListener;
import com.example.vechet.knongdai.entity.MainCateId;
import com.example.vechet.knongdai.entity.MainCategoriesResponse;
import com.example.vechet.knongdai.service.SearchService;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class KnongDaiActivity extends MultipleLagnuagesActivity implements OnMainCateClickItemListener {

    public static final String BASE_URL = "http://110.74.194.125:15000";
    private static final String TAG = "Main";
    private Retrofit retrofit;
    private SearchService service;
    private RecyclerView recyclerView;
    private MainCategoryAdapter adapter;
    private List<MainCategoriesResponse.DataEntity> mainCate = new ArrayList<>();
    private OnSubCateItemClickListener getMainlistener;
    private Toolbar toolbar;
    private TextView tvKnongDaiActionBarTitle;
    private ImageView ivKnongDaiLanguages;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_knong_dai);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        initView();
        setupRetrofit();
        loadMainCategories();
        initEvent();
    }

    private void initEvent() {
        ivKnongDaiLanguages.setOnClickListener(v->{


        });
    }

    private void loadMainCategories() {

        Call<MainCategoriesResponse> call = service.getAllMainCategories();
        call.enqueue(new Callback<MainCategoriesResponse>() {
            @Override
            public void onResponse(Call<MainCategoriesResponse> call, Response<MainCategoriesResponse> response) {
                Log.e(TAG, "onResponse: "  + response.body().getMsg());
                List<MainCategoriesResponse.DataEntity> mainCategoriesResponseList =
                        response.body().getData();
                adapter.getMainCate(mainCategoriesResponseList);

                //Get MainCategories Title
                /*ArrayList<String> mainCateName = new ArrayList<>();
                for(MainCategoriesResponse.DataEntity mainCate : mainCategoriesResponseList){
                    mainCateName.add(mainCate.getCateName());
                }
                Log.e(TAG, "Main Cate Name " + mainCateName );*/
            }

            @Override
            public void onFailure(Call<MainCategoriesResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage() );
            }
        });

    }

    private void setupRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(SearchService.class);
    }

    private void initView() {
        toolbar = findViewById(R.id.knongDaiToolbar);
        setSupportActionBar(toolbar);
        tvKnongDaiActionBarTitle = findViewById(R.id.tvKnongDaiActionBarTitle);
        tvKnongDaiActionBarTitle.setText("Knong Dai");
        ivKnongDaiLanguages = findViewById(R.id.ivKnongDaiLanguages);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MainCategoryAdapter(this);
        adapter.onClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(int position) {
        Log.e(TAG, "onItemClick: " + position );
        MainCategoriesResponse.DataEntity mainCates = adapter.getMainCateById(position);
        Log.e(TAG, "onItemClick: " + mainCates.getId() );
        Intent in = new Intent(KnongDaiActivity.this, SubCategoriesActivity.class);
        MainCateId mainId = new MainCateId(mainCates.getId(), mainCates.getCateName());
        in.putExtra("id", mainId);
        startActivity(in);
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
                startActivity(new Intent(KnongDaiActivity.this, QuerySearchActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        SweetAlertDialog dialog = new SweetAlertDialog(this,SweetAlertDialog.NORMAL_TYPE);
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
    }

}
