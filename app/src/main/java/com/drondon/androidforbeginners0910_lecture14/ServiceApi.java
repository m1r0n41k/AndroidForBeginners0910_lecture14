package com.drondon.androidforbeginners0910_lecture14;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by andriimiroshnychenko on 11/27/17.
 */

public interface ServiceApi {

    @GET("v1/ticker/")
    Call<List<Coin>> getCoins(@Query("start") int start, @Query("limit") int limit);

}
