package com.drondon.androidforbeginners0910_lecture14;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by collos on 11/22/17.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {

    private OnItemClickListener itemClickListener;

    List<Coin> coinList;

    public MyRecyclerViewAdapter(List<Coin> coinList) {
        this.coinList = coinList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_coin, parent, false);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Coin coin = coinList.get(position);
        holder.coinView.setCoin(coin);
    }

    @Override
    public int getItemCount() {
        return coinList.size();
    }

    public void setItemClickListener(@Nullable OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CoinView coinView;

        public MyViewHolder(View itemView) {
            super(itemView);
            coinView = itemView.findViewById(R.id.coinView);
            coinView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (itemClickListener != null) {
                itemClickListener.onItemClicked(v, getAdapterPosition());
            }
        }
    }


    interface OnItemClickListener {
        void onItemClicked(View view, int position);
    }

}
