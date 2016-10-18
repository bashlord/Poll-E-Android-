package com.jjkbashlord.poll_e;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;

/**
 * Created by JJK on 10/14/16.
 */

public class Activity_login extends AppCompatActivity {
    LocalStore localStore;
    EditText etUsername,etPassword, etCode;
    Button blogin, bsignup;
    RestClientRequest req;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUsername = (EditText) findViewById(R.id.etusername);
        etPassword = (EditText) findViewById(R.id.etpassword);
        etCode = (EditText) findViewById(R.id.etcode);

        blogin = (Button) findViewById(R.id.blogin);
        bsignup = (Button) findViewById(R.id.bsignup);
        localStore = new LocalStore(this);


        blogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etUsername.getText().length() == 0){
                    formAlert(0);
                }else if(etPassword.getText().length() == 0){
                    formAlert(1);
                }else if(etCode.getText().length() == 0)
                    formAlert(2);

                req = new RestClientRequest();
                try {
                    req.LoginPostRequest(etUsername.getText().toString(), Integer.parseInt(etCode.getText().toString()),etPassword.getText().toString(), Activity_login.this);

                } catch (JSONException e) {
                    e.printStackTrace();
                    failedConnectionAlert();
                }
            }
        });

    }

    public void failedConnectionAlert(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(Activity_login.this);
        builder1.setMessage("Connection Error, Please try again.");
        builder1.setCancelable(true);
        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    public void formAlert(int flag){
        String msg = "";
        if(flag == 0)
            msg = "Please put a username!";
        else if(flag == 1)
            msg = "Please put a password!";
        else if(flag == 2)
            msg = "Please put your code!";

        AlertDialog.Builder builder1 = new AlertDialog.Builder(Activity_login.this);
        builder1.setMessage(msg);
        builder1.setCancelable(true);
        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
}
