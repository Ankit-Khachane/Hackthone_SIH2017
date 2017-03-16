package com.dbt.Application;

import android.app.Application;

import com.dbt.R;
import com.parse.Parse;
import com.parse.ParseInstallation;

/**
 * Created by admin on 3/16/2017.
 */

public class Initializer extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(getResources().getString(R.string.ApplicationId))
                .clientKey(getResources().getString(R.string.AndroidClientKey))
                .server(getResources().getString(R.string.ServerAddress))
                .build());

        ParseInstallation installation = ParseInstallation.getCurrentInstallation();
        installation.saveInBackground();
    }
}
