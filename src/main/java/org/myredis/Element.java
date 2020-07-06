package org.myredis;

import java.util.Date;

/**
 * Created by Tushar on 7/6/20.
 */
public class Element {
    private Object Key;

    private Object Value;

    private long expiredTime;

    public Element(Object key, Object value) {
        this.setKey(key);
        this.setValue(value);
    }

    public Object getKey() {
        return Key;
    }

    public void setKey(Object key) {
        Key = key;
    }

    public Object getValue() {
        return Value;
    }

    public void setValue(Object value) {
        Value = value;
    }

    public long getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(long expiredTime) {
        this.expiredTime = expiredTime;
    }
}
