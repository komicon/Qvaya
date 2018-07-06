package com.example.thando.qvaya.Driver;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 *
 */

public class MyVolleyHereXul {

    private static MyVolleyHereXul mInstance;
    private RequestQueue mRequestQueue;
    private static Context mCtx;

    private MyVolleyHereXul(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized MyVolleyHereXul getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new MyVolleyHereXul(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

}