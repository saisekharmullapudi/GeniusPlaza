package com.example.genuisplaza.users;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.List;

import com.example.genuisplaza.R;
import com.example.genuisplaza.hitserver.PostUser;
import com.example.genuisplaza.model.User;

public class UsersRecyclerFragment extends Fragment {

    private static final String ARG_USERS = "Users";
    List<User> users;
    RecyclerView myRecyclerView;
    UsersRecyclerAdapter userRecyclerAdapter;


    public UsersRecyclerFragment() {
    }

    public static UsersRecyclerFragment newInstance(List<User> users) {
        UsersRecyclerFragment mr = new UsersRecyclerFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_USERS, (Serializable) users);
        mr.setArguments(args);
        return mr;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            users = (List<User>) getArguments().getSerializable(ARG_USERS);
        }
    }

    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstance) {

        View rootview = inflater.inflate(R.layout.users_recycler_fragment, container, false);


        myRecyclerView = (RecyclerView) rootview.findViewById(R.id.recycleview);

        // specify an adapter
        userRecyclerAdapter = new UsersRecyclerAdapter(getContext(), users);

        myRecyclerView.setAdapter(userRecyclerAdapter);

        // use this setting to improve th performance if you know that changes
        // in content donot change the size of recycle view
        myRecyclerView.setHasFixedSize(true);
        // use a linear layout manager to specify how the items appear in the list
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());

        myRecyclerView.setLayoutManager(mLayoutManager);


        userRecyclerAdapter.setOnItemClickListener(new UsersRecyclerAdapter.OnItemClickListener() {


            @Override
            public void onItemLongClick(View view, int position) {
                users.add(position + 1, cloneUser(users.get(position)));
                userRecyclerAdapter.notifyItemInserted(position + 1);
                PostUser postUser = new PostUser(container.getContext());
                postUser.postToServer(users.get(position + 1));
            }
        });


        return rootview;
    }


    private User cloneUser(User user) {
        User user1 = new User();
        user1.setId(users.size() + 1);
        user1.setEmail(user.getEmail());
        user1.setFirstName(user.getFirstName());
        user1.setLastName(user.getLastName());
        user1.setAvatar(user.getAvatar());

        return user1;

    }
}