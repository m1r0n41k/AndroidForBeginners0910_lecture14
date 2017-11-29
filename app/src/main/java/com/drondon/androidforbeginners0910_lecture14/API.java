package com.drondon.androidforbeginners0910_lecture14;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
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

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    // Enable response caching
                    .addNetworkInterceptor(new ResponseCacheInterceptor())
                    .addInterceptor(new OfflineResponseCacheInterceptor())
                    // Set the cache location and size (5 MB)
                    .cache(new Cache(new File(App.get().getCacheDir(),
                            "api"), 5 * 1024 * 1024))
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://api.coinmarketcap.com/")
                    .build();

            api = retrofit.create(ServiceApi.class);
        }
        return api;
    }


    /**
     * Interceptor to cache data and maintain it for a minute.
     * <p>
     * If the same network request is sent within a minute,
     * the response is retrieved from cache.
     */
    private static class ResponseCacheInterceptor implements Interceptor {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            okhttp3.Response originalResponse = chain.proceed(chain.request());
            return originalResponse.newBuilder()
                    .header("Cache-Control", "public, max-age=" + 20)
                    .build();
        }
    }


    /**
     * Interceptor to cache data and maintain it for four weeks.
     * <p>
     * If the device is offline, stale (at most four weeks old)
     * response is fetched from the cache.
     */
    private static class OfflineResponseCacheInterceptor implements Interceptor {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!isNetworkAvailable()) {
                request = request.newBuilder()
                        .header("Cache-Control",
                                "public, only-if-cached, max-stale=" + 2419200)
                        .build();
            }
            return chain.proceed(request);
        }

        public boolean isNetworkAvailable() {
            ConnectivityManager cm = (ConnectivityManager) App.get().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            return netInfo != null && netInfo.isConnectedOrConnecting();
        }
    }
}
