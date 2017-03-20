package com.dbt;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

public class Splash extends AppCompatActivity {
    String TAG = getClass().getSimpleName();
    ImageView logo;
    Animation anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        logo = (ImageView) findViewById(R.id.dbt_logo);
        anim = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        if (isNetworkAvailable()) {
            Toast.makeText(this, "Internet is Available", Toast.LENGTH_SHORT).show();
            //Splash Screen Thread Logic
            anim.setDuration(1000);
            anim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    Toast.makeText(Splash.this, "Animation Started", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    startActivity(new Intent(Splash.this, Login.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    Toast.makeText(Splash.this, "Animation Stopped", Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            logo.startAnimation(anim);
        } else {
            Toast.makeText(this, "No Internet Connection.", Toast.LENGTH_SHORT).show();
            //exit if no internet connection is available
        }
        Log.i(TAG, "onCreate: isInternetConnected -" + isNetworkAvailable());
    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
