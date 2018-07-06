package com.example.thando.qvaya.pushNitificationRealocatedriver;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 *
 */

public class MyVolleyDelatAlocate {

    private static MyVolleyDelatAlocate mInstance;
    private RequestQueue mRequestQueue;
    private static Context mCtx;

    private MyVolleyDelatAlocate(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized MyVolleyDelatAlocate getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new MyVolleyDelatAlocate(context);
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