package com.dbt;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.IdRes;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dbt.Application.App;
import com.dbt.Application.PreferenceManager;
import com.dbt.UI.ProgressWheel;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Registration extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, View.OnLongClickListener {

    protected static boolean emailVerified = false;
    protected int spinflag = 0;
    protected Animation ani;
    protected Button regBtn;
    protected EditText emailEd, userEd, passEd, lastUserEd;
    protected TextView tv, detailTv;
    protected ImageView passThubLogo;
    protected TextInputLayout etPasswordLayout;
    protected LinearLayout passEdBox;
    protected ProgressWheel prog;
    protected RadioButton profRd;
    protected RadioButton stdRd;
    protected RadioGroup rbGrp;
    protected TextView userTv;
    private PreferenceManager pm;
    private String uname, uemail, upassw, utype, user_mailtag, rf_name, rl_name;
    private ParseObject tempP;
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
                    uname = userEd.getText().toString();
                    uemail = emailEd.getText().toString();
                    if (a.isValidEmail(uemail)) {
                        Log.i(TAG, "onClick: Email Validated");
                    }
                    if (uname.matches("") || uemail.matches("")) {
                        Toast.makeText(this, "Please Enter Details", Toast.LENGTH_SHORT).show();
                    } else {
                        //add parse cross logic code
                        //TODO: add spinner for dynamic table cross check logic
                        ParseQuery<ParseObject> query = ParseQuery.getQuery(utype);
                        query.whereEqualTo(user_mailtag, uemail);
                        query.findInBackground(new FindCallback<ParseObject>() {
                            @Override
                            public void done(List<ParseObject> objects, ParseException e) {
                                if (e == null) {
                                    prog.stopSpinning();
                                    regBtn.setEnabled(true);
                                    regBtn.setText("Register");
                                    for (ParseObject s : objects) {
                                        if (s.getString(user_mailtag).equals(uemail)) {
                                            emailVerified = true;
                                            passEdBox.setVisibility(View.VISIBLE);
                                            passEdBox.setAnimation(ani);
                                            tempP = s;
                                            spinflag++;
                                        }
                                        Log.i(TAG, "done: Data Recived Result " + s.getString(user_mailtag));
                                    }
                                    Log.i(TAG, "done: Data Recived From Verification " + objects.size());
                                } else {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                }
            } else if (spinflag == 1 && emailVerified) {
                a.showProgressDialog("Please Wait", "Registering User...", this);
                spinflag--;
                upassw = passEd.getText().toString();
                if (upassw.matches("") || uname.matches("") || uemail.matches("")) {
                    Toast.makeText(this, "Please Enter Details", Toast.LENGTH_SHORT).show();
                } else {
                    //call register user parse code.
                    //to start dash board activity.
                    final ParseUser user = new ParseUser();
                    user.setUsername(uname);
                    user.setEmail(uemail);
                    user.setPassword(upassw);
                    user.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                ParseUser u = ParseUser.getCurrentUser();
                                if (u.getBoolean("emailVerified")) {
                                    //set session sharedstatus
                                    String temp_name = tempP.get(rf_name) + " " + tempP.getString(rl_name);
                                    pm.setSession(temp_name,
                                            tempP.getString(user_mailtag),
                                            "useretyape",
                                            false,
                                            true,
                                            true,
                                            tempP.getString("Admin_uuid"));
                                    startActivity(new Intent(Registration.this, DashBoard.class));
                                    finish();
                                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                                } else {
                                    startActivity(new Intent(Registration.this, DashBoard.class));
                                    finish();
                                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                                }
                                //TODO: save emailVerified Status of Current Use
                                //TODO: according to the Callback from email verified save shared preference and pass data to dashboard
                            }
                        }
                    });
                }
                a.stopProgressDilaog();
            }
        }
    }

    private void initView() {
        //TODO : Add Progress dialog at the registration process
        a = new App(this);
        passEdBox = (LinearLayout) findViewById(R.id.pass_ed_box);
        emailEd = (EditText) findViewById(R.id.email_ed);
        userEd = (EditText) findViewById(R.id.first_user_ed);
        passEd = (EditText) findViewById(R.id.pass_ed);
        lastUserEd = (EditText) findViewById(R.id.last_user_ed);
        etPasswordLayout = (TextInputLayout) findViewById(R.id.etPasswordLayout);
        regBtn = (Button) findViewById(R.id.reg_btn);
        prog = (ProgressWheel) findViewById(R.id.loading);
        tv = (TextView) findViewById(R.id.tv);
        detailTv = (TextView) findViewById(R.id.detail_tv);
        passThubLogo = (ImageView) findViewById(R.id.pass_thub_logo);
        regBtn.setOnClickListener(Registration.this);
        tv.setPaintFlags(tv.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        passEdBox.setVisibility(View.INVISIBLE);
        ani = AnimationUtils.loadAnimation(this, android.support.v7.appcompat.R.anim.abc_slide_in_bottom);
        pm = new PreferenceManager(this);
        prog.stopSpinning();
        profRd = (RadioButton) findViewById(R.id.prof_rd);
        profRd.setOnLongClickListener(this);
        stdRd = (RadioButton) findViewById(R.id.std_rd);
        stdRd.setOnLongClickListener(this);
        rbGrp = (RadioGroup) findViewById(R.id.rb_grp);
        rbGrp.setOnCheckedChangeListener(this);
        userTv = (TextView) findViewById(R.id.user_tv);
        userTv.setPaintFlags(tv.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

    }

    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.prof_rd:
                utype = "Professor";
                user_mailtag = "Prof_Email";
                rf_name = "Prof_F_name";
                rl_name = "Prof_L_name";
                profRd.setEnabled(false);
                stdRd.setEnabled(false);
                Toast.makeText(this, "Proffessor", Toast.LENGTH_SHORT).show();
                break;
            case R.id.std_rd:
                utype = "Students";
                user_mailtag = "Stud_Email";
                rf_name = "Stud_F_Name";
                rl_name = "Stud_L_Name";
                profRd.setEnabled(false);
                stdRd.setEnabled(false);
                Toast.makeText(this, "Students", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()) {
            case R.id.prof_rd:
                profRd.setEnabled(true);
                stdRd.setEnabled(true);
                Toast.makeText(this, "Select Your User Type", Toast.LENGTH_SHORT).show();
                break;
            case R.id.std_rd:
                profRd.setEnabled(true);
                stdRd.setEnabled(true);
                Toast.makeText(this, "Select Your User Type", Toast.LENGTH_SHORT).show();
                break;
        }

        return false;
    }
}
