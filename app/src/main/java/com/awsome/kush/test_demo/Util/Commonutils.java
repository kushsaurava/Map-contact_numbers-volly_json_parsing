package com.awsome.kush.test_demo.Util;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;


public class Commonutils {
    static ProgressDialog progressDialog;

    public static void showtoast(String msg, Context context) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void progressdialog_show(Context context, String msg) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Connecting to Re#hash...");
        progressDialog.setCancelable(true);
        if (!progressDialog.isShowing())
            progressDialog.show();
    }

    public static void progressdialog_hide() {
        try {
            if (progressDialog != null) {

                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                    progressDialog = null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
