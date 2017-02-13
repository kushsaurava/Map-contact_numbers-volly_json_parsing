package com.awsome.kush.test_demo.Networking;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.awsome.kush.test_demo.Interfaces.AsyncTaskCompleteListener;
import com.awsome.kush.test_demo.Util.Commonutils;

import java.util.HashMap;

public class VollyGetRewuest {
    private AsyncTaskCompleteListener asyncTaskCompleteListener;
    int servicecode;
    CountDownTimer cTimer = null;
    Context context;

    public VollyGetRewuest(final Context context, int GET, HashMap<String, String> map, int getGroups, final AsyncTaskCompleteListener asyncTaskCompleteListener) {

        RequestQueue queue = Volley.newRequestQueue(context);
        String url = map.get("url");
        this.context= context;
        this.servicecode= getGroups;
        this.asyncTaskCompleteListener = asyncTaskCompleteListener;
        startTimer();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        // mTextView.setText("Response is: "+ response.substring(0,500));
                        // asyncTaskCompleteListener.onTaskCompleted(response.toString(), Const.ServiceCode.GET_GROUPS)
                        asyncTaskCompleteListener.onTaskCompleted(response.toString(),servicecode);
                        Log.d("kush", "volley requester response from getmethod " + response.toString());
                        Commonutils.progressdialog_hide();
                        cancelTimer();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("kush", "volley requester response from getmethod " + error);
                cancelTimer();
                String msg = "!!!It looks Like Network connection is too slow.";
                Toast.makeText(context,msg, Toast.LENGTH_SHORT).show();
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    public void startTimer() {
        cTimer = new CountDownTimer(20000, 1000) {

            public void onTick(long millisUntilFinished) {
               // Toast.makeText(activity, "timer start",Toast.LENGTH_SHORT).show();
            }

            public void onFinish() {
                Commonutils.progressdialog_hide();
                String msg = "!!!Your Internet connection is slow.. Please TRY Again";
               Toast.makeText(context,msg, Toast.LENGTH_LONG).show();
               /* Snackbar snackbar = Snackbar
                        .make(drawer, "!No InterNet Connection Please TRY Again", 5000);

                snackbar.show();*/
            }
        };
        cTimer.start();
    }
    //cancel timer
    public void cancelTimer() {
        if (cTimer != null)
            cTimer.cancel();
      //  Toast.makeText(activity, "timer cancel", Toast.LENGTH_SHORT).show();
        Commonutils.progressdialog_hide();
    }

}
