package com.example.genuisplaza.hitserver;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import com.example.genuisplaza.users.MainActivity;
import com.example.genuisplaza.R;
import com.example.genuisplaza.model.User;
import com.example.genuisplaza.parse.ParseUserData;
import com.example.genuisplaza.utility.MyUtility;

public class DownloadJsonAsyncTask extends AsyncTask<String, Void, String> {
    Context mContext;
    String jsonData;
    List<User> users;
    ParseUserData parseUserData;
    ProgressDialog progDailog;
    String murl;

    public DownloadJsonAsyncTask(Context context) {
        users = new ArrayList<>();
        mContext = context;
        murl = mContext.getString(R.string.users_url);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progDailog = new ProgressDialog(mContext);
        progDailog.setMessage("Loading...");
        progDailog.setIndeterminate(false);
        progDailog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progDailog.setCancelable(true);
        progDailog.show();
    }


    @Override
    protected String doInBackground(String... strings) {

        jsonData = MyUtility.downloadJSONusingHTTPGetRequest(murl);

        return jsonData;
    }

    @Override
    protected void onPostExecute(String jsondata) {

        try {
            if (progDailog != null)
                progDailog.dismiss();
            parseUserData = new ParseUserData();

            Log.d("users", jsondata);

            parseUserData.parse(jsondata);
            Log.d("users", String.valueOf(users.size()));
            List<User> userList = parseUserData.getUsers();
            Log.d("users", String.valueOf(users.size()));
            for (User user : userList) {
                users.add(user);
            }
//            Log.d("users", String.valueOf(users.size()));
            ((MainActivity) mContext).loadRecycler(users);


        } catch (Exception e) {
             e.getMessage();
        }

    }

}
