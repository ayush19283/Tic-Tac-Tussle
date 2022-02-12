package com.ankit.online_tic_tac_toe;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.net.ssl.HttpsURLConnection;

import static com.ankit.online_tic_tac_toe.Globals.*;

public class OnlineGameActivity extends AppCompatActivity implements View.OnClickListener{
    public static TextView game_code, game_join_message;
    public static Button zero_zero, zero_one, zero_two, one_zero, one_one, one_two, two_zero, two_one, two_two;
    public static Activity activity;
    public String user_movement_url;
    public static LinearLayout base_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        base_layout = findViewById(R.id.base_layout);

        activity = this;

        game_code = findViewById(R.id.game_code);
        game_code.setVisibility(View.VISIBLE);
        game_code.setOnClickListener(v -> {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("Copied game id", "Join the awesome Tic-Tac-Toe game using this code: " +GAME_CODE);
            clipboard.setPrimaryClip(clip);
            Toast.makeText(this, "Game code copied to clipboard", Toast.LENGTH_LONG).show();
        });

        game_join_message = findViewById(R.id.game_text);
        game_join_message.setVisibility(View.VISIBLE);

        zero_one = findViewById(R.id.zero_one);
        zero_one.setOnClickListener(this);

        zero_zero = findViewById(R.id.zero_zero);
        zero_zero.setOnClickListener(this);

        zero_two = findViewById(R.id.zero_two);
        zero_two.setOnClickListener(this);

        one_zero = findViewById(R.id.one_zero);
        one_zero.setOnClickListener(this);

        one_one = findViewById(R.id.one_one);
        one_one.setOnClickListener(this);

        one_two = findViewById(R.id.one_two);
        one_two.setOnClickListener(this);

        two_zero = findViewById(R.id.two_zero);
        two_zero.setOnClickListener(this);

        two_one = findViewById(R.id.two_one);
        two_one.setOnClickListener(this);

        two_two = findViewById(R.id.two_two);
        two_two.setOnClickListener(this);

        button_hash.put("zero_zero", zero_zero);
        button_hash.put("zero_one", zero_one);
        button_hash.put("zero_two", zero_two);
        button_hash.put("one_zero", one_zero);
        button_hash.put("one_one", one_one);
        button_hash.put("one_two", one_two);
        button_hash.put("two_zero", two_zero);
        button_hash.put("two_one", two_one);
        button_hash.put("two_two", two_two);



    for (String v : w1) {
        wset1.add(v);
    }
        for (String v : w2) {
        wset2.add(v);
    }

        for (String v : w3) {
        wset3.add(v);
    }
        for (String v : w4) {
        wset4.add(v);
    }
        for (String v : w5) {
        wset5.add(v);
    }
        for (String v : w6) {
        wset6.add(v);
    }
        for (String v : w7) {
        wset7.add(v);
    }
        for (String v : w8) {
        wset8.add(v);
    }
        if (user_type == "user_1") {
            this_user_chance = true;
            new get_game_code().execute(new_game_url);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("New Game");

            builder.setMessage("Click on the game code and share it with your friend to play with them.")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // TODO: handle the OK
                        }
                    });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }

        else {
            this_user_chance = false;
            String game_code_msg = "Game Code: "+GAME_CODE;
            SpannableString str = new SpannableString(game_code_msg);
            str.setSpan(new StyleSpan(Typeface.BOLD), 11, 21, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            str.setSpan(new UnderlineSpan(), 11, 21, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            game_code.setText(str);
            new send_requests().execute(join_game_url.get_url(GAME_CODE));
            game_join_message.setText("Waiting for opponent's move");
        }


}


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View view) {
        if (!game_started){
            Toast.makeText(this, "Please wait for the opponent to join", Toast.LENGTH_LONG).show();
            return;
        }
        if (this_user_chance) {
            int id = view.getId();


            Button clicked_button = findViewById(id);
            String cross_or_dot = clicked_button.getText().toString();

            String s = clicked_button.getResources().getResourceEntryName(id);
            System.out.println(s + "id   id  495u49tujrigp fjk ");

            clicked_button.setText(current_user_symbol);
            user1.add(s);
            System.out.println(user1);
            result1 = user1.containsAll(wset1);
            result2 = user1.containsAll(wset2);
            result3 = user1.containsAll(wset3);
            result4 = user1.containsAll(wset4);
            result5 = user1.containsAll(wset5);
            result6 = user1.containsAll(wset6);
            result7 = user1.containsAll(wset7);
            result8 = user1.containsAll(wset8);


            if (result1 || result2 || result3 || result4 || result5 || result6 || result7 || result8) {
                System.out.println("winner");

                Intent intent = new Intent(OnlineGameActivity.this, MainActivity3.class);
                intent.putExtra("win", 5);
                startActivity(intent);

            }
//            else{
//                clicked_button.setText("0");
//                user2.add(s);
//                System.out.println(user2+"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
//                boolean result1 = user2.containsAll(wset1);
//                boolean result2= user2.containsAll(wset2);
//                boolean result3 = user2.containsAll(wset3);
//                boolean result4 = user2.containsAll(wset4);
//                boolean result5 = user2.containsAll(wset5);
//                boolean result6 = user2.containsAll(wset6);
//                boolean result7 = user2.containsAll(wset7);
//                boolean result8 = user2.containsAll(wset8);
//
//                if(result1 || result2 || result3 || result4 || result5 || result6 || result7 || result8)
//                {   System.out.println("winner");
//
//                    Intent intent = new Intent(OnlineGameActivity.this, MainActivity3.class);
//                    intent.putExtra("win",2);
//                    startActivity(intent);
//
//                }


//                if(chance==9)
//                {
//                    System.out.println("draw");
//                    Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
//                    intent.putExtra("win",0);
//                    startActivity(intent);
//                }
            chance++;
            game_join_message.setText("Opponent's move");
            this_user_chance = false;
            if (user_type == "user_1"){
                new send_requests().execute(user1_movement.get_url(GAME_CODE, s));
            }
            else{
                new send_requests().execute(user2_movement.get_url(GAME_CODE, s));
            }
            if (chance == 9) {
                declare();
            }
        }

        else {
            Toast.makeText(this, "Wait for the opponent's move", Toast.LENGTH_LONG).show();
        }
    }

    public void declare(){
        System.out.println("draw");
        Intent intent = new Intent(OnlineGameActivity.this, MainActivity3.class);
        intent.putExtra("win",3);
        startActivity(intent);
    }


    class get_game_code extends AsyncTask<String, String, String> {

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
                return null;
            }
            return buffer.toString();
        }
        @Override
        protected void onPostExecute(String response) {
            if (response != null) {
                try {
                    JSONObject json = new JSONObject(response);
                    Log.d("Tag", json.toString());
//                    JSONObject jsonResponse = json.getJSONObject("data");
                    int status = json.getInt("status");
                    if (status == 200){
                        GAME_CODE = json.getString("game_id");

                        String game_code_msg = "Game Code: "+GAME_CODE;
                        SpannableString str = new SpannableString(game_code_msg);
                        str.setSpan(new StyleSpan(Typeface.BOLD), 11, 21, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        str.setSpan(new UnderlineSpan(), 11, 21, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        game_code.setText(str);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    class send_requests extends AsyncTask<String, String, String> {

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
                return null;
            }
            return buffer.toString();
        }
        @Override
        protected void onPostExecute(String response) {
            if (response != null) {
                try {
                    JSONObject json = new JSONObject(response);
                    Log.d("Tag", json.toString());
//                    JSONObject jsonResponse = json.getJSONObject("data");
                    int status = json.getInt("status");
                    if (status == 200){

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}