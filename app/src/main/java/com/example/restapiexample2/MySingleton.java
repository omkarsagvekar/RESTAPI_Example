package com.example.restapiexample2;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MySingleton {
    private static MySingleton instance;
    private static Context ctx;
    private RequestQueue requestQueue;

    private MySingleton(Context context){
        ctx = context;
        requestQueue = getRequestQueue();
    }

    private RequestQueue getRequestQueue() {
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        }
        return requestQueue;
    }

    public static synchronized MySingleton getInstance(Context context){
        if(instance == null){
            instance = new MySingleton(context);
        }
        return instance;
    }
    public <T> void addToRequestQueue(Request<T> req){ getRequestQueue().add(req);}
}
