package com.jjkbashlord.poll_e;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class Activity_root extends AppCompatActivity {
    LocalStore localStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root);
        localStore = new LocalStore(this);

        if(localStore.getLoggedInUser() < 0){
            Log.d("JJK", "getLoggedIn id: "+Integer.toString(localStore.getLoggedInUser()));
            startActivity(new Intent(Activity_root.this, Activity_login.class));
        }

    }




    public void failedConnectionAlert(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Connection Error, Please try again.");
        builder1.setCancelable(true);
        AlertDialog alert11 = builder1.create();
        alert11.show();

    }
}
