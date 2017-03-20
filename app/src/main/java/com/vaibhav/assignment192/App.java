package com.vaibhav.assignment192;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class App extends Application {

    private static App  mInstance;
    public static final String TAG = App.class
            .getSimpleName();
    private RequestQueue mRequestQueue;
    public App() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;


    }
    public static synchronized App getmInstance(){return mInstance;}

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }
    public <T> void addToRequestQueue(com.android.volley.Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

}
