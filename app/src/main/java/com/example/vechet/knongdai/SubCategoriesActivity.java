package com.example.vechet.knongdai;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vechet.knongdai.Language.MultipleLagnuagesActivity;
import com.example.vechet.knongdai.adapter.SubCateAdapter;
import com.example.vechet.knongdai.callback.OnSubCateItemClickListener;
import com.example.vechet.knongdai.entity.MainCateId;
import com.example.vechet.knongdai.entity.SubByMainIdResponse;
import com.example.vechet.knongdai.entity.SubCateId;
import com.example.vechet.knongdai.service.SearchService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SubCategoriesActivity extends MultipleLagnuagesActivity implements OnSubCateItemClickListener {

    public static final String BASE_URL = "http://110.74.194.125:15000";
    private RecyclerView subCateRecyclerView;
    private static final String TAG = "Sub";
    private Retrofit retrofit;
    private SearchService service;
    private SubCateAdapter adapter;
    private List<SubByMainIdResponse.DataEntity> subCate = new ArrayList<>();
    private Toolbar toolbar;
    private ImageView ivSubCateBack;
    private TextView tvSubCateActionBarTitle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_categories);

        initView();
        setupRetrofit();
        initEvent();

        MainCateId mainId = (MainCateId)getIntent().getSerializableExtra("id");
        tvSubCateActionBarTitle.setText(mainId.getMainTitle());
        Log.e(TAG, "onCreate: " + mainId.getMainId() +" , "+ mainId.getMainTitle() );
        loadSubCateByMainCateId(mainId.getMainId());

    }

    private void initEvent() {
        ivSubCateBack.setOnClickListener(v->{
            Intent in = new Intent(SubCategoriesActivity.this, KnongDaiActivity.class);
            in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            finish();
        });
    }

    private void loadSubCateByMainCateId(final int mainId) {
        Call<SubByMainIdResponse> call = service.getSubCategoriesByMainCateId(mainId);
        call.enqueue(new Callback<SubByMainIdResponse>() {
            @Override
            public void onResponse(Call<SubByMainIdResponse> call, Response<SubByMainIdResponse> response) {
                Log.e(TAG, "onResponse: " + response.body().getMsg());
                List<SubByMainIdResponse.DataEntity> subCateName =
                        response.body().getData();
                adapter.getSubCate(subCateName);

            }

            @Override
            public void onFailure(Call<SubByMainIdResponse> call, Throwable t) {
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
        toolbar = findViewById(R.id.subCateToolbar);
        ivSubCateBack = findViewById(R.id.ivSubCateBack);
        tvSubCateActionBarTitle = findViewById(R.id.tvSubCateActionBarTitle);
        subCateRecyclerView = findViewById(R.id.subCateRecyclerView);
        subCateRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SubCateAdapter();
        adapter.OnSubCateClickListener(this);
        subCateRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onSubCateItemClick(int position) {
        Log.e(TAG, "onSubCateItemClick: " + position );
        SubByMainIdResponse.DataEntity subCates = adapter.getSubCateByMainCateId(position);
        Log.e(TAG, "onItemClick: " + subCates.getId());


        Intent in = new Intent(SubCategoriesActivity.this, SubCateDetailActivity.class);
        SubCateId subCateId = new SubCateId(subCates.getId(),subCates.getCateName());
        in.putExtra("subCateId", subCateId);
        startActivity(in);
    }
}