package com.fizhu.androidchart;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ContohBarChart2Activity extends AppCompatActivity {

    private HorizontalBarChart horizontalBarChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contoh_bar_chart2);

        horizontalBarChart = findViewById(R.id.chart_bar2);

        horizontalBarChart.setDrawGridBackground(false);
        horizontalBarChart.getDescription().setEnabled(false);

        // scaling can now only be done on x- and y-axis separately
        horizontalBarChart.setPinchZoom(false);

        horizontalBarChart.setDrawBarShadow(false);
        horizontalBarChart.setDrawValueAboveBar(true);
        horizontalBarChart.setHighlightFullBarEnabled(false);

        horizontalBarChart.getAxisLeft().setEnabled(false);
        horizontalBarChart.getAxisRight().setAxisMaximum(25f);
        horizontalBarChart.getAxisRight().setAxisMinimum(-25f);
        horizontalBarChart.getAxisRight().setDrawGridLines(false);
        horizontalBarChart.getAxisRight().setDrawZeroLine(true);
        horizontalBarChart.getAxisRight().setLabelCount(7, false);
        horizontalBarChart.getAxisRight().setValueFormatter(new CustomFormatter());
        horizontalBarChart.getAxisRight().setTextSize(9f);

        XAxis xAxis = horizontalBarChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setTextSize(9f);
        xAxis.setAxisMinimum(0f);
        xAxis.setAxisMaximum(110f);
        xAxis.setCenterAxisLabels(true);
        xAxis.setLabelCount(12);
        xAxis.setGranularity(10f);
        xAxis.setValueFormatter(new ValueFormatter() {

            private final DecimalFormat format = new DecimalFormat("###");

            @Override
            public String getFormattedValue(float value) {
                return format.format(value) + "-" + format.format(value + 10);
            }
        });

        Legend l = horizontalBarChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setFormSize(8f);
        l.setFormToTextSpace(4f);
        l.setXEntrySpace(6f);

        // IMPORTANT: When using negative values in stacked bars, always make sure the negative values are in the array first
        ArrayList<BarEntry> values = new ArrayList<>();
        values.add(new BarEntry(5, new float[]{ -10, 10 }));
        values.add(new BarEntry(15, new float[]{ -12, 13 }));
        values.add(new BarEntry(25, new float[]{ -15, 15 }));
        values.add(new BarEntry(35, new float[]{ -17, 17 }));
        values.add(new BarEntry(45, new float[]{ -19, 20 }));
        values.add(new BarEntry(45, new float[]{ -19, 20 }));
        values.add(new BarEntry(55, new float[]{ -19, 19 }));
        values.add(new BarEntry(65, new float[]{ -16, 16 }));
        values.add(new BarEntry(75, new float[]{ -13, 14 }));
        values.add(new BarEntry(85, new float[]{ -10, 11 }));
        values.add(new BarEntry(95, new float[]{ -5, 6 }));
        values.add(new BarEntry(105, new float[]{ -1, 2 }));

        BarDataSet set = new BarDataSet(values, "Judul Data nya");
        set.setDrawIcons(false);
        set.setValueFormatter(new CustomFormatter());
        set.setValueTextSize(7f);
        set.setAxisDependency(YAxis.AxisDependency.RIGHT);
        set.setColors(Color.rgb(67,67,72), Color.rgb(124,181,236));
        set.setStackLabels(new String[]{
                "Label Satu", "Label Dua"
        });

        BarData data = new BarData(set);
        data.setBarWidth(8.5f);
        horizontalBarChart.setData(data);
        horizontalBarChart.invalidate();
    }

    private class CustomFormatter extends ValueFormatter {

        private final DecimalFormat mFormat;

        CustomFormatter() {
            mFormat = new DecimalFormat("###");
        }

        @Override
        public String getFormattedValue(float value) {
            return mFormat.format(Math.abs(value)) + "m";
        }
    }
}
