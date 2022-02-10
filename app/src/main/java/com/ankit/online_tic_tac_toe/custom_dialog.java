package com.ankit.online_tic_tac_toe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class custom_dialog extends Dialog {


        public Activity activity;
        public Button enter;
        EditText global_username;
        String st;
        JSONObject jsonResponse = new JSONObject();
        TextView username_hint;
        AsyncTask<String, String, String> task;

        public custom_dialog(Activity a) {
            super(a);
            this.activity = a;
        }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_dialog);
        enter=findViewById(R.id.enter);

        username_hint = findViewById(R.id.hint);
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

                    SpannableString str = new SpannableString(hint);
                    str.setSpan(new ForegroundColorSpan(Color.parseColor("#04007d")), 0, hint.length(), 0);
                    username_hint.setText(str);
                    try {
                        task.cancel(true);
                    } catch (Exception e) {

                    }
                    task = new retrievedata();
                    task.execute(Globals.join_game_url.get_url(s.toString()));

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        enter = (Button) findViewById(R.id.enter);
        enter.setOnClickListener(v -> {
                System.out.println(st);
        });

    }

    class retrievedata extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... arg) {
            String responseString = null;
            HttpURLConnection conn;
            String response;
            URL url = null;
            BufferedReader reader;
            StringBuffer buffer = null;
            try {
                url = new URL(arg[0]);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            try {
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                if (conn.getResponseCode() == HttpsURLConnection.HTTP_OK) {
//                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    try {
                        InputStream in = new BufferedInputStream(conn.getInputStream());

                        InputStream inputStream = conn.getInputStream();
                        buffer = new StringBuffer();
                        if (inputStream == null) {
                            return null;
                        }
                        reader = new BufferedReader(new InputStreamReader(inputStream));

                        String line;
                        while ((line = reader.readLine()) != null)
                            buffer.append(line + "\n");

                        if (buffer.length() == 0)
                            return null;

                        return buffer.toString();
                    } catch (IOException e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                return "error";
            }
            return buffer.toString();
        }
        @Override
        protected void onPostExecute(String response) {
            if (response != null) {
                if (response == "error") {
                    String hint = "Internet error...";
                    jsonResponse = new JSONObject();
                    SpannableString str = new SpannableString(hint);
                    str.setSpan(new ForegroundColorSpan(Color.parseColor("#ff0000")), 0, hint.length(), 0);
                    username_hint.setText(str);
                } else {
                    try {
                        JSONObject json = new JSONObject(response);
                        Log.d("Tag", json.toString());
//                    JSONObject jsonResponse = json.getJSONObject("data");
                        int status = json.getInt("status");
                        if (status == 200) {
                            String hint = "Game exists...";
                            SpannableString str = new SpannableString(hint);
                            str.setSpan(new ForegroundColorSpan(Color.parseColor("#48a868")), 0, hint.length(), 0);
                            username_hint.setText(str);
                        } else {
                            String hint = "Game not found...";
                            SpannableString str = new SpannableString(hint);
                            str.setSpan(new ForegroundColorSpan(Color.parseColor("#c20000")), 0, hint.length(), 0);
                            username_hint.setText(str);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}