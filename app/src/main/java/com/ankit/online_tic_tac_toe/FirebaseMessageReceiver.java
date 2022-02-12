package com.ankit.online_tic_tac_toe;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.widget.Button;
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

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static com.ankit.online_tic_tac_toe.Globals.*;

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
            String type = json.getString("type");
            String payload_data = json.getString("msg");
            System.out.println(json);

            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {

                    if (type.matches("move")){
                        this_user_chance = true;
                        Button clicked_button = button_hash.get(payload_data);
                        clicked_button.setText(current_opponent_symbol);
                        user2.add(payload_data);
                        System.out.println(user2+"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
                        result1 = user2.containsAll(wset1);
                        result2= user2.containsAll(wset2);
                        result3 = user2.containsAll(wset3);
                        result4 = user2.containsAll(wset4);
                        result5 = user2.containsAll(wset5);
                        result6 = user2.containsAll(wset6);
                        result7 = user2.containsAll(wset7);
                        result8 = user2.containsAll(wset8);

                        if(result1 || result2 || result3 || result4 || result5 || result6 || result7 || result8)
                        {   System.out.println("winner");

                            Intent intent = new Intent(OnlineGameActivity.activity, MainActivity3.class);
                            intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                            intent.putExtra("win",4);
                            startActivity(intent);

                        }


                        if(chance==9)
                        {
                            System.out.println("draw");
                            Intent intent = new Intent(OnlineGameActivity.activity, MainActivity3.class);
                            intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                            intent.putExtra("win",0);
                            startActivity(intent);
                        }
                        OnlineGameActivity.game_join_message.setText("Your chance");
                    }

                    if (type.matches("joined")){
                        game_started = true;
                        this_user_chance = true;
                        OnlineGameActivity.game_join_message.setText("User joined, your chance");
                    }

                }
            });
        }catch (JSONException e) {
            e.printStackTrace();
        }



    }

}