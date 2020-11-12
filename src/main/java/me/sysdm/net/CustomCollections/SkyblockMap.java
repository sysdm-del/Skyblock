package me.sysdm.net.CustomCollections;

import java.io.Serializable;
import java.util.*;

public class SkyblockMap<K, V> extends AbstractMap<K, V> implements Cloneable, Serializable {

    static class Entry implements Map.Entry {
        protected Object key, value;

        public Entry(Object key, Object value) {
            this.key = key;
            this.value = value;
        }

        public Object getKey() {
            return key;
        }

        public Object getValue() {
            return value;
        }

        public Object setValue(Object newValue) {
            Object oldValue = value;
            value = newValue;
            return oldValue;
        }

        public boolean equals(Object o) {
            if (!(o instanceof Map.Entry)) {
                return false;
            }
            Map.Entry e = (Map.Entry) o;
            return (key == null ? e.getKey() == null : key.equals(e.getKey()))
                    && (value == null ? e.getValue() == null : value.equals(e
                    .getValue()));
        }

        public int hashCode() {
            int keyHash = (key == null ? 0 : key.hashCode());
            int valueHash = (value == null ? 0 : value.hashCode());
            return keyHash ^ valueHash;
        }

        public String toString() {
            return key + "=" + value;
        }
    }

    private Set<Map.Entry<K, V>> entries = null;

    private ArrayList list;

    public SkyblockMap() {
        list = new ArrayList();
    }

    public SkyblockMap(Map map) {
        list = new ArrayList();
        putAll(map);
    }

    public SkyblockMap(int initialCapacity) {
        list = new ArrayList(initialCapacity);
    }

    public Set<Map.Entry<K, V>> entrySet() {
        if (entries == null) {
            entries = new AbstractSet<Map.Entry<K, V>>() {
                public void clear() {
                    list.clear();
                }

                public Iterator iterator() {
                    return list.iterator();
                }

                public int size() {
                    return list.size();
                }
            };
        }
        return entries;
    }

    public Object put(Object key, Object value) {
        int size = list.size();
        Entry entry = null;
        int i;
        if (key == null) {
            for (i = 0; i < size; i++) {
                entry = (Entry) (list.get(i));
                if (entry.getKey() == null) {
                    break;
                }
            }
        } else {
            for (i = 0; i < size; i++) {
                entry = (Entry) (list.get(i));
                if (key.equals(entry.getKey())) {
                    break;
                }
            }
        }
        Object oldValue = null;
        if (i < size) {
            oldValue = entry.getValue();
            entry.setValue(value);
        } else {
            list.add(new Entry(key, value));
        }
        return oldValue;
    }

    public Object clone() {
        return new SkyblockMap<K, V>(this);
    }

}
