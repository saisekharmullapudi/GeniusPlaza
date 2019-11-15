package com.example.genuisplaza.users;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.AsyncTask;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import com.example.genuisplaza.R;
import com.example.genuisplaza.hitserver.DownloadJsonAsyncTask;
import com.example.genuisplaza.model.User;

public class MainActivity extends AppCompatActivity {

    List<User> users;
    Fragment mcontent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        users = new ArrayList<>();

        DownloadJsonAsyncTask downloadJsonAsyncTask = new DownloadJsonAsyncTask(MainActivity.this);
        downloadJsonAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }


    public void loadRecycler(List<User> userlist) {

        users.clear();

        for (int i = 0; i < userlist.size(); i++) {
            users.add(userlist.get(i));
        }


        //loading users list fragment
        mcontent = UsersRecyclerFragment.newInstance(users);
        getSupportFragmentManager().beginTransaction().replace(R.id.usersactvityframelayout,
                mcontent).addToBackStack(null).commitAllowingStateLoss();

    }
}