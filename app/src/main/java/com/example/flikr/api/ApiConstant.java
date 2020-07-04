package com.example.flikr.api;

public interface ApiConstant {
    String BASE_URL = "https://api.flickr.com";
    String URL = "/services/rest";

    // Query params
    String KEY_QUERY_METHOD = "method";
    String KEY_QUERY_API = "api_key";
    String KEY_QUERY_FORMAT = "format";
    String KEY_QUERY_NO_JSON_CALLBACK = "nojsoncallback";
    String KEY_QUERY_TEXT = "text";
    String KEY_QUERY_PAGE = "page";

    // Query values
    String VALUE_QUERY_METHOD = "flickr.photos.search";
    String VALUE_QUERY_API_KEY = "c1865c2463c23a41ec42a4c2edb33a68";
    String VALUE_QUERY_FORMAT = "json";
    String VALUE_QUERY_NO_JSON_CALLBACK = "1";
}
