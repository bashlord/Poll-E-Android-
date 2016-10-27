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
import java.util.List;
import java.util.Map;

/**
 * Created by Edwin on 15/02/2015.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created
    public List<Fragment> fragments;
    ArrayList<Integer> answered, unanswered;
    ArrayList<Poll> qs;

    int flag;
    int qcount, acount, unacount;
    int qindex, aindex, unaindex;
    int currfragid;
    Activity_poll poll1,poll2;

    // Build a Constructor and assign the passed Values to appropriate values in the class
    public ViewPagerAdapter(FragmentManager fm,  ArrayList ans, ArrayList unans, Collection qs ) {
        super(fm);
        this.unanswered = new ArrayList();
        this.answered = new ArrayList();
        this.unanswered.addAll(unans);
        this.answered.addAll(ans);
        this.fragments = new ArrayList<Fragment>();
        poll1 = new Activity_poll();
        poll2 = new Activity_poll();

        this.qs = new ArrayList();
        this.qs.addAll(qs);

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

    public ViewPagerAdapter(FragmentManager fm,CharSequence mTitles[], int mNumbOfTabsumb) {
        super(fm);
        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;
    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {
        if(this.flag == 0){//all
            if(this.currfragid == 0){
                Log.d("JJK", "???????? IS THIS HAPPENING?");
                Activity_poll poll = new Activity_poll();
                poll.poll = this.qs.get(position);
                return poll;
            }else{
                Activity_poll poll = new Activity_poll();
                poll.poll = this.qs.get(position);
                return poll;
            }
        }else if(this.flag == 1){//unanswered
            Activity_poll poll = new Activity_poll();
            poll.poll = this.qs.get( this.unanswered.get(position));
            return poll;

        }else{//answered
            Activity_poll poll = new Activity_poll();
            poll.poll = this.qs.get( this.answered.get(position));
            return poll;

        }
    }

    public void getNextItem(){
        if(this.flag == 0){//all

        }else if(this.flag == 1){

        }else{

        }
    }

    // This method return the titles for the Tabs in the Tab Strip

    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    // This method return the Number of tabs for the tabs Strip

    @Override
    public int getCount() {
        if(this.flag == 0)
            return this.qcount;
        else if(this.flag == 1)
            return  this.unacount;
        else
            return this.acount;
    }



    public void printData(){
        Log.d("JJK", "ViewPagerAdapter print\n COUNTS q/a/u: " + Integer.toString(this.qcount) +"/"+Integer.toString(this.unacount) +"/"+Integer.toString(this.acount));
    }

    public void refresh(){

        this.flag = 0;
        this.qcount = this.unanswered.size() + this.answered.size();
        this.acount = this.answered.size();
        this.unacount = this.unanswered.size();
        this.unaindex = this.aindex = this.qindex = 0;
        this.currfragid = 0;
    }
}