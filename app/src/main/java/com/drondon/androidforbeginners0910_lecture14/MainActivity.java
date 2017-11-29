package com.drondon.androidforbeginners0910_lecture14;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity_";

    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    Button updateButton;
    MyRecyclerViewAdapter adapter;

    List<Coin> coinList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);

        adapter = new MyRecyclerViewAdapter(coinList);

        adapter.setItemClickListener(new MyRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(View view, int position) {
                Coin coin = (Coin) view.getTag();
                FirebaseMessaging.getInstance().subscribeToTopic(coin.getName());
            }
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Обновляем информацию по swipe вниз
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                updateData();
            }
        });

        updateButton = findViewById(R.id.button);
        updateButton.setOnClickListener(this);

        refreshConfig();

        updateData();
    }

    @Override
    public void onClick(View v) {
        updateData();
    }

    private void updateData() {
        Call<List<Coin>> call = API.get().getCoins(0, 50);
        call.enqueue(new Callback<List<Coin>>() {
            @Override
            public void onResponse(Call<List<Coin>> call, Response<List<Coin>> response) {
                if (response.isSuccessful()) {
                    List<Coin> coins = response.body();
                    coinList.clear();
                    coinList.addAll(coins);
                    adapter.notifyItemRangeChanged(0, coins.size());
                }
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<List<Coin>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        showComeBackNotification();
    }

    private void showComeBackNotification() {
        //Intent и PendingIntent нужны, чтоб открыть Activity
        Intent intent = new Intent();
        intent.setAction("launch.main.acivity");

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        //NotificationManager отвечает за вывод уведомлений
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        //Уведомление
        Notification notification = new NotificationCompat.Builder(this, BuildConfig.APPLICATION_ID)
                .setAutoCancel(true)                   //Скрывать по клику
                .setSmallIcon(R.mipmap.ic_launcher)    //Картинка в строке состояния
                .setDefaults(Notification.DEFAULT_ALL) //Стандартные звуки и т.д.
                .setTicker("Вернись!")            //Текст в строке состояния
                .setContentTitle("Ты куда пропал?") //Заголовок уведомления
                .setContentText("Биток уже почти 13к. Это фиаско братан!")   //Текст уведомления
                .setWhen(System.currentTimeMillis())   //Время уведомления
                .setContentIntent(pendingIntent)       //Клик на уведомление
                .build();

        //Запуск уведомления
        notificationManager.notify(1, notification);

    }


    private void refreshConfig() {
        final FirebaseRemoteConfig remoteConfig = FirebaseRemoteConfig.getInstance();

        remoteConfig.fetch().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                boolean showBuyNowButton = remoteConfig.getBoolean("show_buy_now");
                Log.d(TAG, "onComplete: showBuyNowButton: " + showBuyNowButton);
                updateButton.setVisibility(showBuyNowButton ? View.VISIBLE : View.GONE);
            }
        });
    }

}
