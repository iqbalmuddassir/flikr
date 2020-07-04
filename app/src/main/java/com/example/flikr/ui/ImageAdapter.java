package com.example.flikr.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.flikr.R;
import com.example.flikr.api.models.Photo;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ImageAdapter extends RecyclerView.Adapter<ImageViewHolder> {
    private final List<Photo> photos = new ArrayList<>();

    public void updateData(List<Photo> photos) {
        if (photos == null) { return; }
        this.photos.addAll(photos);
        notifyDataSetChanged();
    }

    public void setData(List<Photo> photos) {
        this.photos.clear();
        updateData(photos);
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        final View view = layoutInflater.inflate(R.layout.item_image, viewGroup, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder imageViewHolder, int i) {
        final Photo photo = photos.get(i);
        imageViewHolder.loadData(photo);
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }
}
