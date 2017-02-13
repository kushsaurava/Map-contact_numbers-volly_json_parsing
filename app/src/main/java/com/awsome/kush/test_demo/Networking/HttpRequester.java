package com.awsome.kush.test_demo.Networking;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.awsome.kush.test_demo.Interfaces.AsyncTaskCompleteListener;
import com.awsome.kush.test_demo.Util.Commonutils;

import java.util.HashMap;
import java.util.Map;


public class HttpRequester {

    private Context activity;
    private AsyncTaskCompleteListener asyncTaskCompleteListener;
    private Map<String, String> map;
    int servicecode;

    CountDownTimer cTimer = null;
    String URL;
    public HttpRequester(Context activity, int method_type, Map<String, String> map, int servicecode, AsyncTaskCompleteListener asyncTaskCompleteListener) {
        int method;
        this.activity = activity;
        this.asyncTaskCompleteListener = asyncTaskCompleteListener;
        this.map = map;

        this.servicecode = servicecode;
        URL = map.get("url");
        System.out.print("urlll inhhttp"+URL);
        map.remove("url");
        if (method_type == 0) {

            method = Request.Method.GET;
            volley_requester(method,URL);
            startTimer();
        }
        else {
            method = Request.Method.POST;
            volley_requester(method, URL, (map == null) ? null : map);
            startTimer();
        }

        //seekbar = new SeekbarTimer(5 * 1000, 1000);

        if (method == Request.Method.POST) {


        } else {

        }
    }



    public void startTimer() {
        cTimer = new CountDownTimer(20000, 1000) {

            public void onTick(long millisUntilFinished) {
          //       Toast.makeText(activity, "timer start",Toast.LENGTH_SHORT).show();
            }

            public void onFinish() {
                Commonutils.progressdialog_hide();
                String msg = "!!! It looks Like Network connection is too slow.";
                Toast.makeText(activity,msg, Toast.LENGTH_SHORT).show();
            }
        };
        cTimer.start();
    }

    //cancel timer
    public void cancelTimer() {
        if (cTimer != null)
            cTimer.cancel();
       // Toast.makeText(activity, "timer cancel", Toast.LENGTH_SHORT).show();
        Commonutils.progressdialog_hide();
    }

    public void volley_requester(int method, String url, final Map<String, String> requestbody) {


        StringRequest jsonObjRequest = new StringRequest(method, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        asyncTaskCompleteListener.onTaskCompleted(response.toString(), servicecode);
                        Log.d("kush", "volley requester response from POST " + response.toString());
                        cancelTimer();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                if (error instanceof NoConnectionError) {
                    Log.d("kush", "volley requester  network error" + error.toString());
                    String msg = "!!! It looks Like Network connection is too slow.";
                    Toast.makeText(activity,msg, Toast.LENGTH_SHORT).show();
                    cancelTimer();
                }
            }
        }) {

            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params = requestbody;
                return params;
            }

        };


        jsonObjRequest.setRetryPolicy(new DefaultRetryPolicy(
                3000,0,
                1f));

    }

    public void volley_requester(int method,String url) {

        final StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        //mTextView.setText("Response is: "+ response.substring(0,500));
                       asyncTaskCompleteListener.onTaskCompleted(response.toString(), servicecode);
                        Log.d("kush", "volley requester response from getmethod " + response.toString());

                        cancelTimer();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               // mTextView.setText("That didn't work!");
            }
        });

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                3000, 0,
                1f));;
    }
}
