package com.dbt;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class RegisterPageOne extends Fragment {

    protected EditText regFoneUserEd;
    protected EditText regFoneEmailEd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register_page_one, container, false);

        regFoneUserEd = (EditText) view.findViewById(R.id.reg_fone_user_ed);
        regFoneEmailEd = (EditText) view.findViewById(R.id.reg_fone_email_ed);

        return view;
    }
}
