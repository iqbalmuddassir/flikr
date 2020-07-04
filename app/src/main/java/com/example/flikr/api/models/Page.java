package com.example.flikr.api.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Page {
    private int page;
    private int pages;
    @SerializedName("photo") private List<Photo> photos;

    public int getPage() {
        return page;
    }

    public int getPages() {
        return pages;
    }

    public List<Photo> getPhotos() {
        return photos;
    }
}
