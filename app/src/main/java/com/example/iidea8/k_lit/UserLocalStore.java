package com.example.iidea8.k_lit;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Abhigyan on 8/19/2015.
 */
public class UserLocalStore {

    private static final String SP_NAME = "userDetails";
    SharedPreferences userLocalDatabase;

    public UserLocalStore(Context context){
        userLocalDatabase = context.getSharedPreferences(SP_NAME, 0);
    }

    public void storeUserData(User user){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putString("name", user.name);
        spEditor.putString("email", user.email);
        spEditor.putString("password", user.password);
        spEditor.apply();
    }

    public User getLoggedInUser(){
        String name = userLocalDatabase.getString("NAME", "");
        String email = userLocalDatabase.getString("EMAIL", "");
        String password = userLocalDatabase.getString("PASSWORD", "");

        User storedUser = new User(name, email, password);
        return storedUser;
    }

    public void setUserLoggedIn(boolean loggedIn){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("loggedIn", loggedIn);
        spEditor.apply();
    }

    public boolean getUserLoggedIn(){
        return userLocalDatabase.getBoolean("loggedIn", false);
    }
     public void clearUserData(){
         SharedPreferences.Editor spEditor = userLocalDatabase.edit();
         spEditor.clear();
         spEditor.apply();
     }
}
