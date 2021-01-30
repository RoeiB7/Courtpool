package com.example.courtpool.utilities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.example.courtpool.R;
import com.example.courtpool.objects.Court;

import java.util.List;

public class Adapter_Court extends RecyclerView.Adapter<Adapter_Court.MyViewHolder> {

    private List<Court> courts;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // _courts is passed into the constructor
    public Adapter_Court(Context context, List<Court> _courts) {
        this.mInflater = LayoutInflater.from(context);
        this.courts = _courts;
    }

    // inflates the row layout from xml when needed
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_court, parent, false);
        return new MyViewHolder(view);
    }


    // binds the _courts to the TextView in each row
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Court court = courts.get(position);
        holder.court_LBL_name.setText(court.getName());
        holder.court_LBL_address.setText(court.getAddress());
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return courts.size();
    }


    // convenience method for getting _courts at click position
    Court getItem(int id) {
        return courts.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }


    // stores and recycles views as they are scrolled off screen
    public class MyViewHolder extends ViewHolder {

        ImageView court_IMG_checkmark;
        TextView court_LBL_address;
        TextView court_LBL_name;

        MyViewHolder(View itemView) {
            super(itemView);
            court_IMG_checkmark = itemView.findViewById(R.id.court_IMG_checkmark);
            court_LBL_address = itemView.findViewById(R.id.court_LBL_address);
            court_LBL_name = itemView.findViewById(R.id.court_LBL_name);
            itemView.setOnClickListener(v -> {
                if (mClickListener != null) {
                    mClickListener.onItemClick(v, getAdapterPosition());
                }
            });
        }


    }
}

