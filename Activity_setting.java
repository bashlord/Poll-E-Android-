package com.jjkbashlord.poll_e;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by JJK on 10/18/16.
 */

public class Activity_setting extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener {
    EditText et_sugg;
    Button b_sugg,blogout;
    RestClientRequest req;
    LocalStore localStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        localStore = new LocalStore(this);
        et_sugg = (EditText) findViewById(R.id.etsugg);
        b_sugg = (Button) findViewById(R.id.bsugg);
        blogout = (Button) findViewById(R.id.blogout);

        this.findViewById(android.R.id.content).setOnTouchListener(this);
        b_sugg.setOnClickListener(this);
        blogout.setOnClickListener(this);

        et_sugg.setOnTouchListener(this);


    }

    @Override
    public void onClick(View v) {
        if(v == b_sugg){
            if( b_sugg.getText().length() != 0 ){
                req.OnSuggest(b_sugg.getText().toString(),localStore.getLoggedInUser(), this );
            }
        }else if(v == blogout){
            localStore.clearUserData();
            onBackPressed();
        }

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(v == et_sugg){
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(this.INPUT_METHOD_SERVICE);
            imm.showSoftInput(et_sugg ,
                    InputMethodManager.SHOW_IMPLICIT);
        }else if(v != et_sugg){
            InputMethodManager imm = (InputMethodManager)
            getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(
                    et_sugg.getWindowToken(), 0);
        }
        return false;
    }


}
