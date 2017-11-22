package com.drondon.androidforbeginners0910_lecture14;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by collos on 11/22/17.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {

    private OnItemClickListener itemClickListener;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_info, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // TODO: 11/22/17
        holder.tvSymbol.setText("");
        holder.tvName.setText("");
        holder.tvRank.setText("");
        holder.tvPriceUsd.setText("");
        holder.tvPriceBtc.setText("");
    }

    @Override
    public int getItemCount() {
        // TODO: 11/22/17
        return 0;
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
