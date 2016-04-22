
package com.fstation.core.dao;

public interface KeyMapper<K, T> {

    K getKey(T t);
}
