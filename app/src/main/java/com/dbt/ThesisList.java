package com.dbt;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.dbt.DataModel.Students;
import com.dbt.DataModel.ThesisAdapter;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class ThesisList extends AppCompatActivity {
    ParseQuery<Students> pqr;
    List<Students> mlist;
    ThesisAdapter detailStudlist;
    private String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thesis_list);

        RecyclerView stud_detail_recycle = (RecyclerView) findViewById(R.id.thesis_list_recycler);
        mlist = new ArrayList<Students>();

        detailStudlist = new ThesisAdapter(mlist, this);

        stud_detail_recycle.setLayoutManager(new LinearLayoutManager(this));
        stud_detail_recycle.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL));
        stud_detail_recycle.setAdapter(detailStudlist);
        prepareDataList();
    }

    private void prepareDataList() {
        pqr = new ParseQuery<Students>(Students.class);
        pqr.findInBackground(new FindCallback<Students>() {
            @Override
            public void done(List<Students> stdl, ParseException e) {
                if (e == null) {
                    for (Students i : stdl) {
                        mlist.add(i);
                        detailStudlist.notifyDataSetChanged();
                        Log.i(TAG, "done: Data Loaded  " + i.getStudFName() + "  " + i.getStudEmail());
                    }
                } else {
                    Toast.makeText(ThesisList.this, "Data Not Present", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
    }

}