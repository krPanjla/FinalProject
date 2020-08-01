package com.example.final_project.CheckService;

import com.example.final_project.Database.useradate.UserDatadbProvider;

public class Formate {
    public static String toUsername(String s){
        StringBuilder string = new StringBuilder();
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)!='.' && s.charAt(i)!='#' && s.charAt(i)!='$' && s.charAt(i)!='[' && s.charAt(i)!=']')
                string.append(s.charAt(i));
        }
        return string.toString();
    }
}
