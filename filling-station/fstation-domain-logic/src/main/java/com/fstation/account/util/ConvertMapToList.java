package com.fstation.account.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ConvertMapToList<K, T> {

    public ConvertMapToList() { }

    public final List<T> convert(final Map<K, T> mapToConvert) {
        List<T> convertedList = new ArrayList<T>();
        for (Map.Entry<K, T> entry : mapToConvert.entrySet()) {
            convertedList.add(entry.getValue());
        }
        return convertedList;
    }
}
