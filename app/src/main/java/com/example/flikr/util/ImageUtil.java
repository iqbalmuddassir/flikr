package com.example.flikr.util;

import java.util.Locale;

public class ImageUtil {
    public String formatImageUrl(String farm, String server, String id, String secret){
        String url = "http://farm%s.static.flickr.com/%s/%s_%s.jpg";
        return String.format(Locale.ENGLISH, url, farm, server, id, secret);
    }
}
