package com.drondon.androidforbeginners0910_lecture14;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_info, parent, false);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Coin coin = coinList.get(position);

        holder.tvSymbol.setText(coin.getSymbol());
        holder.tvName.setText(coin.getName());
        holder.tvRank.setText(coin.getRank());
        holder.tvPriceUsd.setText(coin.getPriceUsd());
        holder.tvPriceBtc.setText(coin.getPriceBtc());

    }

    @Override
    public int getItemCount() {
        return coinList.size();
    }

    public void setItemClickListener(@Nullable OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private View cardView;
        TextView tvSymbol, tvName, tvRank, tvPriceUsd, tvPriceBtc;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvSymbol = itemView.findViewById(R.id.tv_symbol);
            tvName = itemView.findViewById(R.id.tv_name);
            tvRank = itemView.findViewById(R.id.tv_rank);
            tvPriceUsd = itemView.findViewById(R.id.tv_price_usd);
            tvPriceBtc = itemView.findViewById(R.id.tv_price_btc);
            cardView = itemView.findViewById(R.id.cardView);

            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (itemClickListener != null) {
                itemClickListener.onItemClicked((View) v.getParent(), getAdapterPosition());
            }
        }
    }


    interface OnItemClickListener {
        void onItemClicked(View view, int position);
    }

}
