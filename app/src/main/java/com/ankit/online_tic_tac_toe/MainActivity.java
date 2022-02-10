package com.ankit.online_tic_tac_toe;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;


public class MainActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button play_button = findViewById(R.id.online);
        Button button=findViewById(R.id.offline);

        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(instanceIdResult -> {
            Globals.fcm_token = instanceIdResult.getToken();
            Globals.new_game_url = String.format("%s/new_game?user_1_fcm_id=%s", Globals.SERVER_POINT, Globals.fcm_token);
            System.out.println(Globals.fcm_token);
            // send it to server
        });

        play_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, MainActivity4.class);
                startActivity(intent);

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);

            }
        });
    }

//    @RequiresApi(api = Build.VERSION_CODES.N)
//    @Override
//    public void onClick(View view) {
//        int id = view.getId();
//
//
//        Button clicked_button = findViewById(id);
//        String s = clicked_button.getText().toString();
////        String s = clicked_button.getResources().getResourceEntryName(id);
//        System.out.println(s);
//        if(s == "PLAY ONLINE") {
//            Intent intent = new Intent(MainActivity.this, MainActivity4.class);
//            startActivity(intent);
//        }else {
//            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
//            startActivity(intent);
//        }
//
//    }
}