package com.dbt.DataModel;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dbt.R;
import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by admin on 3/14/2017.
 */

public class StudAdapter extends RecyclerView.Adapter<StudAdapter.StudViewHolder> {
    String TAG = getClass().getSimpleName();
    Context ct;
    List<Students> studArray;

    public StudAdapter(List<Students> studArray, Context context) {
        this.studArray = studArray;
        this.ct = context;
    }

    @Override
    public StudViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_take_attendance, parent, false);
        return new StudViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final StudViewHolder hld, int position) {
        final Students s = studArray.get(position);
        if (s != null) {
            hld.tv.setText(s.getStudFName() + " " + s.getStudLName());
            s.getStudProfile().getDataInBackground(new GetDataCallback() {
                public void done(byte[] data, ParseException e) {
                    if (e == null) {
                        // data has the bytes for the resume
                        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                        hld.iv.setImageBitmap(bitmap);
                    } else {
                        // something went wrong
                    }
                }
            });
        }
        Log.i(TAG, "onBindViewHolder: Data Bound  " + s.getStudFName() + "  " + s.getStudEmail());

        hld.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ct, "TextViw from viewholder is clicked " + hld.tv.getText(), Toast.LENGTH_SHORT).show();
            }
        });
        hld.markgrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.Absent_rb:
                        //add mark attendance function for clicked student view block and pass absent as parameter
                        hld.a.setClickable(false);
                        hld.p.setClickable(false);
                        markAttendance("A", s.getObjectId());
                        Toast.makeText(ct, "Marked Absent", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.Present_rb:
                        //add mark attendance function for clicked student view block and pass absent as parameter
                        hld.a.setClickable(false);
                        hld.p.setClickable(false);
                        markAttendance("P", s.getObjectId());
                        Toast.makeText(ct, "Marked Present", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        hld.markgrp.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                hld.a.setClickable(true);
                hld.p.setClickable(true);
                Toast.makeText(ct, "Edit Attendance", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    private void markAttendance(final String attendStatus, String selectedStudent) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat month_date = new SimpleDateFormat("MMMM");
        final String ThisMonth = month_date.format(cal.getTime());
        ParseQuery<Students> query = ParseQuery.getQuery(Students.class);
        query.getInBackground(selectedStudent, new GetCallback<Students>() {
            public void done(Students stud, ParseException e) {
                if (e == null) {
                    ParseObject st = stud.getStudAttendance().getParseObject(ThisMonth);
                    if (st != null) {
                        st.put(ThisMonth + 1, attendStatus);
                        st.saveInBackground();
                    }
                } else {

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return studArray.size();
    }

    public class StudViewHolder extends RecyclerView.ViewHolder {
        public TextView tv;
        public ImageView iv;
        public RadioButton p, a;
        public RadioGroup markgrp;

        public StudViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.c_name);
            iv = (ImageView) itemView.findViewById(R.id.c_iv);
            p = (RadioButton) itemView.findViewById(R.id.Present_rb);
            a = (RadioButton) itemView.findViewById(R.id.Absent_rb);
            markgrp = (RadioGroup) itemView.findViewById(R.id.Attendance_rb_grp);
        }

    }
}
