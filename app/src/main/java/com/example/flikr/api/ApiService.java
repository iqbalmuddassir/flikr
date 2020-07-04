package com.example.flikr.api;

import com.example.flikr.api.models.ApiResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface ApiService {
    @GET(ApiConstant.URL)
    Call<ApiResponse> fetchImageList(@QueryMap Map<String, String> queries);
}
