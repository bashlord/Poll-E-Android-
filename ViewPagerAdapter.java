package com.jjkbashlord.poll_e;


/**
 * Created by JJK on 7/21/15.
 */
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Edwin on 15/02/2015.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public List<Fragment> fragments;
    ArrayList<Integer> answered, unanswered,q;
    //ArrayList<Poll> qs;
    public Map<Integer, Poll> qs;

    int flag;
    int qcount, acount, unacount;
    int qindex, aindex, unaindex;
    int currfragid;
    Activity_poll poll1,poll2,poll3;

    // Build a Constructor and assign the passed Values to appropriate values in the class
    public ViewPagerAdapter(FragmentManager fm,  ArrayList ans, ArrayList unans, Map qs ) {
        super(fm);
        this.unanswered = new ArrayList();
        this.answered = new ArrayList();
        this.q = new ArrayList<>();
        this.unanswered.addAll(unans);
        this.answered.addAll(ans);
        this.fragments = new ArrayList<Fragment>();
        poll1 = new Activity_poll();
        poll2 = new Activity_poll();

        this.qs = new HashMap<Integer, Poll>();
        this.qs.putAll(qs);

        this.fragments.add(poll1);
        this.fragments.add(poll2);

        this.flag = 0;
        this.qcount = unans.size() + ans.size();
        this.acount = ans.size();
        this.unacount = unans.size();
        this.unaindex = this.aindex = this.qindex = 0;
        this.currfragid = 0;
        Log.d("JJK", "ViewPagerAdapter MADE, COUNTS q/a/u: " + Integer.toString(this.qcount) +"/"+Integer.toString(this.unacount) +"/"+Integer.toString(this.acount));
    }

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        this.unanswered = new ArrayList();
        this.answered = new ArrayList();
        this.q = new ArrayList<>();
        poll1 = new Activity_poll();
        poll2 = new Activity_poll();
        poll3 = new Activity_poll();
        this.fragments = new ArrayList<Fragment>();
        this.fragments.add(poll1);
        this.fragments.add(poll2);
        this.fragments.add(poll3);
        this.qs =  new HashMap<Integer, Poll>();
        this.flag = 0;
        this.qcount = 0;
        this.acount =0;
        this.unacount = 0;
        this.unaindex = this.aindex = this.qindex = 0;
        this.currfragid = 0;
    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {
        Log.d("JJK", "ViewPagerAdapter FLAG: " + Integer.toString(this.flag) + " for INDEX " + Integer.toString(position));
        Poll p;
        if(this.flag == 0){
            Activity_poll poll = new Activity_poll();
            p = this.qs.get( this.q.get(position));
            return Activity_poll.init(p.id, p.resp, p.q );
        }else if(this.flag ==1){
            Activity_poll poll = new Activity_poll();
            p = this.qs.get( this.unanswered.get(position));
            if(this.qs.containsKey(this.unanswered.get(position))){
                Log.d("JJK", "  qs HAS OBJECT FOR KEY ind/pos " + str(this.unanswered.get(position))+"/"+ str(position));
            }else{
                Log.d("JJK", "  qs DOES NOT HAVE OBJECT FOR KEY ind/pos " + str(this.unanswered.get(position))+"/"+ str(position));

                for(Poll pp : this.qs.values()){
                    Log.d("JJK", str(pp.id) + "/" + str(pp.resp) + "/" + pp.q);
                }
                for(int in:this.qs.keySet()){
                    Log.d("JJK", str(in));
                }

            }
            if(p != null){
                Log.d("JJK", "    getItem id/resp/q" + Integer.toString(p.id) + "/" + Integer.toString(p.resp)+ "/" + p.q);
            }else{
                Log.d("JJK", "   WHY IS THIS FAILING ON " + Integer.toString(this.unanswered.get(position)) + "/" + Integer.toString(position) );
                Log.d("JJK", Integer.toString(this.qs.size()));
            }
            return Activity_poll.init(p.id, p.resp, p.q );
        }else{
            Activity_poll poll = new Activity_poll();
            p = this.qs.get( this.answered.get(position));
            return Activity_poll.init(p.id, p.resp, p.q );
        }
    }

    public void segmentFlag(int i){
        this.flag = i;
    }

    public int getFlagIndex(int i){
        if(this.flag == 0){
            return i;
        }else if(this.flag ==1){
            return unanswered.get(i);
        }else{
            return answered.get(i);
        }
    }

    // This method return the Number of tabs for the tabs Strip
    @Override
    public int getCount() {
        if(this.flag == 0)
            return this.qs.size();
        else if(this.flag == 1)
            return  this.unanswered.size();
        else
            return this.answered.size();
    }

    public void printData(){
        String str1 = "";
        String str2 = "";
        String str3 = "";
        Log.d("JJK", "ViewPagerAdapter print\n COUNTS q/a/u: " + Integer.toString(this.qcount) +"/"+Integer.toString(this.unacount) +"/"+Integer.toString(this.acount));
        int max = this.qs.size();

        for(int i = 0; i <max; i++){
            if(i < this.answered.size())
                str1 += Integer.toString(this.answered.get(i)) + " ";
            if(i < this.unanswered.size())
                str2 += Integer.toString(this.unanswered.get(i))+ " ";
            str3 += Integer.toString(this.qs.get(i).id) + " ";
        }
        Log.d("JJK", "ERRTHANG: " +str3);
        Log.d("JJK", "unanswered: " +str2);
        Log.d("JJK", "answered: " + str1);
    }

    public void refresh(){
        this.flag = 0;
        this.qcount = this.unanswered.size() + this.answered.size();
        this.acount = this.answered.size();
        this.unacount = this.unanswered.size();
        this.unaindex = this.aindex = this.qindex = 0;
        this.currfragid = 0;
    }

    public String str(int i){
        return Integer.toString(i);
    }
}