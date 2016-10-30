package com.jjkbashlord.poll_e;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ViewAnimator;

import org.json.JSONException;

import info.hoang8f.android.segmented.SegmentedGroup;

public class Activity_root extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    LocalStore localStore;
    RestClientRequest req;
    Button bprofile,bnext,bprev, bsetting;
    ViewPager mViewPager;
    ViewPagerAdapter adapter;
    Button btest;
    int curr_q = 0;
    int curr_una = 0;
    int curr_a = 0;
    int flag = 0;
    SegmentedGroup segmentedGroup;
    RadioButton rb_all,rb_una,rb_a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_root);
        mViewPager = (ViewPager) findViewById(R.id.vp_pager);
        mViewPager.addOnPageChangeListener(pageChangeListener);

        bsetting = (Button) findViewById(R.id.bsetting);
        bnext = (Button) findViewById(R.id.bnext);
        bprev = (Button)findViewById(R.id.bprev);
        segmentedGroup = (SegmentedGroup) findViewById(R.id.segmentedGroup);
        rb_all = (RadioButton) segmentedGroup.findViewById(R.id.buttonall);
        rb_a = (RadioButton) segmentedGroup.findViewById(R.id.buttona);
        rb_una = (RadioButton) segmentedGroup.findViewById(R.id.buttonun);
        bprofile = (Button) findViewById(R.id.bProfile);
        bprofile.setOnClickListener(this);
        bsetting.setOnClickListener(this);
        bnext.setOnClickListener(this);
        bprev.setOnClickListener(this);

        //just a button to console log data
        btest = (Button) findViewById(R.id.btest);
        btest.setOnClickListener(this);

        localStore = new LocalStore(this);
        req = new RestClientRequest();

        if(localStore.getLoggedInUser() < 0){
            Log.d("JJK", "getLoggedIn id: "+Integer.toString(localStore.getLoggedInUser()));
            startActivity(new Intent(Activity_root.this, Activity_login.class));
        }
    }

    @Override
    protected void onStart(){
        super.onStart();
        if(((AppDelegate) getApplication()).Q.size() == 0){
            try {
                adapter = new ViewPagerAdapter(getSupportFragmentManager());
                req.OnSetupRequest( localStore.getLoggedInUser(), Activity_root.this);

                //adapter = new ViewPagerAdapter(getSupportFragmentManager(), ((AppDelegate) getApplication()).answered, ((AppDelegate) getApplication()).unanswered, ((AppDelegate) getApplication()).Q.values());
                mViewPager.setOffscreenPageLimit(1);
                mViewPager.setAdapter(adapter);
                rb_all.setChecked(true);
                segmentedGroup.setOnCheckedChangeListener(this);
                //rb_all.setChecked(true);
            } catch (JSONException e) {
                e.printStackTrace();
                failedConnectionAlert();
            }
        }else{
            //rb_all.setChecked(true);

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(localStore.getLoggedInUser() == -1){
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

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if(checkedId == rb_all.getId()){
            flag = 0;
            adapter.segmentFlag(0);
            adapter.notifyDataSetChanged();
            Log.d("JJK","onCheckedChanged()-----> seg0 " + Integer.toString(curr_q));
            mViewPager.setCurrentItem(curr_q);
        }else if(checkedId == rb_una.getId()){
            flag = 1;
            adapter.segmentFlag(1);
            adapter.notifyDataSetChanged();
            Log.d("JJK","onCheckedChanged()-----> seg1 " + Integer.toString(adapter.getFlagIndex(curr_una)));
            mViewPager.setCurrentItem( curr_una);
        }else if(checkedId == rb_a.getId()){
            flag = 2;
            adapter.segmentFlag(2);
            adapter.notifyDataSetChanged();
            Log.d("JJK","onCheckedChanged()-----> seg2 " + Integer.toString(adapter.getFlagIndex(curr_a)));
            mViewPager.setCurrentItem(curr_a);
        }
    }

    @Override
    public void onClick(View v) {
        if(v == bprofile){
            startActivity(new Intent(Activity_root.this, Activity_profile.class));
        }else if(v == bsetting){
            startActivity(new Intent(Activity_root.this, Activity_setting.class));
        }else if(v == btest){
            Log.d("JJK","wutface");
            Log.d("JJK","Counts of Q/unanswered/answerd: " + Integer.toString(((AppDelegate) getApplication()).Q.size())+
                    "/"+Integer.toString(((AppDelegate) getApplication()).unanswered.size())+"/"+
                    Integer.toString(((AppDelegate) getApplication()).answered.size()) );
            adapter.printData();
        }else if(v == bnext){
            if(flag == 0){
                if(curr_q+1 < adapter.qs.size()) {
                    curr_q++;
                    mViewPager.setCurrentItem(curr_q);
                }
            }else if(flag == 1){
                if(curr_una+1 < adapter.unanswered.size()) {
                    curr_una++;
                    mViewPager.setCurrentItem(curr_una);
                }
            }else{
                if(curr_a+1 < adapter.answered.size()) {
                    curr_a++;
                    mViewPager.setCurrentItem(curr_a);
                }
            }
        }else if(v == bprev){
            if(flag == 0){
                if(curr_q-1 >= 0){
                    curr_q--;
                    mViewPager.setCurrentItem(curr_q);
                }
            }else if(flag == 1){
                if(curr_una-1 >= 0) {
                    curr_una--;
                    mViewPager.setCurrentItem(curr_una);
                }
            }else{
                if(curr_a-1 >= 0) {
                    curr_a--;
                    mViewPager.setCurrentItem(curr_a);
                }
            }
        }
    }

    private ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int newPosition) {
            Log.d("JJK", "MAIN ACTIVITY ON PAGE LISTENER MADE");

            if(flag == 0){
                FragmentLifeCycle fragmentToHide = (FragmentLifeCycle)adapter.getItem(curr_q);
                fragmentToHide.onPauseFragment();

                FragmentLifeCycle fragmentToShow = (FragmentLifeCycle)adapter.getItem(newPosition);
                fragmentToShow.onResumeFragment();

                curr_q = newPosition;
            }else if(flag == 1){
                FragmentLifeCycle fragmentToHide = (FragmentLifeCycle)adapter.getItem( curr_una);
                fragmentToHide.onPauseFragment();

                FragmentLifeCycle fragmentToShow = (FragmentLifeCycle)adapter.getItem( newPosition);
                fragmentToShow.onResumeFragment();

                curr_una = newPosition;
            }else{
                FragmentLifeCycle fragmentToHide = (FragmentLifeCycle)adapter.getItem( curr_a);
                fragmentToHide.onPauseFragment();

                FragmentLifeCycle fragmentToShow = (FragmentLifeCycle)adapter.getItem( newPosition);
                fragmentToShow.onResumeFragment();

                curr_a = newPosition;
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) { }

        public void onPageScrollStateChanged(int arg0) { }
    };

    public void refreshFrags(){
        this.adapter.refresh();
        this.adapter.notifyDataSetChanged();
    }

    public void refreshTabs(){
        this.rb_a.setChecked(false);
        this.rb_una.setChecked(false);
        this.rb_all.setChecked(false);
    }

}
