package com.jjkbashlord.poll_e;

import org.json.*;
import com.loopj.android.http.*;

import cz.msebera.android.httpclient.Header;

/**
 * Created by JJK on 10/14/16.
 */

public class RestClientRequest {

    public void LoginPostRequest(String username, int code, String password) throws JSONException{
        RequestParams params = new RequestParams();
        params.put("u", username);
        params.put("c", code);
        params.put("p", password);
        RestClient.post("/login.php", params, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {
                // Pull out the first event on the public timeline

                // Do something with the response
                System.out.println(tweetText);
            }

        });

    }

}
