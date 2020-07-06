package org.myredis;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Tushar on 7/6/20.
 */
public class MyCache {

    private Map<Object, Element> mapElements = null;

    private String cacheName;

    private long maxTimeToLiveSeconds;

    private long maxTimeIdleSeconds;

    private static final int SCHEDULE_CHECK_TIME = 1; //in seconds

    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            clearExpiredElements();
        }
    };

    Timer timer = new Timer("timer");

    public MyCache(String name,
                        int maxElements,
                        long timeToLiveSeconds,
                        long timeToIdleSeconds
                        ) {
        mapElements = new ConcurrentHashMap<Object, Element>(maxElements);
        this.cacheName = name;
        this.maxTimeToLiveSeconds = timeToLiveSeconds;
        this.maxTimeIdleSeconds = timeToIdleSeconds;

        timer.scheduleAtFixedRate(timerTask, 0, SCHEDULE_CHECK_TIME * 1000);

    }

    public void put(Element element) {
        element.setExpiredTime(new Date().getTime() + (maxTimeToLiveSeconds * 1000));
        mapElements.put(element.getKey().getClass(), element);
    }

    public Element get(Object key) {
        return mapElements.get(key);
    }

    /*
        This function removes elements from cache that are expired
     */
    private void clearExpiredElements() {
        long currentTime = new Date().getTime();

        mapElements.entrySet().removeIf(entry -> (entry.getValue().getExpiredTime() < currentTime));
    }

    public String getCacheName() {
        return cacheName;
    }
}
