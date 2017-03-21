package com.dbt;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Registration extends AppCompatActivity implements View.OnClickListener {

    protected Button regBtn;
    protected EditText emailEd;
    protected EditText userEd;
    protected EditText passEd;
    protected TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.registration);
        initView();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.reg_btn) {
            Toast.makeText(this, "Registering User", Toast.LENGTH_SHORT).show();
        }
    }

    private void initView() {
        emailEd = (EditText) findViewById(R.id.email_ed);
        userEd = (EditText) findViewById(R.id.user_ed);
        passEd = (EditText) findViewById(R.id.pass_ed);
        regBtn = (Button) findViewById(R.id.reg_btn);
        regBtn.setOnClickListener(Registration.this);
        tv = (TextView) findViewById(R.id.tv);
//        TODO : Add Progress dialog at the registration process
        tv.setPaintFlags(tv.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }
}
