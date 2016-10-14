package com.jjkbashlord.poll_e;

import com.loopj.android.http.*;

/**
 * Created by JJK on 10/13/16.
 */

public class RestClient {
    private static final String BASE_URL = "http://jjkbashlord.com/poll";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}