package com.dbt;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class RegisterPageTwo extends Fragment {

    protected EditText regFtwoPassEd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register_page_two, container, false);
        regFtwoPassEd = (EditText) view.findViewById(R.id.reg_ftwo_pass_ed);
        return view;
    }
}
