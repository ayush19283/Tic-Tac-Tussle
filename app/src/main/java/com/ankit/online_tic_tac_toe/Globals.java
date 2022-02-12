package com.ankit.online_tic_tac_toe;

import android.widget.Button;

import com.google.firebase.iid.FirebaseInstanceId;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

interface join_game{
    public String get_url(String game_id);
}

interface user_movement{
    public String get_url(String game_id, String button_id);
}

public class Globals {

    public static boolean result1;
    public static boolean result2;
    public static boolean result3;
    public static boolean result4;
    public static boolean result5;
    public static boolean result6;
    public static boolean result7;
    public static boolean result8;

    public static Set<String> user2=new HashSet<String>();

    public static HashMap<String, Button> button_hash = new HashMap<String, Button>();

    public static String SERVER_POINT = "https://test-app-goo.herokuapp.com/";
    public static Boolean game_started = false;
    public static String user_type;
    public static String GAME_CODE;

    public static String current_user_symbol;
    public static String current_opponent_symbol;

    public static String fcm_token;
    public static String new_game_url = String.format("%s/new_game?user_1_fcm_id=%s", SERVER_POINT, fcm_token);
    public static join_game join_game_url = (game_id) -> String.format("%s/join_game?game_id=%s&user_2_fcm_id=%s", SERVER_POINT, game_id, fcm_token);

    public static join_game check_game_url = (game_id) -> String.format("%s/check_game?game_id=%s", SERVER_POINT, game_id);

    public static user_movement user1_movement = (game_id, button_id) -> String.format("%s/user_1_move?game_id=%s&button_id=%s", SERVER_POINT, game_id, button_id);

    public static user_movement user2_movement = (game_id, button_id) -> String.format("%s/user_2_move?game_id=%s&button_id=%s", SERVER_POINT, game_id, button_id);

    public static boolean this_user_chance = false;

    public static int chance=0;
    public static int win;

    public static String[] w1= {"zero_zero","zero_one","zero_two"};
    public static String[] w2 ={"one_zero","one_one","one_two"};
    public static String[] w3= {"two_zero","two_one","two_two"};
    public static String[] w4= {"zero_zero","one_zero","two_zero"};
    public static String[] w5={"zero_one","one_one","two_one"};
    public static String[] w6={"zero_two","one_two","two_two"};
    public static String[] w7={"zero_zero","one_one","two_two"};
    public static String[] w8={"zero_two","one_one","two_zero"};

    // List<String> w1=new ArrayList<String>();
    //w1.add()
    public static Set<String> wset1 = new HashSet<String>();
    public static Set<String> wset2 = new HashSet<String>();
    public static Set<String> wset3 = new HashSet<String>();
    public static Set<String> wset4 = new HashSet<String>();
    public static Set<String> wset5 = new HashSet<String>();
    public static Set<String> wset6 = new HashSet<String>();
    public static Set<String> wset7 = new HashSet<String>();
    public static Set<String> wset8 = new HashSet<String>();


    public static Set<String> user1=new HashSet<String>();

    public static void reset_variables(){
        result1 = false;
        result2 = false;
        result3 = false;
        result4 = false;
        result5 = false;
        result6 = false;
        result7 = false;
        result8 = false;

        user2=new HashSet<String>();

        button_hash = new HashMap<String, Button>();

        game_started = false;
        user_type = "";
        GAME_CODE = "";

        current_user_symbol = "";
        current_opponent_symbol = "";

        this_user_chance = false;

        chance=0;

        // List<String> w1=new ArrayList<String>();
        //w1.add()
         wset1 = new HashSet<String>();
         wset2 = new HashSet<String>();
         wset3 = new HashSet<String>();
         wset4 = new HashSet<String>();
         wset5 = new HashSet<String>();
         wset6 = new HashSet<String>();
         wset7 = new HashSet<String>();
         wset8 = new HashSet<String>();


         user1=new HashSet<String>();
    }
}
