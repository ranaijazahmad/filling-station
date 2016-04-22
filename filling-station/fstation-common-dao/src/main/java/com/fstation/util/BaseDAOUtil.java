package com.fstation.util;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;
import java.util.UUID;

@Deprecated
public class BaseDAOUtil {

    public static boolean isEmptyString(String str) {
        return str == null || "".equals(str.trim());
    }

    public static <T> boolean determineListIsNull(List<T> list) {
        return determineCollectionIsNull(list);
    }

    public static <T> boolean determineCollectionIsNull(Collection<T> collection) {
        if (collection == null || collection.isEmpty())
            return true;

        boolean nonNull = true;
        for (T t : collection) {
            nonNull = nonNull && t != null;
        }

        return !nonNull;
    }

    public static String generateUUIDKey() {

        String uuid = UUID.randomUUID().toString();
        // remove the dashes
        StringTokenizer st = new StringTokenizer(uuid, "-");
        StringBuilder retUUID = new StringBuilder();
        while (st.hasMoreTokens()) {
            retUUID.append(st.nextToken());
        }
        return retUUID.toString();
    }

    public static boolean isDirty(String existStr, String newStr) {
        // consider empty string same as null, but don't trim in comparisons
        if (existStr == null || existStr.length() == 0)
            return !(newStr == null || newStr.length() == 0);
        else if (newStr == null || newStr.length() == 0)
            return !(existStr == null || existStr.length() == 0);
        else
            return !newStr.equals(existStr);
    }

    public static boolean isDirty(Integer existInt, Integer newInt) {
        // consider null same as 0
        if (existInt == null || existInt == 0)
            return !(newInt == null || newInt == 0);
        else if (newInt == null || newInt == 0)
            return !(existInt == null || existInt == 0);
        else
            return (existInt.compareTo(newInt) != 0);
    }

    public static boolean isDirty(BigDecimal existBD, BigDecimal newBD) {
        // here, null is DIFFERENT from 0
        if (existBD == null)
            return !(newBD == null);
        else if (newBD == null)
            return !(existBD == null);
        else
            return !(newBD.compareTo(existBD) == 0);

    }

    public static boolean isDirty(Date existDate, Date newDate) {
        if (existDate == null)
            return !(newDate == null);
        else if (newDate == null)
            return !(existDate == null);
        else
            return !(newDate.compareTo(existDate) == 0);
    }

    public static boolean convertToBoolean(String convertStr) {
        if (convertStr == null || convertStr.equals("")) {
            return false;
        }
        else if (convertStr.equals("Y") || convertStr.equalsIgnoreCase("True")) {
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean convertToBoolean(int convertInt) {
        if (convertInt == 1) {
            return true;
        }
        else {
            return false;
        }
    }

    public static int convertToInt(boolean convertBool) {
        if (convertBool) {
            return 1;
        }
        else {
            return 0;
        }
    }

    /**
     * Sorts list by natural comparator and puts into comma-sep string.
     */
    public static <T> String commaSepString(Collection<T> list) {
        if (list == null || list.size() == 0)
            return null;
        T[] arr = (T[]) list.toArray();
        Arrays.sort(arr);

        StringBuilder res = new StringBuilder();
        boolean first = true;
        for (T t : arr) {
            if (!first)
                res.append(",");
            else
                first = false;
            res.append(String.valueOf(t));
        }
        return res.toString();
    }

    public static String getInClauseFromAList(Collection<String> ids) {
        StringBuilder inClause = new StringBuilder(" IN  ( ");
        if (ids != null && ids.size() > 0) {
            for (String id : ids) {
                inClause.append("'").append(id).append("',");
            }
            inClause.deleteCharAt(inClause.length() - 1);

        }
        inClause.append(" ) ");
        return inClause.toString();
    }

    public static String getInClauseFromIntList(Collection<Integer> ids) {
        StringBuilder inClause = new StringBuilder(" IN  ( ");
        if (ids != null && ids.size() > 0) {
            for (Integer id : ids) {
                inClause.append(id).append(",");
            }
            inClause.deleteCharAt(inClause.length() - 1);

        }
        inClause.append(" ) ");
        return inClause.toString();
    }

    public static String getMaskedSSN(String ssn) {
        String mSsn = safeTrim(ssn);
        return (mSsn == null || mSsn.length() < 9) ? null
            : "XXX-XX-" + mSsn.substring(mSsn.length() - 4);
    }

    /** String conversion utility. */
    public static String safeTrim(String s) {
        if (s == null)
            return null;
        s = s.trim();
        return "null".equalsIgnoreCase(s) ? null : s;
    }

}
