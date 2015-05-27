package com.angrychimps.appname;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/*
    Volley is an Android library designed by Google which provides very fast and simple server communication optimized for large numbers of requests
    and a small file sizes. Also provides image loading. Not for use with large files or streaming.

    A single instance of Volley sets up a queue for all server communication and handles threading.
 */

public class VolleySingleton {

    private static VolleySingleton instance =null;
    private final ImageLoader imageLoader;
    private RequestQueue requestQueue;

    private VolleySingleton(){
        requestQueue = getRequestQueue();
        imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {

            private final LruCache<String, Bitmap> cache=new LruCache<>((int)(Runtime.getRuntime().maxMemory()/1024)/8);
            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                cache.put(url, bitmap);
            }
        });
    }

    public static synchronized VolleySingleton getInstance() {
        if (instance == null) instance = new VolleySingleton();
        return instance;
    }

    private RequestQueue getRequestQueue(){
        if (requestQueue == null) requestQueue = Volley.newRequestQueue(MainApplication.getAppContext());
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);

    }

    public ImageLoader getImageLoader(){
        return imageLoader;
    }
}