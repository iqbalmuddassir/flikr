package com.example.flikr.api;

import com.example.flikr.api.models.ApiResponse;
import com.example.flikr.api.models.Photo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiHandler {
    private int pageNumber;
    private final RetrofitProvider retrofitProvider;
    private final ApiCallback callback;

    private boolean hasLastPageReached = false;

    public ApiHandler(ApiCallback callback) {
        this.retrofitProvider = new RetrofitProvider();
        this.callback = callback;
    }

    public void sendSearchQuery(String text) {
        hasLastPageReached = false;
        Map<String, String> queryMap = getQueryParams(text);

        retrofitProvider.getApiService()
                .fetchImageList(queryMap)
                .enqueue(new Callback<ApiResponse>() {

                    @Override
                    public void onResponse(@NonNull Call<ApiResponse> call, @NonNull Response<ApiResponse> response) {
                        final ApiResponse body = response.body();
                        if (isValidResponse(body)) { return; }
                        pageNumber = body.getPage()
                                .getPage();
                        if (pageNumber == body.getPage()
                                .getPages()) { hasLastPageReached = true; }

                        callback.onResponse(body.getPage()
                                .getPhotos());
                    }

                    @Override
                    public void onFailure(@NonNull Call<ApiResponse> call, @NonNull Throwable t) {
                        callback.showToast("Something went wrong! please try again");
                    }
                });
    }

    public void sendNextPageRequest(String queryText) {
        if (hasLastPageReached) {
            callback.showToast("No more pages");
            return;
        }
        if (queryText == null) { return; }
        ++pageNumber;
        Map<String, String> queryMap = getQueryParams(queryText);

        retrofitProvider.getApiService()
                .fetchImageList(queryMap)
                .enqueue(new Callback<ApiResponse>() {

                    @Override
                    public void onResponse(@NonNull Call<ApiResponse> call, @NonNull Response<ApiResponse> response) {
                        final ApiResponse body = response.body();
                        if (isValidResponse(body)) { return; }
                        pageNumber = body.getPage()
                                .getPage();
                        if (pageNumber == body.getPage()
                                .getPages()) { hasLastPageReached = true; }

                        callback.onUpdate(body.getPage()
                                .getPhotos());

                    }

                    @Override
                    public void onFailure(@NonNull Call<ApiResponse> call, @NonNull Throwable t) {
                        callback.showToast("Something went wrong! please try again");
                    }
                });
    }

    private boolean isValidResponse(ApiResponse body) {
        return body == null || body.getPage() == null || body.getPage()
                .getPhotos() == null;
    }

    private Map<String, String> getQueryParams(String text) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put(ApiConstant.KEY_QUERY_METHOD, ApiConstant.VALUE_QUERY_METHOD);
        queryMap.put(ApiConstant.KEY_QUERY_API, ApiConstant.VALUE_QUERY_API_KEY);
        queryMap.put(ApiConstant.KEY_QUERY_FORMAT, ApiConstant.VALUE_QUERY_FORMAT);
        queryMap.put(ApiConstant.KEY_QUERY_NO_JSON_CALLBACK, ApiConstant.VALUE_QUERY_NO_JSON_CALLBACK);
        queryMap.put(ApiConstant.KEY_QUERY_TEXT, text);
        queryMap.put(ApiConstant.KEY_QUERY_PAGE, String.valueOf(pageNumber));
        return queryMap;
    }

    public interface ApiCallback {
        void onResponse(List<Photo> photos);

        void onUpdate(List<Photo> photos);

        void showToast(String message);
    }
}
