package com.dbt;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Registration extends AppCompatActivity implements View.OnClickListener {

    protected Button regBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.registration);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        RegisterPageOne regone = new RegisterPageOne();
        ft.add(R.id.Frag_container, regone);
        ft.commit();
        initView();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.reg_btn) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            RegisterPageTwo regone = new RegisterPageTwo();
            ft.replace(R.id.Frag_container, regone);
            ft.commit();
        }
    }

    private void initView() {
        regBtn = (Button) findViewById(R.id.reg_btn);
        regBtn.setOnClickListener(Registration.this);
    }
}
