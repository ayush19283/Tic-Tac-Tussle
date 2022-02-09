package com.example.zero_cross;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class custom_dialog extends Dialog {


        public Activity activity;
        public Button enter;
        EditText global_username;
        String st;

        public custom_dialog(Activity a) {
            super(a);
            this.activity = a;
        }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_dialog);
        enter=findViewById(R.id.enter);
        global_username = findViewById(R.id.global_username);
        global_username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    String hint = "Searching...";
                    System.out.println("tessssssssssst"+s.toString());
                    st=s.toString();
                               }}

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        enter = (Button) findViewById(R.id.enter);
        enter.setOnClickListener(v -> {
                System.out.println(st);
        });

    }
}