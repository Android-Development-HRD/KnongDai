package com.example.vechet.knongdai;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vechet.knongdai.adapter.SubCateDetailAdapter;
import com.example.vechet.knongdai.callback.OnSubCateDetailItemClickListener;
import com.example.vechet.knongdai.entity.SubCateDetailId;
import com.example.vechet.knongdai.entity.SubCateDetialResponse;
import com.example.vechet.knongdai.entity.SubCateId;
import com.example.vechet.knongdai.service.SearchService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SubCateDetailActivity extends AppCompatActivity implements OnSubCateDetailItemClickListener {

    public static final String BASE_URL = "http://110.74.194.125:15000";
    private static final String TAG = "Detail";
    private Retrofit retrofit;
    private SearchService service;
    private RecyclerView subCateDetailRecyclerView;
    private SubCateDetailAdapter adapter;
    private int page = 1;
    private TextView tvSubCateDetailActionBarTitle;
    private Toolbar toolbar;
    private ImageView ivSubCateDetailBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_cate_detail);

        SubCateId subCateId = (SubCateId)getIntent().getSerializableExtra("subCateId");
        Log.e(TAG, "onCreate: " + subCateId.getSubCateId() + subCateId.getSubCateTitle());
        tvSubCateDetailActionBarTitle = findViewById(R.id.tvSubCateDetailActionBarTitle);
        tvSubCateDetailActionBarTitle.setText(subCateId.getSubCateTitle());

        initView();
        setupRetrofit();
        loadSubCateDetailBySubCateId(page, subCateId.getSubCateId());
        initEvent();

        //size17

    }

    private void initEvent() {
        ivSubCateDetailBack.setOnClickListener(v->{
            Intent in = new Intent(SubCateDetailActivity.this, SubCategoriesActivity.class);
            in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            finish();
        });
    }

    private void loadSubCateDetailBySubCateId(int page, int id) {
        Call<SubCateDetialResponse> call = service.getAllWebsiteBySubCateId(page,id);
        call.enqueue(new Callback<SubCateDetialResponse>() {
            @Override
            public void onResponse(Call<SubCateDetialResponse> call, Response<SubCateDetialResponse> response) {
                Log.e(TAG, "onResponse: " + response.body().getMsg());
                List<SubCateDetialResponse.DataEntity> subCateDetailListResponse =
                        response.body().getData();
                adapter.getSubCateDetail(subCateDetailListResponse);

            }

            @Override
            public void onFailure(Call<SubCateDetialResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
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
        toolbar = findViewById(R.id.subCateDetailToolbar);
        setSupportActionBar(toolbar);
        ivSubCateDetailBack = findViewById(R.id.ivSubCateDetailBack);
        subCateDetailRecyclerView = findViewById(R.id.subCateDetailRecyclerView);
        subCateDetailRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SubCateDetailAdapter();
        adapter.onSubCateDetailClickListener(this);
        subCateDetailRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onSubCateDetailItemClick(int position) {
        Log.e(TAG, "onSubCateDetailItemClick: " + position );
        SubCateDetialResponse.DataEntity subCateDetail = adapter.getSubCateDetailById(position);
        Log.e(TAG, "onSubCateDetailItemClick: " + subCateDetail.getId());
        Log.e(TAG, "onSubCateDetailItemClick: " + subCateDetail.getLink());

        Intent in = new Intent(SubCateDetailActivity.this, WebViewActivity.class);
        SubCateDetailId subCateDetailId = new SubCateDetailId(subCateDetail.getId(), subCateDetail.getLink(), subCateDetail.getTitle());
        in.putExtra("subCateDetailId", subCateDetailId);
        startActivity(in);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu_subcatedetail, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuSubCateDetailSearch:
                startActivity(new Intent(SubCateDetailActivity.this, QuerySearchActivity.class));
                break;
            case R.id.menuHome:
                Intent in = new Intent(SubCateDetailActivity.this, KnongDaiTabActivity.class);
                in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(in);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
