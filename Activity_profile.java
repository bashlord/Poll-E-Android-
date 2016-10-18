package com.jjkbashlord.poll_e;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by JJK on 10/18/16.
 */

public class Activity_profile extends AppCompatActivity implements View.OnTouchListener {
    ListView lv_gender, lv_ages, lv_eth, lv_relstat, lv_belief, lv_eyec, lv_hairc;
    TextView tv_heightval, tv_weightval;
    SeekBar sb_weight, sb_height;

    StringAdapter sa1, sa2, sa3,sa4,sa5, sa6;
    IntAdapter ia1;
    ArrayList<String> genders = new ArrayList<>(Arrays.asList("Pick!","Male", "Female", "Other"));
    ArrayList<String> r_status = new ArrayList<>(Arrays.asList("Please select!","Single", "Taken", "Complicated", "Married", "Other"));
    ArrayList<String> races = new ArrayList<>(Arrays.asList("Please select!", "Asian American","Black/African American", "Native American/Alaska Native",
                                                                    "Native Hawaiian/Other Pacific Islander", "White American","Other"));
    ArrayList<String> religions = new ArrayList<>(Arrays.asList("Pick one!","Judaism", "Christianity", "Islam", "Bahá'í", "Hinduism",
                                                                    "Taoism", "Buddhism", "Sikhism", "Wicca", "Kemetism", "Hellenism", "Agnostic", "Other"));
    ArrayList<String> colorsh = new ArrayList<>(Arrays.asList("Red", "Brown", "Black", "Blond", "Grey", "Purple", "Green","Blue","Other"));
    ArrayList<String> colorse = new ArrayList<>(Arrays.asList("Red", "Brown", "Black", "Blond", "Grey", "Purple", "Green","Blue","Other"));


    ArrayList<Integer> ages = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        lv_gender = (ListView) findViewById(R.id.lv_gender);
        lv_ages = (ListView) findViewById(R.id.lv_ages);
        lv_eth = (ListView) findViewById(R.id.lv_eth);
        lv_relstat = (ListView) findViewById(R.id.lv_relstat);
        lv_belief = (ListView) findViewById(R.id.lv_belief);
        lv_eyec = (ListView) findViewById(R.id.lv_eyec);
        lv_hairc = (ListView) findViewById(R.id.lv_hairc);

        tv_heightval = (TextView) findViewById(R.id.tv_heightval);
        tv_weightval = (TextView) findViewById(R.id.tv_weightval);

        sb_height = (SeekBar) findViewById(R.id.sb_height);
        sb_weight = (SeekBar) findViewById(R.id.sb_weight);

        lv_gender.setOnTouchListener(this);
        lv_eth.setOnTouchListener(this);
        lv_ages.setOnTouchListener(this);
        lv_relstat.setOnTouchListener(this);
        lv_belief.setOnTouchListener(this);
        lv_eyec.setOnTouchListener(this);
        lv_hairc.setOnTouchListener(this);

        sa1 = new StringAdapter(this, genders);
        sa2 = new StringAdapter(this, r_status);
        sa3 = new StringAdapter(this, races);
        sa4 = new StringAdapter(this, religions);
        sa5 = new StringAdapter(this, colorsh);
        sa6 = new StringAdapter(this, colorse);
        ia1 = new IntAdapter(this, ages);

        lv_gender.setAdapter(sa1);
        lv_eth.setAdapter(sa3);
        lv_relstat.setAdapter(sa2);
        lv_belief.setAdapter(sa4);
        lv_hairc.setAdapter(sa5);
        lv_eyec.setAdapter(sa6);
        lv_ages.setAdapter(ia1);


        for(int i = 1; i < 120; i++)
            ages.add(i);

    }

    @Override
    public void onStart(){
        super.onStart();

        
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(v == lv_gender){
            lv_gender.requestDisallowInterceptTouchEvent(true);
            return false;
        }else if(v == lv_eth){
            lv_eth.requestDisallowInterceptTouchEvent(true);
            return false;
        }else if(v == lv_relstat){
            lv_relstat.requestDisallowInterceptTouchEvent(true);
            return false;
        }else if(v == lv_belief){
            lv_belief.requestDisallowInterceptTouchEvent(true);
            return false;
        }else if(v == lv_hairc){
            lv_hairc.requestDisallowInterceptTouchEvent(true);
            return false;
        }else if(v == lv_eyec){
            lv_eyec.requestDisallowInterceptTouchEvent(true);
            return false;
        }else if(v == lv_ages){
            lv_ages.requestDisallowInterceptTouchEvent(true);
            return false;
        }
        return false;
    }
}
