package com.ankit.online_tic_tac_toe;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

public class FirebaseMessageReceiver extends FirebaseMessagingService {

    @Override
    public void onNewToken(String token) {
        Log.d("TAG", "New token: " + token);
        FirebaseMessaging.getInstance().subscribeToTopic("Tic-Tac-Toe");
    }
    @Override
    public void
    onMessageReceived(RemoteMessage remoteMessage) {
        Log.e("DATAAAAA", remoteMessage.getNotification().getBody() + " " + remoteMessage.getNotification().getBody());
        try {
            JSONObject json = new JSONObject(remoteMessage.getNotification().getBody());
            System.out.println(json.get("type"));
            System.out.println(json.get("msg"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {

            }
        });



    }

}