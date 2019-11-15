package com.example.genuisplaza.hitserver;


import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySingletonQueue {

    private static VolleySingletonQueue mInstance;
    private RequestQueue requestQueue;
    private static Context mContext;


    private VolleySingletonQueue(Context context) {
        mContext = context;
        requestQueue = getRequestQueue();
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return requestQueue;
    }


    public static synchronized VolleySingletonQueue getmInstance(Context context) {
        if (mInstance == null) {
            mInstance = new VolleySingletonQueue(context);
        }
        return mInstance;
    }

    public <T> void addRequestQueue(Request<T> request) {

        request.setRetryPolicy(new DefaultRetryPolicy(
                0,
                -1,
                0));

        getRequestQueue().add(request);
    }
}
