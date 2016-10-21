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
import android.widget.ViewAnimator;

import org.json.JSONException;

public class Activity_root extends AppCompatActivity implements View.OnClickListener {
    LocalStore localStore;
    RestClientRequest req;
    Button bprofile,bnext,bprev, bsetting;
    ViewAnimator viewAnimator;
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root);

        mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.vp_pager);
        bsetting = (Button) findViewById(R.id.bsetting);
        bnext = (Button) findViewById(R.id.bnext);
        bprev = (Button)findViewById(R.id.bprev);

        //viewAnimator = (ViewAnimator) findViewById(R.id.va_polls);
        final Animation inAnim = AnimationUtils.loadAnimation(this,android.R.anim.slide_in_left);
        final Animation outAnim = AnimationUtils.loadAnimation(this,android.R.anim.slide_out_right);

        viewAnimator.setInAnimation(inAnim);
        viewAnimator.setOutAnimation(outAnim);



        bprofile = (Button) findViewById(R.id.bProfile);
        bprofile.setOnClickListener(this);
        bsetting.setOnClickListener(this);
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
                req.OnSetupRequest( localStore.getLoggedInUser(), Activity_root.this);
                Log.d("JJK","Counts of Q/unanswered/answerd: " + Integer.toString(((AppDelegate) getApplication()).Q.size())+
                        "/"+Integer.toString(((AppDelegate) getApplication()).unanswered.size())+"/"+
                        Integer.toString(((AppDelegate) getApplication()).answered.size()) );
            } catch (JSONException e) {
                e.printStackTrace();
                failedConnectionAlert();
            }
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
    public void onClick(View v) {
        if(v == bprofile){
            /*
            Log.d("JJK","wutface");
            Log.d("JJK","Counts of Q/unanswered/answerd: " + Integer.toString(((AppDelegate) getApplication()).Q.size())+
                    "/"+Integer.toString(((AppDelegate) getApplication()).unanswered.size())+"/"+
                    Integer.toString(((AppDelegate) getApplication()).answered.size()) );
            */

            startActivity(new Intent(Activity_root.this, Activity_profile.class));

        }else if(v == bsetting){
            startActivity(new Intent(Activity_root.this, Activity_setting.class));
        }
    }
}
