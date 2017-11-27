package com.drondon.androidforbeginners0910_lecture14;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by andriimiroshnychenko on 11/27/17.
 */

//API.get()
public class API {

    private static ServiceApi api;
    public static ServiceApi get() {
        if (api == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://api.coinmarketcap.com/")
                    .build();

            api = retrofit.create(ServiceApi.class);
        }
        return api;
    }

}
