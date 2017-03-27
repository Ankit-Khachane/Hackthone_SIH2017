package com.dbt;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.dbt.Application.StudAdapter;
import com.dbt.Application.Student;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class TakeAttendance extends AppCompatActivity {
    ParseQuery<Student> pqr;
    List<Student> mlist;
    StudAdapter stda;

    protected RecyclerView recyclerView;
    private String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.take_attendance);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mlist = new ArrayList<Student>();

        stda = new StudAdapter(mlist);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL));
        recyclerView.setAdapter(stda);
        prepareDataList();
    }

    private void prepareDataList() {
        pqr = new ParseQuery<Student>(Student.class);
        pqr.findInBackground(new FindCallback<Student>() {
            @Override
            public void done(List<Student> stdl, ParseException e) {
                if (e==null) {
                    for (Student i : stdl) {
                        mlist.add(i);
                        stda.notifyDataSetChanged();
                        Log.i(TAG, "done: Data Loaded  " + i.getStudName() + "  " + i.getStudEmail());
                    }
                } else {
                    Toast.makeText(TakeAttendance.this, "Data Not Present", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
    }

}
