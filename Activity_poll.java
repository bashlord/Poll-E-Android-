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
    RestClientRequest req;
    LocalStore localStore;

    static Activity_poll init(int qid, int resp,String q) {
        Activity_poll apoll = new  Activity_poll();
        // Supply val input as an argument.
        Bundle args = new Bundle();
        args.putInt("qid", qid);
        args.putInt("resp", resp);
        args.putString("q", q);
        apoll.setArguments(args);
        return apoll;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            this.poll = new Poll(getArguments().getString("q"), getArguments().getInt("qid"), getArguments().getInt("resp"));
        }else {

        }
    }

    @Override
    public void onStart() {
        super.onStart();
        displayUserDetails();
        req = new RestClientRequest();
        localStore = new LocalStore(getActivity());
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
                req.OnPollResp( localStore.getLoggedInUser(), poll.id, 1, getActivity());
                if(poll.resp == -1){
                    ((AppDelegate) getActivity().getApplication()).unansweredToAnswered(poll.id);
                    rl_poll.setBackgroundColor(getResources().getColor(R.color.answeredgrey));
                }
                poll.resp = 1;
                ((AppDelegate) getActivity().getApplication()).Q.put(poll.id, poll);
            }
        });

        bno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                req.OnPollResp( localStore.getLoggedInUser(), poll.id, 0, getActivity());
                if(poll.resp == -1){
                    ((AppDelegate) getActivity().getApplication()).unansweredToAnswered(poll.id);
                    rl_poll.setBackgroundColor(getResources().getColor(R.color.answeredgrey));
                }
                poll.resp = 0;
                ((AppDelegate) getActivity().getApplication()).Q.put(poll.id, poll);
            }
        });
        return view;
    }

    private void displayUserDetails() {
        if(poll != null && poll.q != null) {
            this.tv_poll.setText(poll.q);
            if(poll.resp != -1){
                rl_poll.setBackgroundColor(getResources().getColor(R.color.answeredgrey));
            }else{
                rl_poll.setBackgroundColor(Color.WHITE);
            }
        }else {
            this.tv_poll.setText("Currently NOTHING HERE GO AWAY JUST LEAVE ME ALONE :((((");
        }
    }

    @Override
    public void onPauseFragment() {
        if(poll != null && poll.q != null)
            Log.d("JJK", "PAUSED Poll id: " + Integer.toString(this.poll.id) + "  response: " + Integer.toString(this.poll.resp) );
    }

    @Override
    public void onResumeFragment() {
        if(poll != null && poll.q != null)
            Log.d("JJK", "RESUMED Poll id: " + Integer.toString(this.poll.id) + "  response: " + Integer.toString(this.poll.resp) );
    }

}


