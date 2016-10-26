package com.jjkbashlord.poll_e;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by JJK on 10/19/16.
 */


// THIS IS A FRAGMENT, NOT AN ACTIVITY
public class Activity_poll extends Fragment implements FragmentLifeCycle {
    Button byes, bno;
    TextView tv_poll;
    Poll poll;
    int id;
    RelativeLayout rl_poll;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();
        displayUserDetails();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("JJK", "are the fragments ever being made??????");
        View view = inflater.inflate(R.layout.activity_poll, container, false);
        rl_poll = (RelativeLayout) view.findViewById(R.id.rl_poll);
        tv_poll = (TextView) view.findViewById(R.id.tv_poll);
        byes = (Button) view.findViewById(R.id.byes);
        bno = (Button) view.findViewById(R.id.bno);

        byes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        bno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return view;
    }

    private void displayUserDetails() {

        this.tv_poll.setText(poll.q);
        if(poll.resp != -1){
            rl_poll.setBackgroundColor(getResources().getColor(R.color.answeredgrey));
        }else{
            rl_poll.setBackgroundColor(Color.WHITE);
        }
    }

    @Override
    public void onPauseFragment() {

    }

    @Override
    public void onResumeFragment() {
        Log.d("JJK", "Current Poll id: " + Integer.toString(this.poll.id) + "  response: " + Integer.toString(this.poll.resp) );


    }

}


