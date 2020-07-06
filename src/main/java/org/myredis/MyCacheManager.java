package org.myredis;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Tushar on 7/6/20.
 */
public class MyCacheManager {

    private static MyCacheManager myCacheManager = null;

    Map<String, org.myredis.MyCache> myCacheMap = new ConcurrentHashMap<String, MyCache>();

    private MyCacheManager() {}

    public static MyCacheManager create() {
        if (myCacheManager == null) {
            myCacheManager = new MyCacheManager();
        }

        return myCacheManager;
    }

    public void addCache(String key, MyCache cache) {
        myCacheMap.put(key, cache);
    }

    public MyCache getCache(String key) {
        return myCacheMap.get(key);
    }
}
