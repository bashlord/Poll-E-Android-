package com.jjkbashlord.poll_e;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by JJK on 10/19/16.
 */


// THIS IS A FRAGMENT, NOT AN ACTIVITY
public class Activity_poll extends Fragment implements FragmentLifeCycle {
    Button byes, bno;
    TextView tv_poll;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_poll, container, false);
        tv_poll = (TextView) view.findViewById(R.id.tv_poll);
        byes = (Button) view.findViewById(R.id.byes);
        bno = (Button) view.findViewById(R.id.bno);


        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onPauseFragment() {

    }

    @Override
    public void onResumeFragment() {

    }
}


