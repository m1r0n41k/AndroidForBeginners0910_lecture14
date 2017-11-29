package com.drondon.androidforbeginners0910_lecture14;


import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by andriimiroshnychenko on 11/29/17.
 */

public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {

    private static final String TAG = "MyFirebaseInstanceIdSer";

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        String token = FirebaseInstanceId.getInstance().getToken();

        sendTokenToServer(token);

    }

    private void sendTokenToServer(String token) {
        Log.d(TAG , "Token: " + token);
    }
}
