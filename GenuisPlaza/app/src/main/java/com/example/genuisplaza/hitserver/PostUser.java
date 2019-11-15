package com.example.genuisplaza.hitserver;


import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import com.example.genuisplaza.R;
import com.example.genuisplaza.model.User;

public class PostUser {

    Context mcontext;

    public PostUser(Context context){
        mcontext = context;
    }


    public void postToServer(final User user){

        if(user==null)return;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, mcontext.getString(R.string.users_url), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("response is:",response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.getMessage();

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                return getLoadStartParam(user);
            }
        };

        VolleySingletonQueue.getmInstance(mcontext).addRequestQueue(stringRequest);

    }

    private Map<String, String> getLoadStartParam(User user) {
        Map<String, String> params = new HashMap<>();
        try {
            if(user.getId()>0)
                params.put("Id", String.valueOf(user.getId()));

            if(user.getEmail()!=null)
                params.put("email", user.getEmail());
            if(user.getFirstName()!=null)
                params.put("first_name", user.getFirstName());
            if(user.getLastName()!=null)
                params.put("last_name", user.getLastName());
            if(user.getAvatar()!=null)
                params.put("avatar", user.getAvatar());

        }
        catch (Exception e) {
            e.getMessage();
        }

        return params;
    }
}