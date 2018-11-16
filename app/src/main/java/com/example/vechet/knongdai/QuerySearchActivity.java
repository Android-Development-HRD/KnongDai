package com.example.vechet.knongdai;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vechet.knongdai.Language.MultipleLagnuagesActivity;
import com.example.vechet.knongdai.adapter.QuerySearchAdapter;
import com.example.vechet.knongdai.callback.OnQuerySearchClickItemListener;
import com.example.vechet.knongdai.entity.QuerySearchId;
import com.example.vechet.knongdai.entity.QuerySearchResponse;
import com.example.vechet.knongdai.service.SearchService;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuerySearchActivity extends MultipleLagnuagesActivity implements OnQuerySearchClickItemListener {

    public static final String BASE_URL = "http://110.74.194.125:15000";
    private static final String TAG = "QuerySearchActivity";
    private Retrofit retrofit;
    private SearchService service;
    private RecyclerView querySearchRecyclerview;
    private QuerySearchAdapter adapter;
    private int page = 1;
    private Toolbar toolbar;
    private MaterialSearchView searchView;
    private TextView tvQuerySearchActionBarTitle;
    private ImageView ivQuerySerchBack;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_search);

        initView();
        setupRetrofit();
        onSearchQueryData();
        onSearchSuggestionClickListener();
        initEvent();


    }

    private void onSearchSuggestionClickListener() {

    }

    private void initEvent() {
        ivQuerySerchBack.setOnClickListener(v->{
            Intent in = new Intent(QuerySearchActivity.this, SubCateDetailActivity.class);
            in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            finish();
        });
    }

    private void onSearchQueryData() {
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if(query != null){
                    Call<QuerySearchResponse> call = service.getAllWebsiteByKeyword(page, query);
                    call.enqueue(new Callback<QuerySearchResponse>() {
                        @Override
                        public void onResponse(Call<QuerySearchResponse> call, Response<QuerySearchResponse> response) {

                            Log.e(TAG, "onQuerySearchKeywordResponse: " + response.body().getCode());
                            List<QuerySearchResponse.DataEntity> getallSearchResponse =
                                    response.body().getData();
                            adapter.clearSearchData();
                            adapter.getQuerySearch(getallSearchResponse);
                        }

                        @Override
                        public void onFailure(Call<QuerySearchResponse> call, Throwable t) {
                            Log.e(TAG, "onQuerySearchKeywordFailure: " + t.getMessage() );
                        }
                    });

                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if(!TextUtils.isEmpty(newText)){
                    Call<String[]> call = service.getAllSuggestKeyword(newText);
                    call.enqueue(new Callback<String[]>() {
                        @Override
                        public void onResponse(Call<String[]> call, Response<String[]> response) {
                            Log.e(TAG, "onSearchSuggestResponse: " + response.body() );
                            searchView.setSuggestions(response.body());
                        }

                        @Override
                        public void onFailure(Call<String[]> call, Throwable t) {
                            Log.e(TAG, "onSearchSuggestFailure: " + t.getMessage());
                        }
                    });
                }

                return false;
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
        toolbar = findViewById(R.id.querySearchToolbar);
        setSupportActionBar(toolbar);
        searchView = findViewById(R.id.querySearch);
        ivQuerySerchBack = findViewById(R.id.ivQuerySerchBack);
        querySearchRecyclerview = findViewById(R.id.querySearchRecyclerView);
        querySearchRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapter = new QuerySearchAdapter();
        adapter.onQuerySearchClickListener(this);
        querySearchRecyclerview.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu_querysearch, menu);
        MenuItem menuItem = menu.findItem(R.id.menuQuerySearch);
        searchView.setMenuItem(menuItem);

        menuItem.expandActionView();

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onQuerySearchClickItem(int position) {
        Log.e(TAG, "onQuerySearchClickItem: " + position );
        QuerySearchResponse.DataEntity querySearch = adapter.getQuerySearchById(position);
        Log.e(TAG, "onQuerySearchClickItem: " + querySearch.getTitle() );

        Intent in = new Intent(QuerySearchActivity.this, WebViewActivity.class);
        QuerySearchId querySearchId = new QuerySearchId(
                querySearch.getId(), querySearch.getLink(), querySearch.getTitle()
        );
        in.putExtra("querySearchId", querySearchId);
        startActivity(in);
    }
}
