package com.dbt;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class DashBoard extends AppCompatActivity implements View.OnClickListener {

    protected CircleImageView tImagec;
    protected TextView titleMain;
    protected TextView titleSmall;
    protected Toolbar dashTb;
    protected TextView blankTv;
    protected TextView studAttendTv;
    protected CardView studAttendC;
    protected TextView attendPanTv;
    protected CardView attendPanC;
    protected TextView studThesTv;
    protected CardView studThesC;
    protected LinearLayout contentTask;
    protected RelativeLayout dashboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.dash_board);
        //TODO :get Data From Register Bloc
        initView();

    }

    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.t_imagec) {
            //profile view
        } else if (view.getId() == R.id.title_main) {
            //profile view
        } else if (view.getId() == R.id.title_small) {
            //profile view
        } else if (view.getId() == R.id.stud_attend_c) {
            //goto Attendance page
            startActivity(new Intent(DashBoard.this,TakeAttendance.class));
        } else if (view.getId() == R.id.attend_pan_c) {
            //attendance detail page
        } else if (view.getId() == R.id.stud_thes_c) {
            //student thesis detail page

        }
    }

    private void initView() {
        tImagec = (CircleImageView) findViewById(R.id.t_imagec);
        tImagec.setOnClickListener(DashBoard.this);
        titleMain = (TextView) findViewById(R.id.title_main);
        titleMain.setOnClickListener(DashBoard.this);
        titleSmall = (TextView) findViewById(R.id.title_small);
        titleSmall.setOnClickListener(DashBoard.this);
        dashTb = (Toolbar) findViewById(R.id.dash_tb);
        blankTv = (TextView) findViewById(R.id.blank_tv);
        studAttendTv = (TextView) findViewById(R.id.stud_attend_tv);
        studAttendC = (CardView) findViewById(R.id.stud_attend_c);
        studAttendC.setOnClickListener(DashBoard.this);
        attendPanTv = (TextView) findViewById(R.id.attend_pan_tv);
        attendPanC = (CardView) findViewById(R.id.attend_pan_c);
        attendPanC.setOnClickListener(DashBoard.this);
        studThesTv = (TextView) findViewById(R.id.stud_thes_tv);
        studThesC = (CardView) findViewById(R.id.stud_thes_c);
        studThesC.setOnClickListener(DashBoard.this);
        contentTask = (LinearLayout) findViewById(R.id.content_task);
        dashboard = (RelativeLayout) findViewById(R.id.dashboard);
    }
}
