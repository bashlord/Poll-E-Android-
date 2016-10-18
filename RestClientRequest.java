package com.jjkbashlord.poll_e;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.util.Log;

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
        RestClient.post("login.php", params, new JsonHttpResponseHandler(){
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
            public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {
                // Pull out the first event on the public timeline
                if(statusCode >= 200 && statusCode < 300){
                    JSONObject firstEvent = null;
                    try {
                        firstEvent = (JSONObject) timeline.get(0);
                        String tweetText = firstEvent.getString("text");
                        // Do something with the response
                        System.out.println(tweetText);
                    }catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else{
                    //status code is bad, show error
                }
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, java.lang.String responseString, java.lang.Throwable throwable){
                failedConnectionAlert(activity);
            }



        });
    }


    public void failedConnectionAlert(Context context){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setMessage("Connection Error, Please try again.");
        builder1.setCancelable(true);
        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

}
