package com.dbt.DataModel;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dbt.R;
import com.dbt.StudentPage;
import com.parse.GetDataCallback;
import com.parse.ParseException;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by khach on 28-03-2017.
 */

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.DetailViewHolder> {
    List<Students> studlist;
    Context ctx;
    private String TAG = getClass().getSimpleName();

    public DetailAdapter(List<Students> studlist, Context ctx) {
        this.studlist = studlist;
        this.ctx = ctx;
    }

    @Override
    public DetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_detail_attendance, parent, false);
        return new DetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DetailViewHolder h, int position) {
        Students s = studlist.get(position);
        //add data binding logic code
        if (s != null) {
            h.studname.setText(s.getStudFName() + " " + s.getStudLName());
//            ParseFile p= new ParseFile()
            s.getStudProfile().getDataInBackground(new GetDataCallback() {
                public void done(byte[] data, ParseException e) {
                    if (e == null) {
                        // data has the bytes for the resume
                        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                        h.cv.setImageBitmap(bitmap);
                    } else {
                        // something went wrong
                    }
                }
            });
            Log.i(TAG, "onBindViewHolder: data retrived " + s.getStudFName() + " " + s.getStudLName());
        }


    }

    @Override
    public int getItemCount() {
        return studlist.size();
    }

    public class DetailViewHolder extends RecyclerView.ViewHolder {
        TextView studname, detail, percent;
        CircleImageView cv;

        public DetailViewHolder(View itemView) {
            super(itemView);
            studname = (TextView) itemView.findViewById(R.id.stud_name_tvmain);
            detail = (TextView) itemView.findViewById(R.id.details);
            percent = (TextView) itemView.findViewById(R.id.percent_details);
            cv = (CircleImageView) itemView.findViewById(R.id.profile_view);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ctx.startActivity(new Intent(ctx, StudentPage.class));
                }
            });

        }
    }
}
