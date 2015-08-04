package com.angrychimps.citizenvet;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/*
    Volley is an Android library designed by Google which provides very fast and simple server communication optimized for large numbers of requests
    and a small file sizes. Also provides image loading. Not for use with large files or streaming.

    A single instance of Volley sets up a queue for all server communication and handles threading. Using the enum singleton pattern as recommended
    by Bloch in Effective Java.
 */

public enum VolleySingleton {
    INSTANCE;

    private final RequestQueue requestQueue = Volley.newRequestQueue(App.getAppContext());
    private final ImageLoader imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {

        private final LruCache<String, Bitmap> cache = new LruCache<>((int) (Runtime.getRuntime().maxMemory() / 1024) / 8);

        @Override
        public Bitmap getBitmap(String url) {
            return cache.get(url);
        }

        @Override
        public void putBitmap(String url, Bitmap bitmap) {
            cache.put(url, bitmap);
        }
    });

    public <T> void addToRequestQueue(Request<T> req) {
        requestQueue.add(req);
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }
}