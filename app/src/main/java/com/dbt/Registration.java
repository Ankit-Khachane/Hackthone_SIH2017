package com.dbt;

import android.content.Context;
import android.content.Intent;
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
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Registration extends AppCompatActivity implements View.OnClickListener {

    protected static boolean emailVerified = false;
    protected int spinflag = 0;
    protected Animation ani;
    protected Button regBtn;
    protected EditText emailEd, userEd, passEd;
    protected TextView tv;
    protected ImageView passThubLogo;
    protected TextInputLayout etPasswordLayout;
    protected LinearLayout passEdBox;
    protected ProgressWheel prog;
    private String uname, uemail, upassw;
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
                    regBtn.setEnabled(false);
                    spinflag++;
                    uname = userEd.getText().toString();
                    uemail = emailEd.getText().toString();
                    //add parse cross logic code
                    ParseQuery<ParseObject> query = ParseQuery.getQuery("Admin");
                    query.whereEqualTo("Admin_Email", uemail);
                    query.findInBackground(new FindCallback<ParseObject>() {
                        @Override
                        public void done(List<ParseObject> objects, ParseException e) {
                            if (e == null) {
                                prog.stopSpinning();
                                regBtn.setEnabled(true);
                                regBtn.setText("Register");
                                for (ParseObject s : objects) {
                                    if (s.getString("Admin_Email").equals(uemail)) {
                                        emailVerified = true;
                                        passEdBox.setVisibility(View.VISIBLE);
                                        passEdBox.setAnimation(ani);
                                    }
                                    Log.i(TAG, "done: Data Recived Result " + s.getString("Admin_Email"));
                                }
                                Log.i(TAG, "done: Data Recived From Verification " + objects.size());
                            } else {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            } else if (spinflag == 1 && emailVerified) {
                a.showProgressDialog("Please Wait", "Registering User...", this);
                spinflag--;
                upassw = passEd.getText().toString();
                //call register user parse code.
                //to start dash board activity.
                ParseUser user = new ParseUser();
                user.setUsername(uname);
                user.setEmail(uemail);
                user.setPassword(upassw);
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            startActivity(new Intent(Registration.this, DashBoard.class));
                            //TODO: save emailVerified Status of Current Use
                            //TODO: according to the Callback from email verified save shared preference and pass data to dashboard
                        }
                    }
                });
                a.stopProgressDilaog();

            }

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
