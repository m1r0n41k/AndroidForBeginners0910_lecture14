package com.drondon.androidforbeginners0910_lecture14;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        int id = intent.getIntExtra("id", -1);

        Intent startActivityIntent = new Intent(context, MainActivity.class);
        startActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivityIntent.putExtra("id", id);

        context.startActivity(startActivityIntent);


    }
}
