package com.example.flikr;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.flikr.api.ApiHandler;
import com.example.flikr.api.models.Photo;
import com.example.flikr.ui.ImageAdapter;
import com.example.flikr.util.SearchTextWatcher;

import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ImageSearchActivity extends AppCompatActivity {

    private ImageAdapter imageAdapter;
    private RecyclerView imageList;
    private EditText searchView;

    private ApiHandler apiHandler;
    private SearchTextWatcher watcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_search);
        setupView();
    }

    private void setupView() {
        imageList = findViewById(R.id.imageList);
        searchView = findViewById(R.id.searchBar);

        // setup recycler view
        imageList.setHasFixedSize(true);
        imageList.setLayoutManager(new GridLayoutManager(this, 3));
        imageAdapter = new ImageAdapter();
        imageList.setAdapter(imageAdapter);

        setupApiHandler();
        addScrollListener();
        addTextWatcher();
    }

    private void setupApiHandler() {
        apiHandler = new ApiHandler(new ApiHandler.ApiCallback() {
            @Override
            public void onResponse(List<Photo> photos) {
                imageAdapter.setData(photos);
            }

            @Override
            public void onUpdate(List<Photo> photos) {
                imageAdapter.updateData(photos);
            }

            @Override
            public void showToast(String message) {
                Toast.makeText(ImageSearchActivity.this, message, Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }

    private void addTextWatcher() {
        watcher = new SearchTextWatcher(new SearchTextWatcher.Callback() {
            @Override
            public void onSearchText(String text) {
                if (text.isEmpty()) {
                    imageAdapter.setData(Collections.<Photo>emptyList());
                    return;
                }
                apiHandler.sendSearchQuery(text);
            }
        });
        searchView.addTextChangedListener(watcher);
    }

    private void addScrollListener() {
        imageList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1)) {
                    apiHandler.sendNextPageRequest(watcher.getQueryText());
                }
            }
        });
    }
}
