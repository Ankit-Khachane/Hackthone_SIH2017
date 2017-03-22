package com.dbt;

import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dbt.Application.App;
import com.dbt.UI.ProgressWheel;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Registration extends AppCompatActivity implements View.OnClickListener {

    protected int spinflag = 0;
    protected boolean emailVerified = false;
    protected Animation ani;
    protected Button regBtn;
    protected EditText emailEd;
    protected EditText userEd;
    protected EditText passEd;
    protected TextView tv;
    protected ImageView passThubLogo;
    protected TextInputLayout etPasswordLayout;
    protected LinearLayout passEdBox;
    protected ProgressWheel prog;
    private String TAG = getClass().getSimpleName();
    private App a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.registration);
        initView();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.reg_btn) {

            Log.i(TAG, "onClick: - " + prog.isSpinning());

            if (spinflag == 0) {
                if (!prog.isSpinning()) {
                    prog.spin();
                    spinflag++;
                    String email = emailEd.getText().toString();
                    if (a.verifyUser(email)) {
                        emailVerified = true;
                        //to start dash board activity
                        //TODO: save emailVerified Status of Current Use
                        // TODO: according to the Callback from email verified save shared preference and pass data to dashboard
                    }
                }
            } else if (spinflag == 1 && emailVerified) {
                if (prog.isSpinning()) {
                    prog.stopSpinning();
                }
                a.showProgressDialog("Please Wait", "Registering User...", this);
                spinflag--;
                passEdBox.setVisibility(View.VISIBLE);
                passEdBox.setAnimation(ani);
                //TODO: Add parse signup function from App class
            }
            regBtn.setText("Register");
        }
    }

    private void initView() {
        //TODO : Add Progress dialog at the registration process
        a = new App(this);
        emailEd = (EditText) findViewById(R.id.email_ed);
        userEd = (EditText) findViewById(R.id.user_ed);
        passEd = (EditText) findViewById(R.id.pass_ed);
        regBtn = (Button) findViewById(R.id.reg_btn);
        regBtn.setOnClickListener(Registration.this);
        tv = (TextView) findViewById(R.id.tv);
        tv.setPaintFlags(tv.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        passThubLogo = (ImageView) findViewById(R.id.pass_thub_logo);
        etPasswordLayout = (TextInputLayout) findViewById(R.id.etPasswordLayout);
        passEdBox = (LinearLayout) findViewById(R.id.pass_ed_box);
        passEdBox.setVisibility(View.INVISIBLE);
        ani = AnimationUtils.loadAnimation(this, android.support.v7.appcompat.R.anim.abc_slide_in_bottom);
        prog = (ProgressWheel) findViewById(R.id.loading);
        prog.stopSpinning();

    }


    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
