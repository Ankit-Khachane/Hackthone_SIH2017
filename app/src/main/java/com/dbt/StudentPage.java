package com.dbt;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.dbt.UI.PieChartView;

import java.util.ArrayList;
import java.util.List;

public class StudentPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_page);
        PieChartView pieChartView = (PieChartView) findViewById(R.id.pie_chart);

        List<PieChartView.PieceDataHolder> pieceDataHolders = new ArrayList<>();

        pieceDataHolders.add(new PieChartView.PieceDataHolder(75, 0xFF77CCAA, "Jan"));
        pieceDataHolders.add(new PieChartView.PieceDataHolder(99, 0xFF11AA33, "Feb"));
        pieceDataHolders.add(new PieChartView.PieceDataHolder(85, Color.GRAY, "Mar"));
        pieceDataHolders.add(new PieChartView.PieceDataHolder(60, Color.YELLOW, "Apr"));
        pieceDataHolders.add(new PieChartView.PieceDataHolder(50, Color.RED, "May"));
        pieceDataHolders.add(new PieChartView.PieceDataHolder(77, Color.BLUE, "Jun"));

        pieChartView.setData(pieceDataHolders);
    }
}
