package com.dbt.Application;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dbt.R;
import com.dbt.Application.Student;
import com.parse.GetDataCallback;
import com.parse.ParseException;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by admin on 3/14/2017.
 */

public class StudAdapter extends RecyclerView.Adapter<StudAdapter.StudViewHolder> {
    String TAG = getClass().getSimpleName();
    List<Student> studArray;

    public StudAdapter(List<Student> studArray) {
        this.studArray = studArray;
    }

    @Override
    public StudViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_1, parent, false);
        return new StudViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final StudViewHolder hld, int position) {
        Student s = studArray.get(position);
        hld.tv.setText(s.getStudName());
        s.getStudProfile().getDataInBackground(new GetDataCallback() {
            public void done(byte[] data, ParseException e) {
                if (e == null) {
                    // data has the bytes for the resume
                    Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
//                    hld.cv.setImageBitmap(bitmap);
                    hld.iv.setImageBitmap(bitmap);
                } else {
                    // something went wrong
                }
            }
        });
        Log.i(TAG, "onBindViewHolder: Data Bound  " + s.getStudName() + "  " + s.getStudEmail());
    }

    @Override
    public int getItemCount() {
        return studArray.size();
    }

    public class StudViewHolder extends RecyclerView.ViewHolder {
        public TextView tv;
        public CircleImageView cv;
        public ImageView iv;

        public StudViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.c_name);
            iv = (ImageView) itemView.findViewById(R.id.c_iv);
//            cv = (CircleImageView) itemView.findViewById(R.id.c_img);
        }
    }
}
