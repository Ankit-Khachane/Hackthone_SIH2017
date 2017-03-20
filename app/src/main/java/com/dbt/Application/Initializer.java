package com.dbt.Application;

import android.app.Application;

import com.dbt.R;
import com.parse.Parse;
import com.parse.ParseInstallation;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by admin on 3/16/2017.
 */

public class Initializer extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-Medium.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(getResources().getString(R.string.ApplicationId))
                .clientKey(getResources().getString(R.string.AndroidClientKey))
                .server(getResources().getString(R.string.ServerAddress))
                .build());

        ParseInstallation installation = ParseInstallation.getCurrentInstallation();
        installation.saveInBackground();
    }
}
