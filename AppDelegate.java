package com.jjkbashlord.poll_e;

import android.app.Application;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by JJK on 10/14/16.
 */

public class AppDelegate extends Application {
    LocalStore localStore;
    public Map<Integer, Poll> Q = new HashMap<Integer, Poll>();;
    public ArrayList answered = new ArrayList();
    public ArrayList unanswered = new ArrayList();


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("JJK", "AppDelegate initialized BEGINNING OF ITS LIFECYCLE");
        localStore = new LocalStore(this);
        int uid = localStore.getLoggedInUser();

    }

    public void clearData(){
        this.Q.clear();
        this.answered.clear();
        this.unanswered.clear();
    }

}
