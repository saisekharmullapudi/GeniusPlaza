package com.example.genuisplaza.users;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import com.example.genuisplaza.R;
import com.example.genuisplaza.model.User;


public class UsersRecyclerAdapter extends RecyclerView.Adapter<UsersRecyclerAdapter.ViewHolder> {

    private List<User> mDataset;
    private Context mcontext;
    // constructor
    public UsersRecyclerAdapter(Context myContext, List<User> myDataset) {
        mDataset = myDataset;
        mcontext = myContext;
    }


    static  OnItemClickListener mItemClickListener;

    public interface OnItemClickListener {
        public void onItemLongClick ( View view , int position );
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }


    // Return the size of dataset invoked by layout manager
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView userName;
        public ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView =(ImageView)itemView.findViewById(R.id.imageView);
            userName = (TextView)itemView.findViewById(R.id.userName);

            itemView.setOnLongClickListener(new View.OnLongClickListener(){
                @Override
                public boolean onLongClick(View v) {
                    if (mItemClickListener != null) {
                        if (getAdapterPosition() != RecyclerView.NO_POSITION) {
                            mItemClickListener.onItemLongClick(v, getAdapterPosition());
                        }
                    }
                    return true;
                }
            });


        }

        public void bindUserData(User user, int position){
            userName.setText(user.getFirstName()+" "+user.getLastName());

            Picasso.with(mcontext).load(user.getAvatar()).into(imageView);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_item, parent, false);
        // set view layout, size, margins . paddings etc..
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = mDataset.get(position);

        holder.bindUserData(user, position);
    }


}