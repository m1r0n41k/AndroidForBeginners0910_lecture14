package com.drondon.androidforbeginners0910_lecture14;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Coin {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("symbol")
    @Expose
    private String symbol;
    @SerializedName("rank")
    @Expose
    private String rank;
    @SerializedName("price_usd")
    @Expose
    private String priceUsd;
    @SerializedName("price_btc")
    @Expose
    private String priceBtc;
    @SerializedName("last_updated")
    @Expose
    private String lastUpdated;
    
    @SerializedName("percent_change_1h")
    @Expose
    private double percentChange1H; 
    
    @SerializedName("percent_change_24h")
    @Expose
    private double percentChange24H;
    
    @SerializedName("percent_change_7d")
    @Expose
    private double percentChange7D;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getRank() {
        return rank;
    }

    public String getPriceUsd() {
        return priceUsd;
    }

    public String getPriceBtc() {
        return priceBtc;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public double getPercentChange1H() {
        return percentChange1H;
    }

    public double getPercentChange24H() {
        return percentChange24H;
    }

    public double getPercentChange7D() {
        return percentChange7D;
    }
}
