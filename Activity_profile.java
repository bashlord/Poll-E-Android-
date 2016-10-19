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
import org.json.*;
import com.loopj.android.http.*;

import cz.msebera.android.httpclient.Header;
/**
 * Created by JJK on 10/18/16.
 */

public class Activity_profile extends AppCompatActivity implements View.OnTouchListener {
    LocalStore localStore;
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
        localStore = new LocalStore(this);
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
        ArrayList info = localStore.getUserInfo();
        /*
        info.add(userLocalDatabase.getInt("gen",-1));
        info.add(userLocalDatabase.getInt("age",-1));
        info.add(userLocalDatabase.getInt("eth",-1));
        info.add(userLocalDatabase.getInt("r",-1));
        info.add(userLocalDatabase.getInt("rel",-1));
        info.add(userLocalDatabase.getFloat("w",-1));
        info.add(userLocalDatabase.getFloat("h",-1));
        info.add(userLocalDatabase.getInt("eye",-1));
        info.add(userLocalDatabase.getInt("hair",-1));
        */
        if((int)info.get(0) != -1){
            lv_gender.setSelection((int)info.get(0));
        }
        if((int)info.get(1) != -1){
            lv_ages.setSelection((int)info.get(1));
        }
        if((int)info.get(2) != -1){
            lv_eth.setSelection((int)info.get(2));
        }
        if((int)info.get(3) != -1){
            lv_relstat.setSelection((int)info.get(3));
        }
        if((int)info.get(4) != -1){
            lv_belief.setSelection((int)info.get(4));
        }
        if((float)info.get(5) != -1){
            Float v = (100*((float)info.get(5)/108));
            sb_height.setProgress(v.intValue());
        }
        if((float)info.get(6) != -1){
            Float v = 100*((float)info.get(6)/300);
            sb_weight.setProgress( v.intValue());
        }
        if((int)info.get(7) != -1){
            lv_eyec.setSelection((int)info.get(7));
        }
        if((int)info.get(8) != -1){
            lv_hairc.setSelection((int)info.get(8));
        }

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


    @Override
    public void onBackPressed(){

        ArrayList info = localStore.getUserInfo();
        RequestParams params = new RequestParams();
        params.put("id", localStore.getLoggedInUser());
        if((int)info.get(0) != lv_gender.getSelectedItemPosition()){
            params.put("p5",lv_gender.getSelectedItemPosition());
            localStore.setInt("gen", lv_gender.getSelectedItemPosition());
        }
        if((int)info.get(1) != lv_ages.getSelectedItemPosition()){
            params.put("p2",lv_ages.getSelectedItemPosition());
            localStore.setInt("age", lv_ages.getSelectedItemPosition());
        }
        if((int)info.get(2) != lv_eth.getSelectedItemPosition()){
            params.put("p6",lv_eth.getSelectedItemPosition());
            localStore.setInt("eth", lv_eth.getSelectedItemPosition());
        }
        if((int)info.get(3) != lv_relstat.getSelectedItemPosition()){
            params.put("p8",lv_relstat.getSelectedItemPosition());
            localStore.setInt("r", lv_relstat.getSelectedItemPosition());
        }
        if((int)info.get(4) !=  lv_belief.getSelectedItemPosition()){
            params.put("p9",lv_belief.getSelectedItemPosition());
            localStore.setInt("rel", lv_belief.getSelectedItemPosition());
        }

        Float h = new Float((sb_height.getProgress()*108)/100);
        if((float)info.get(5) != h){
            params.put("p4",h);
            localStore.setFloat("h", h);

        }

        Float w = new Float((sb_weight.getProgress()*300)/100);
        if((float)info.get(6) != w){
            params.put("p1",w);
            localStore.setFloat("w", w);
        }
        if((int)info.get(7) != lv_eyec.getSelectedItemPosition()){
            params.put("p7",lv_eyec.getSelectedItemPosition());
            localStore.setInt("eye", lv_eyec.getSelectedItemPosition());
        }
        if((int)info.get(8) != lv_hairc.getSelectedItemPosition()){
            params.put("p3",lv_hairc.getSelectedItemPosition());
            localStore.setInt("hair", lv_hairc.getSelectedItemPosition());
        }

        RestClientRequest req = new RestClientRequest();
        try {
            req.OnUpdateBinfo(params);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        super.onBackPressed();
    }
}
