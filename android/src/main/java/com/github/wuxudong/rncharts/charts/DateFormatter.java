package com.github.wuxudong.rncharts.charts;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class DateFormatter implements IAxisValueFormatter {

	private final SimpleDateFormat mDayFormat = new SimpleDateFormat("dd");
	private final SimpleDateFormat mMonthFormat = new SimpleDateFormat("dd MMM");
	private long previousDate = 0;

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        String dateFormat;
		if(value == axis.getAxisMinimum()) {
			dateFormat = "";
			previousDate = 0;
		} else {
			if (previousDate == 0 || !isSameMonth(previousDate, (long)value)) {
				dateFormat = mMonthFormat.format((long) value);
			} else {
				dateFormat = mDayFormat.format((long) value);
			}
		}

		if(value != axis.getAxisMinimum()) {
			previousDate = (long) value;
		}
		return dateFormat;
    }
	
	private boolean isSameMonth(long previousDate, long value) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTimeInMillis(previousDate);
        cal2.setTimeInMillis(value);
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
    }
}
