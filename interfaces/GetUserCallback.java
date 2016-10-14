package com.jjkbashlord.poll_e.interfaces;

/**
 * Created by JJK on 10/13/16.
 */

public  interface GetUserCallback {

    /**
     * Invoked when background task is completed
     */

    public abstract void done(int uid);
}