package com.fstation.util;

import java.util.Calendar;
import java.util.Date;

@Deprecated
public class BaseDateUtil {

    /**
     * Return a calendar set to a specific time on the given date (in local
     * timezone).
     */
    public static Calendar createCalendar(Date d, int hours, int minute, int seconds, int milli) {
        Calendar cal = Calendar.getInstance();
        if (d != null) {
            cal.setTime(d);
        }
        if (hours >= 0) {
            cal.set(Calendar.HOUR_OF_DAY, hours);
        }
        if (minute >= 0) {
            cal.set(Calendar.MINUTE, minute);
        }

        if (seconds >= 0) {
            cal.set(Calendar.SECOND, seconds);
        }
        if (milli >= 0) {
            cal.set(Calendar.MILLISECOND, milli);
        }
        return cal;
    }

}
