package com.example.vechet.knongdai.service;

import com.example.vechet.knongdai.entity.MainCategoriesResponse;
import com.example.vechet.knongdai.entity.QuerySearchResponse;
import com.example.vechet.knongdai.entity.SubByMainIdResponse;
import com.example.vechet.knongdai.entity.SubCateDetialResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SearchService {
    @GET("/api/v1/categories")
    Call<MainCategoriesResponse> getAllMainCategories();

    @GET("/api/v1/categories/sub-by-main-id/{id}")
    Call<SubByMainIdResponse> getSubCategoriesByMainCateId(@Path("id") int mainId);

    @GET("/api/v1/search")
    Call<SubCateDetialResponse> getAllWebsiteBySubCateId(@Query("page") int page, @Query("cate_id") int subCateId);

    @GET("/api/v1/search")
    Call<QuerySearchResponse> getAllWebsiteByKeyword(@Query("page") int page, @Query("q") String keyword);

    @GET("/api/v1/search/keyword/{keyword}")
    Call<String[]> getAllSuggestKeyword(@Path("keyword") String keyword);
}
