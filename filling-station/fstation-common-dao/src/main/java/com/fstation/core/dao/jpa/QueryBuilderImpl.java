
package com.fstation.core.dao.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.Query;

import com.fstation.core.dao.KeyMapper;
import com.fstation.core.dao.QueryBuilder;

class QueryBuilderImpl<T> implements QueryBuilder<T> {

    private final Query query;

    QueryBuilderImpl(final Query q) {
        query = q;
    }

    public QueryBuilder<T> setParameter(final String name, final Object value) {
        query.setParameter(name, value);
        return this;
    }

    public QueryBuilder<T> setParameter(final int paramNum, final Object value) {
        query.setParameter(paramNum, value);
        return this;
    }

    @SuppressWarnings("unchecked")
    public T asSingle() {
    	T result = null;
    	List list = query.getResultList();
    	if (list != null && list.size() > 0) {
    		result = (T) list.get(0);
    	}	
        return result;
    }

    @SuppressWarnings("unchecked")
    public List<T> asList() {
        // TODO Make sure this always returns empty list
        return (List<T>) query.getResultList();
    }

    public <K> Map<K, T> asMap(final KeyMapper<K, T> mapper) {
        Map<K, T> map = new HashMap<K, T>();
        for (T t : asList()) {
            map.put(mapper.getKey(t), t);
        }
        return map;
    }

    public <K> Map<K, T> asTreeMap(final KeyMapper<K, T> mapper) {
        Map<K, T> map = new TreeMap<K, T>();
        for (T t : asList()) {
            map.put(mapper.getKey(t), t);
        }
        return map;
    }

}
