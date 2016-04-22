package com.fstation.account.util;

import java.util.Comparator;

public abstract class AbstractComparatorFactory<T> implements Comparator<T> {
    private Boolean sortOrder = Boolean.FALSE;

    public AbstractComparatorFactory(final String order) {
        if (order.toLowerCase().startsWith("asc")) {
            sortOrder = true; // ascending sort
        } else if (order.toLowerCase().startsWith("des")) {
            sortOrder = false; // descending sort
        }
    }

    public final Boolean getSortOrder() {
        return sortOrder;
    }

    public final void setSortOrder(final Boolean sortOrder) {
        this.sortOrder = sortOrder;
    }
}
