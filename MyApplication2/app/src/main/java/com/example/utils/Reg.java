package com.example.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Reg {
    public static boolean checkphone(String  phone) {
        String regex = "^1{1}[0-9]{10}$";
        Pattern pattern= Pattern.compile(regex);
        Matcher matcher=pattern.matcher(phone);
        return matcher.matches();
    }
    public static boolean isNum(String str){
        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }
}
