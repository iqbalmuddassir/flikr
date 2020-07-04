package com.example.flikr.util;

import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;

public class SearchTextWatcher implements TextWatcher {
    private static final int THRESHOLD = 3;
    private static final int DELAY_MILLIS = 500;

    private final Handler handler;
    private final Runnable runnable;
    private final Callback callback;
    private String queryText;

    public SearchTextWatcher(final Callback callback) {
        this.callback = callback;
        this.handler = new Handler(Looper.getMainLooper());
        this.runnable = new Runnable() {
            @Override
            public void run() {
                callback.onSearchText(queryText);
            }
        };
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.length() < THRESHOLD) {
            callback.onSearchText("");
            return;
        }
        queryText = s.toString();
        handler.removeCallbacks(runnable);
        handler.postDelayed(runnable, DELAY_MILLIS);
    }

    public String getQueryText() {
        return queryText;
    }

    public interface Callback {
        void onSearchText(String text);
    }
}
