package com.dbt.Application;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by khach on 19-03-2017.
 */

public class App {
    Context ctx;
    ProgressDialog progressDialog;

    public App(Context ctx) {
        this.ctx = ctx;
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


}
