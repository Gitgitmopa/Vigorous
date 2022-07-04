package com.example.vigorous;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SharedPref {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Context context;

    //Constant

    //Name of Shared preference
    public static final String SHARED_NAME = "userLoginSession";

    //Name of session variable
    public static final String KEY_REMEMBER = "remember";
    public static final String KEY_USERNAME = "user";
    public static final String KEY_PASSWORD = "pass";

    //Create constructor
    public SharedPref (Context context1, String SharedName){
        this.context = context1;

        preferences = context.getSharedPreferences(SharedName,Context.MODE_PRIVATE);
        editor = preferences.edit();

    }
    //Stored the username and pass
    public void createRememberToStored(String username, String password){
        editor.putBoolean(KEY_REMEMBER,true);
        editor.putString(KEY_USERNAME,username);
        editor.putString(KEY_PASSWORD,password);
        editor.apply();
    }
    public HashMap<String, String> getDetailsFromRememberMe(){
        HashMap<String, String> userData = new HashMap<String,String>();

        userData.put(KEY_USERNAME,preferences.getString(KEY_USERNAME,null));
        userData.put(KEY_PASSWORD,preferences.getString(KEY_PASSWORD,null));
        return userData;
    }
    public boolean checkRememberMe() {
        if (preferences.getBoolean(KEY_REMEMBER, false)) {
            return true;
        } else {
            return false;
        }
    }
}
