package com.jjkbashlord.poll_e;


import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by JJK on 10/14/16.
 */

public class Poll {
    String q;
    int id, index,resp;
    Date time, rtime;
    boolean isUna;

    public Poll(String q, int id,Date d){
        this.q = q;
        this.id = id;
        this.time = d;
        this.resp = -1;
    }
}
