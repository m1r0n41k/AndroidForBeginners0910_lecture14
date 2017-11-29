package com.drondon.androidforbeginners0910_lecture14;

import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.List;

/**
 * Created by collos on 11/22/17.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {

    private NumberFormat df = NumberFormat.getPercentInstance();
    {
        df.setMinimumFractionDigits(2);
    }


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

        setTraiding(holder.tvTrendingHour, coin.getPercentChange1H());
        setTraiding(holder.tvTrendingDay, coin.getPercentChange24H());
        setTraiding(holder.tvTrendingWeek, coin.getPercentChange7D());

        holder.cardView.setTag(coin);

    }

    @Override
    public int getItemCount() {
        return coinList.size();
    }

    public void setItemClickListener(@Nullable OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    private void setTraiding(TextView textView, double value) {
        textView.setText(df.format(value / 100.0));
        textView.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(textView.getContext(), getTraidingImageResource(value)), null, null, null);
    }

    @DrawableRes
    private int getTraidingImageResource(double value) {
        int compare = Double.compare(value, 0.0);
        switch (compare) {
            default:
            case 0:
                return R.drawable.ic_trending_flat;
            case 1:
                return R.drawable.ic_trending_up;
            case -1:
                return R.drawable.ic_trending_down;
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private View cardView;
        TextView tvSymbol, tvName, tvRank, tvPriceUsd, tvPriceBtc, tvTrendingHour, tvTrendingDay, tvTrendingWeek;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvSymbol = itemView.findViewById(R.id.tv_symbol);
            tvName = itemView.findViewById(R.id.tv_name);
            tvRank = itemView.findViewById(R.id.tv_rank);
            tvPriceUsd = itemView.findViewById(R.id.tv_price_usd);
            tvPriceBtc = itemView.findViewById(R.id.tv_price_btc);
            cardView = itemView.findViewById(R.id.cardView);

            tvTrendingHour = itemView.findViewById(R.id.tv_trending_hour);
            tvTrendingDay = itemView.findViewById(R.id.tv_trending_day);
            tvTrendingWeek = itemView.findViewById(R.id.tv_trending_week);

            cardView.setOnClickListener(this);
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
