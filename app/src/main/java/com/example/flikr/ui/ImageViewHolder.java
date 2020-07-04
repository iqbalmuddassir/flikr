package com.example.flikr.ui;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.flikr.util.ImageUtil;
import com.example.flikr.R;
import com.example.flikr.api.models.Photo;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class ImageViewHolder extends RecyclerView.ViewHolder {
    private ImageUtil imageUtil;

    private ImageView imageView;

    ImageViewHolder(@NonNull View itemView) {
        super(itemView);
        imageUtil = new ImageUtil();

        imageView = itemView.findViewById(R.id.imageView);
    }

    void loadData(Photo photo) {
        Glide.with(imageView.getContext())
                .load(imageUtil.formatImageUrl(photo.getFarm(), photo.getServer(), photo.getId(), photo.getSecret()))
                .into(imageView);

    }
}
