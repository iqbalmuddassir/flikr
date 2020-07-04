package com.example.flikr.api.models;

import com.google.gson.annotations.SerializedName;

public class ApiResponse {
    @SerializedName("photos") private Page page;

    public Page getPage() {
        return page;
    }
}
