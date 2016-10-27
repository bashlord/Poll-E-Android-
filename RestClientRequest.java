package com.jjkbashlord.poll_e;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.json.*;
import com.loopj.android.http.*;

import cz.msebera.android.httpclient.Header;

/**
 * Created by JJK on 10/14/16.
 */

public class RestClientRequest {

    public void LoginPostRequest(String username, int code, String password, final Activity activity) throws JSONException{
        RequestParams params = new RequestParams();
        params.put("u", username);
        params.put("c", code);
        params.put("p", password);
        final LocalStore localStore = new LocalStore(activity);
        final int flag = -1;
        RestClient.post(RestConstants.log, params, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
                if(statusCode >= 200 && statusCode < 300){
                    try {

                        Log.d("JJK", response.toString());
                        int success = response.getInt("success");
                        if(success == 1) {
                            localStore.setUserLoggedIn(true);
                            localStore.storeUserData(response.getInt("id"));
                            double p1 = response.getDouble("p1");
                            double p4 = response.getDouble("p4");

                            int p2 = response.getInt("p2");
                            int p3 = response.getInt("p3");
                            int p5 = response.getInt("p5");
                            int p6 = response.getInt("p6");
                            int p7 = response.getInt("p7");
                            int p8 = response.getInt("p8");
                            int p9 = response.getInt("p9");

                            localStore.setUserInfo(p1,p2,p3,p4,p5,p6,p7,p8,p9);

                            activity.startActivity(new Intent( activity,Activity_root.class ));
                        }else{
                               failedConnectionAlert(activity);

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        failedConnectionAlert(activity);
                    }

                }else{
                    failedConnectionAlert(activity);
                }
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, java.lang.String responseString, java.lang.Throwable throwable){
                failedConnectionAlert(activity);
                Log.d("JJK", "LoginPostReq RESTClient FAILED Attempt:");
                Log.d("JJK", "Code: " + Integer.toString(statusCode) + "\n Response: " + responseString);
            }

        });
    }

    public void OnSetupRequest(int uid, final Activity activity) throws JSONException{
        RequestParams params = new RequestParams();
        params.put("id", uid);

        RestClient.post(RestConstants.onlog, params, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
                if(statusCode >= 200 && statusCode < 300){
                    JSONArray qs, ans;
                    SimpleDateFormat simpleDateFormat;
                    Calendar calander;

                    try {
                        Log.d("JJK", response.toString());

                        calander = Calendar.getInstance();
                        simpleDateFormat = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");



                        qs = response.getJSONArray("qs");
                        ans = response.getJSONArray("ans");

                        int anscount = 0;
                        int p = -1;
                        for(int i = 0; i < qs.length(); i++){
                            int qid = qs.getJSONObject(i).getInt("id");
                            String q = qs.getJSONObject(i).getString("que");
                            Date qt = simpleDateFormat.parse(qs.getJSONObject(i).getString("t"));

                            if(anscount < ans.length()){
                                p = ans.getJSONObject(anscount).getInt("qid");
                            }

                            Poll tpoll = new Poll(q,qid, qt);
                            if(qid == p){
                                tpoll.resp = ans.getJSONObject(anscount).getInt("r");
                                tpoll.rtime = simpleDateFormat.parse(ans.getJSONObject(anscount).getString("t"));

                                ((AppDelegate) activity.getApplication()).answered.add(i);
                                anscount++;
                            }else{
                                ((AppDelegate) activity.getApplication()).unanswered.add(i);
                            }

                            ((AppDelegate) activity.getApplication()).Q.put(i, tpoll);
                        }

                        /*
                        * adapter = new ViewPagerAdapter(getSupportFragmentManager(), ((AppDelegate) getApplication()).answered, ((AppDelegate) getApplication()).unanswered, ((AppDelegate) getApplication()).Q.values());
                mViewPager.setOffscreenPageLimit(2);
                mViewPager.setAdapter(adapter);
                        * */
                        ((Activity_root) activity).adapter.qs.clear();
                        ((Activity_root) activity).adapter.answered.clear();
                        ((Activity_root) activity).adapter.unanswered.clear();

                        ((Activity_root) activity).adapter.qs.addAll( ((AppDelegate) activity.getApplication()).Q.values() );
                        ((Activity_root) activity).adapter.answered.addAll(( (AppDelegate) activity.getApplication()).answered);
                        ((Activity_root) activity).adapter.unanswered.addAll(( (AppDelegate) activity.getApplication()).unanswered);

                        ((Activity_root) activity).adapter.refresh();
                        ((Activity_root) activity).adapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                        failedConnectionAlert(activity);
                    } catch (ParseException e) {
                        e.printStackTrace();
                        failedConnectionAlert(activity);
                    }

                }else{
                    failedConnectionAlert(activity);
                }
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, java.lang.String responseString, java.lang.Throwable throwable){
                failedConnectionAlert(activity);
                Log.d("JJK", "onSetupReq RESTClient FAILED Attempt:");
                Log.d("JJK", "Code: " + Integer.toString(statusCode) + "\n Response: " + responseString);
            }

        });
    }

    public void OnUpdateBinfo(RequestParams params) throws JSONException{

    }

    public void OnSuggest(String p, int i, final Activity activity){
        RequestParams params = new RequestParams();
        params.put("p", p);
        params.put("i", i);
        RestClient.post(RestConstants.setpoll, params, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                int resp = -1;
                // If the response is JSONObject instead of expected JSONArray
                if(statusCode >= 200 && statusCode < 300){
                    try {
                        resp = response.getInt("fin");
                        successConnectionAlert(0, activity);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        failedConnectionAlert(activity);
                    }
                }else{
                    failedConnectionAlert(activity);
                }
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, java.lang.String responseString, java.lang.Throwable throwable){
                failedConnectionAlert(activity);
                Log.d("JJK", "onSuggest RESTClient FAILED Attempt:");
                Log.d("JJK", "Code: " + Integer.toString(statusCode) + "\n Response: " + responseString);
            }
        });
    }

    public void OnPollResp(int uid, int qid, int r, final Activity activity){
        RequestParams params = new RequestParams();
        params.put("u", uid);
        params.put("q", qid);
        params.put("r", r);
        RestClient.post(RestConstants.updatepoll, params, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                int resp = -1;
                // If the response is JSONObject instead of expected JSONArray
                if(statusCode >= 200 && statusCode < 300){
                    try {
                        resp = response.getInt("fin");
                        successConnectionAlert(0, activity);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        failedConnectionAlert(activity);
                    }

                }else{
                    failedConnectionAlert(activity);
                }
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, java.lang.String responseString, java.lang.Throwable throwable){
                failedConnectionAlert(activity);
                Log.d("JJK", "onSuggest RESTClient FAILED Attempt:");
                Log.d("JJK", "Code: " + Integer.toString(statusCode) + "\n Response: " + responseString);
            }
        });
    }

    public void successConnectionAlert(int flag, Activity activity){
        if(flag == 0)
            Toast.makeText(activity, "Suggestion for poll posted!", Toast.LENGTH_SHORT).show();
    }

    public void failedConnectionAlert(Context context){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setMessage("Connection Error, Please try again.");
        builder1.setCancelable(true);
        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

}
