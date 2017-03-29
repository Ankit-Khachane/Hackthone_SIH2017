package com.dbt.DataModel;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dbt.R;
import com.parse.GetDataCallback;
import com.parse.ParseException;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by khach on 28-03-2017.
 */

public class ThesisAdapter extends RecyclerView.Adapter<ThesisAdapter.ThesisViwHolder> {
    List<Students> studlist;
    Context ctx;
    private String TAG = getClass().getSimpleName();

    public ThesisAdapter(List<Students> studlist, Context ctx) {
        this.studlist = studlist;
        this.ctx = ctx;
    }

    @Override
    public ThesisViwHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_thesis_detail, parent, false);
        return new ThesisViwHolder(view);
    }

    @Override
    public void onBindViewHolder(final ThesisViwHolder h, int position) {
        Students s = studlist.get(position);
        if (s != null) {
//            ParseFile p= new ParseFile()
            h.studtv.setText(s.getStudFName() + " " + s.getStudLName());
            s.getStudProfile().getDataInBackground(new GetDataCallback() {
                public void done(byte[] data, ParseException e) {
                    if (e == null) {
                        // data has the bytes for the resume
                        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                        h.tcv.setImageBitmap(bitmap);
                    } else {
                        // something went wrong
                    }
                }
            });
            Log.i(TAG, "onBindViewHolder: data retrived " + s.getStudFName() + " " + s.getStudLName());
        }
        Log.i(TAG, "onBindViewHolder: Data Thesis Activity");
    }

    @Override
    public int getItemCount() {
        return studlist.size();
    }

    public class ThesisViwHolder extends RecyclerView.ViewHolder {
        CircleImageView tcv;
        TextView studtv, thesis;

        public ThesisViwHolder(View itemView) {
            super(itemView);
            studtv = (TextView) itemView.findViewById(R.id.thesis_stud_name);
            thesis = (TextView) itemView.findViewById(R.id.thesis_stud_thesis);
            tcv = (CircleImageView) itemView.findViewById(R.id.thesi_prof_cv);

        }
    }
}
