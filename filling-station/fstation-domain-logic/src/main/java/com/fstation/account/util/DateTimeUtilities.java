package com.fstation.account.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class DateTimeUtilities {

    private DateTimeUtilities() {

    }

    public static String getCurrentDate(final String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(new Date());
    }

    public static String getFormattedDate(final java.util.Date date, final String formatString) {
        String val = null;
        if (date != null) {
            SimpleDateFormat simpleDateTimeFormat = new SimpleDateFormat(formatString);
            val = simpleDateTimeFormat.format(date);
        }
        return val;
    }

    public static java.util.Date getParsedDate(final String dateString, final String formatString)
        throws ParseException {
        Date val = null;
        if (dateString != null && !"".equals(dateString)) {
            SimpleDateFormat simpleDateTimeFormat = new SimpleDateFormat(formatString);
            val = simpleDateTimeFormat.parse(dateString);
        }

        return val;
    }

    public static Date dateTotDate(final Date date, final String formatString) throws ParseException {
        Date parsedDate = null;

        SimpleDateFormat simpleDateTimeFormat = new SimpleDateFormat(formatString);
        String formatedDateString = simpleDateTimeFormat.format(date);
        parsedDate = simpleDateTimeFormat.parse(formatedDateString);

        return parsedDate;
    }

    public static Date getNextDate(final int days, final Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, +days);

        return calendar.getTime();
    }

    public static String dateToString(final Date date, final String format) {
        String formatedDate = null;
        if (date != null) {
            DateFormat dateFormat = new SimpleDateFormat(format);
            formatedDate = dateFormat.format(date);
        }

        return formatedDate;

    }

    public static Date getNextMonth(final int months, final Date createdDate) {

        Calendar nextMonthDate = Calendar.getInstance();
        nextMonthDate.setTime(createdDate);

        nextMonthDate.add(Calendar.MONTH, months);

        return nextMonthDate.getTime();
    }

    // CHECKSTYLE:OFF
    public static boolean isHoliday(final Date currentDate) {
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        if ((Calendar.SATURDAY == c.get(Calendar.DAY_OF_WEEK)) || (Calendar.SUNDAY == c.get(Calendar.DAY_OF_WEEK))) {
            return true;
        } else {
            return false;
        }
    }

    public static Date getLastDateOfMonth(final Date date) {

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        return c.getTime();
    }

    public static Date getFifteenthDateOfMonth(int increment, final Date date) {

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
        c.set(Calendar.DAY_OF_MONTH, increment);
        return c.getTime();
    }

    public static Date getNextYear(final int years, final Date createdDate) {

        Calendar nextNextDate = Calendar.getInstance();
        nextNextDate.setTime(createdDate);

        nextNextDate.add(Calendar.YEAR, years);

        return nextNextDate.getTime();
    }

    public static boolean compareDates(final Date date1, final Date date2) {
        boolean equal = false;
        if (removeTime(date1).equals(removeTime(date2))) {
            equal = true;
        }

        return equal;
    }

    public static Date removeTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

}
