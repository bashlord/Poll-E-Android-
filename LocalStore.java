package com.jjkbashlord.poll_e;

/**
 * Created by JJK on 10/13/16.
 */

import android.content.Context;
import android.content.SharedPreferences;

public class LocalStore {

    public static final String SP_NAME = "userLocal";

    SharedPreferences userLocalDatabase;

    public LocalStore(Context context) {
        userLocalDatabase = context.getSharedPreferences(SP_NAME, 0);
    }

    public void storeUserData(int uid) {
        SharedPreferences.Editor userLocalDatabaseEditor = userLocalDatabase.edit();
        userLocalDatabaseEditor.putInt("uid", uid);
        userLocalDatabaseEditor.commit();
    }

    public void setUserLoggedIn(boolean loggedIn) {
        SharedPreferences.Editor userLocalDatabaseEditor = userLocalDatabase.edit();
        userLocalDatabaseEditor.putBoolean("loggedIn", loggedIn);
        userLocalDatabaseEditor.commit();
    }

    public void setUserInfo(double p1, int p2, int p3, double p4, int p5, int p6, int p7, int p8, int p9){
        SharedPreferences.Editor userLocalDatabaseEditor = userLocalDatabase.edit();

        userLocalDatabaseEditor.putFloat("w",(float) p1);
        userLocalDatabaseEditor.putFloat("h", (float)p4);
        userLocalDatabaseEditor.putInt("age",p2);
        userLocalDatabaseEditor.putInt("hair",p3);
        userLocalDatabaseEditor.putInt("gen",p5);
        userLocalDatabaseEditor.putInt("eth",p6);
        userLocalDatabaseEditor.putInt("eye",p7);
        userLocalDatabaseEditor.putInt("r",p8);
        userLocalDatabaseEditor.putInt("rel",p9);

        userLocalDatabaseEditor.commit();

    }

    public void clearUserData() {
        SharedPreferences.Editor userLocalDatabaseEditor = userLocalDatabase.edit();
        userLocalDatabaseEditor.clear();
        userLocalDatabaseEditor.commit();
    }

    public int getLoggedInUser() {
        if (userLocalDatabase.getBoolean("loggedIn", false) == false) {
            return -1;
        }

        return userLocalDatabase.getInt("uid", -1);
    }
}