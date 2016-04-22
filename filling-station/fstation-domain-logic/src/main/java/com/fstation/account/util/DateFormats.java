package com.fstation.account.util;

public final class DateFormats {
    private DateFormats() {

    }

    public static final String APPLICATION_DATE_FORMAT = "MM/dd/yyyy";
    public static final String APPPLICATION_FULL_DATE_FORMAT = "dd/MM/yyyy HH:mm:ss SSS";
    /*
     * Time will be in AM and PM
     */
    public static final String APPPLICATION_DATE_TIME_FORMAT = "MM/dd/yyyy h:m a";
    /*
     * Time will be in 24 hours
     */
    public static final String APPPLICATION_24_DATE_TIME_FORMAT = "MM/dd/yyyy HH:mm";

    /*
     * Time will be in 24 hours without colon
     */
    public static final String APPPLICATION_24_DATE_TIME_FORMAT_WITHOUT_COLON = "MM/dd/yyyy HHmm";

    public static final String SP_QUERY_RETURN_DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";

    public static final String CCR_DATE_FORMAT = "yyyy-MM-dd";

    /*
     * Time will be in AM and PM
     */
    public static final String APPPLICATION_DATE_TIME_FULL_FORMAT = "MM/dd/yyyy hh:mm a";

}
