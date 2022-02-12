package com.ankit.online_tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.GoalRow;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MainActivity4 extends AppCompatActivity implements View.OnClickListener {

//    EditText txt=(EditText) findViewById(R.id.box);
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        findViewById(R.id.join).setOnClickListener(this);
        findViewById(R.id.create).setOnClickListener(this);

//        Button play_button = findViewById(R.id.play_offline);
//        play_button.setOnClickListener(new OnClickListener() {
//        @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
//                startActivity(intent);
//
//            }
//        });
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        //EditText txt=(EditText) findViewById(R.id.box);
        Button clicked_button = findViewById(id);
        String a=clicked_button.getText().toString();
        System.out.println(a);

        String s = clicked_button.getResources().getResourceEntryName(id);
        System.out.println(s);
        System.out.println(s.getClass().getName());
        if (s.matches("join")) {
            System.out.println("hiiiiiiiiiiiiiiiiiiii");
            custom_dialog customDialog = new custom_dialog(this);
            customDialog.show();


        } else {
            Intent intent = new Intent(this, OnlineGameActivity.class);
            Globals.user_type = "user_1";
            Globals.current_user_symbol = "X";
            Globals.current_opponent_symbol = "O";
            startActivity(intent);
        }

    }


}