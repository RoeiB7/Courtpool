package com.example.courtpool.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.courtpool.R;
import com.example.courtpool.objects.User;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.List;

public class Adapter_Matches extends RecyclerView.Adapter<Adapter_Matches.MyViewHolder> {


    private List<User> users;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private ItemLongClickListener mItemLongClickListener;

    // _courts is passed into the constructor
    public Adapter_Matches(Context context, List<User> _users) {
        this.mInflater = LayoutInflater.from(context);
        this.users = _users;
    }

    public void updateOneItem(int position) {
        notifyItemChanged(position);
    }

    // inflates the row layout from xml when needed
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_players, parent, false);
        return new MyViewHolder(view);
    }


    // binds the _courts to the TextView in each row
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        User user = users.get(position);
        if (user.getProfileImage().equals("N/A")) {
            Glide.with(mInflater.getContext()).load(R.drawable.ic_user).apply(RequestOptions.circleCropTransform()).into(holder.fragment_matches_IMG_image);
        } else {
            Glide.with(mInflater.getContext()).load(user.getProfileImage()).apply(RequestOptions.circleCropTransform()).into(holder.fragment_matches_IMG_image);
        }
        holder.fragment_matches_LBL_name.setText(user.getName());

        ArrayList<String> courts = user.getCourtLocation();
        StringBuilder court_name = new StringBuilder();
        for (int i = 0; i < courts.size(); i = i + 2) {
            court_name.append(courts.get(i)).append("\n");
        }
        holder.fragment_matches_LBL_courts.setText(court_name);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return users.size();
    }


    // convenience method for getting _courts at click position
    User getItem(int id) {
        return users.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public void setLongClickListener(ItemLongClickListener itemLongClickListener) {
        this.mItemLongClickListener = itemLongClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface ItemLongClickListener {
        void onLongClick(View v, int position);
    }


    // stores and recycles views as they are scrolled off screen
    public class MyViewHolder extends ViewHolder {

        ImageView fragment_matches_IMG_image;
        TextView fragment_matches_LBL_name;
        MaterialTextView fragment_matches_LBL_courts;

        MyViewHolder(View itemView) {
            super(itemView);
            fragment_matches_IMG_image = itemView.findViewById(R.id.fragment_matches_IMG_image);
            fragment_matches_LBL_name = itemView.findViewById(R.id.fragment_matches_LBL_name);
            fragment_matches_LBL_courts = itemView.findViewById(R.id.fragment_matches_LBL_courts);

            itemView.setOnLongClickListener(v -> {
                if (mItemLongClickListener != null) {
                    mItemLongClickListener.onLongClick(v, getAdapterPosition());
                }
                return true;
            });

            itemView.setOnClickListener(v -> {
                if (mClickListener != null) {
                    mClickListener.onItemClick(v, getAdapterPosition());
                }
            });
        }


    }
}

