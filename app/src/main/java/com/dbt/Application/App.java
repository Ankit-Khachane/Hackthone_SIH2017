package com.dbt.Application;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Patterns;

import java.util.regex.Pattern;

/**
 * Created by khach on 19-03-2017.
 */

public class App {
    Context ctx;
    ProgressDialog progressDialog;

    public App(Context ctx) {
        this.ctx = ctx;
    }

    public static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    public void showProgressDialog(String title, String Message, Context ctxv) throws NullPointerException {

        progressDialog = new ProgressDialog(ctxv);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(true);
        progressDialog.setTitle(title);
        progressDialog.setMessage(Message);
        progressDialog.show();
    }

    public void stopProgressDilaog() {
        progressDialog.cancel();
    }

    private boolean isValidEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }


}
