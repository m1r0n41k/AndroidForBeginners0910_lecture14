package com.drondon.androidforbeginners0910_lecture14;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * Created by andriimiroshnychenko on 12/4/17.
 */

public class CoinView extends FrameLayout {
    private static NumberFormat df = NumberFormat.getPercentInstance();
    static {
        df.setMinimumFractionDigits(2);
    }

    public CoinView(@NonNull Context context) {
        super(context);
        init(null);
    }

    public CoinView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CoinView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public CoinView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private View cardView;
    private TextView tvSymbol, tvName, tvRank, tvPriceUsd, tvPriceBtc, tvTrendingHour, tvTrendingDay, tvTrendingWeek;


    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.item_main_info, this);

        tvSymbol = findViewById(R.id.tv_symbol);
        tvName = findViewById(R.id.tv_name);
        tvRank = findViewById(R.id.tv_rank);
        tvPriceUsd = findViewById(R.id.tv_price_usd);
        tvPriceBtc = findViewById(R.id.tv_price_btc);
        cardView = findViewById(R.id.cardView);

        tvTrendingHour = findViewById(R.id.tv_trending_hour);
        tvTrendingDay = findViewById(R.id.tv_trending_day);
        tvTrendingWeek = findViewById(R.id.tv_trending_week);
    }


    public void setCoin(Coin coin) {
        tvSymbol.setText(coin.getSymbol());
        tvName.setText(coin.getName());
        tvRank.setText(coin.getRank());
        tvPriceUsd.setText(coin.getPriceUsd());
        tvPriceBtc.setText(coin.getPriceBtc());

        setTraiding(tvTrendingHour, coin.getPercentChange1H());
        setTraiding(tvTrendingDay, coin.getPercentChange24H());
        setTraiding(tvTrendingWeek, coin.getPercentChange7D());

        cardView.setTag(coin);

    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        cardView.setOnClickListener(l);
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

}
