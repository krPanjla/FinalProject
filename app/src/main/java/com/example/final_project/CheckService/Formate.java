package com.example.final_project.CheckService;

import android.util.Log;

import com.example.final_project.Database.useradate.UserDatadbProvider;

public class Formate {
    private static final String TAG = "Formate";

    public static String toUsername(String s) {
        StringBuilder string = new StringBuilder();
        try {
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) != '.' && s.charAt(i) != '#' && s.charAt(i) != '$' && s.charAt(i) != '[' && s.charAt(i) != ']')
                    string.append(s.charAt(i));
            }
            return string.toString().trim();
        }catch (NullPointerException e){
            Log.e(TAG,e+" : Length");
            return null;
        }
    }
}
