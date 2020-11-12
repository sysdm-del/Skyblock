package me.sysdm.net.CustomCollections;

import org.apache.commons.lang.ArrayUtils;

import java.io.Serializable;
import java.util.*;

public class SkyblockList<T> extends AbstractList<T> implements Serializable {

    private T[] a = (T[]) new Object[0];

    @Override
    public boolean add(Object t) {
        a = Arrays.copyOf(a, a.length + 1);
        a[a.length - 1] = (T) t;
        return true;
    }

    @Override
    public boolean remove(Object t) {
        for(T type : a) {
            if(type == t) {
                int index = ArrayUtils.indexOf(a, t);
                ArrayUtils.remove(a, index);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(Object t) {
        for(T type : a) {
            if(type == t) {
                return true;
            }
        }
        return false;
    }

    @Override
    public T get(int index) {
        return a[index];
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        a = Arrays.copyOf(a, c.size() + a.length);
        for(T type : c) {
            for(int i = a.length - c.size(); i <= a.length; i++) {
                a[i] = type;
            }
        }
        return true;
    }

    @Override
    public int size() {
        return a.length;
    }
}
