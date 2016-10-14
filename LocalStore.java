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