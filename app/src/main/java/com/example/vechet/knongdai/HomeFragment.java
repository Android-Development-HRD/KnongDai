package com.example.vechet.knongdai;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vechet.knongdai.adapter.HomeFragmentAdapter;
import com.example.vechet.knongdai.callback.OnMainCateClickItemListener;
import com.example.vechet.knongdai.entity.MainCateId;
import com.example.vechet.knongdai.entity.MainCategoriesResponse;
import com.example.vechet.knongdai.service.SearchService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements OnMainCateClickItemListener {


    public HomeFragment() {
        // Required empty public constructor
    }

    public static final String BASE_URL = "http://110.74.194.125:15000";
    private static final String TAG = "HomeFragment";
    private Retrofit retrofit;
    private SearchService service;
    private RecyclerView rvHomeFragment;
    private HomeFragmentAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        initView(v);
        setupRetrofit();
        loadMainCategories();
        //loadMainCategoriesWithObservable();
        initEvent();

        return v;
    }

    /*private void loadMainCategoriesWithObservable() {
        Observable<MainCategoriesResponse> observable = service.getAllMainCategories();
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<MainCategoriesResponse>() {
                    @Override
                    public void onNext(MainCategoriesResponse mainCategoriesResponse) {
                        Log.e(TAG, "onNext: " + mainCategoriesResponse.getData() );
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage() );
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete: "  );
                    }
                });

        observable.unsubscribeOn(Schedulers.io());
    }
*/
    private void initEvent() {

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
                ArrayList<String> mainCateName = new ArrayList<>();
                for(MainCategoriesResponse.DataEntity mainCate : mainCategoriesResponseList){
                    mainCateName.add(mainCate.getCateName());
                }
                Log.e(TAG, "Main Cate Name " + mainCateName );
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

    public void initView(View v) {
        rvHomeFragment = v.findViewById(R.id.rvHomeFragment);
        rvHomeFragment.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new HomeFragmentAdapter(getContext());
        adapter.onClickListener(this);
        rvHomeFragment.setAdapter(adapter);
    }

    @Override
    public void onItemClick(int position) {
        Log.e(TAG, "onItemClick: " + position );
        MainCategoriesResponse.DataEntity mainCates = adapter.getMainCateById(position);
        Log.e(TAG, "onItemClick: " + mainCates.getId() );

        Intent in = new Intent(getContext(), SubCategoriesActivity.class);
        MainCateId mainId = new MainCateId(mainCates.getId(), mainCates.getCateName());
        in.putExtra("id", mainId);
        startActivity(in);
    }
}
