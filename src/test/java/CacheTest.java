import org.junit.Test;
import org.junit.Assert;
import org.myredis.*;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Tushar on 7/6/20.
 */
public class CacheTest {
    @Test
    public void testCache() {
        MyCacheManager manager = MyCacheManager.create();
        Assert.assertEquals(null, manager.getCache("Nothing"));

        MyCache cache = new MyCache("testCache", 100, 10L, 10L);
        MyCache cache2 = new MyCache("cache2", 200, 20L, 20L);

        Element element = new Element("store", "My Element Store");
        Element element1 = new Element("list", new int[] {1, 2, 3, 4, 5, 6});

        cache.put(element);
        cache.put(element1);


        manager.addCache("testCache", cache);
        manager.addCache(cache2.getCacheName(), cache2);

        MyCache testCache = manager.getCache("testCache");
        Assert.assertEquals("testCache", testCache.getCacheName());

        Element arrElement = testCache.get("list");
        Assert.assertArrayEquals(new int[] {1, 2, 3, 4, 5, 6}, (int[])arrElement.getValue());

        try {
            Thread.sleep(12000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(null, testCache.get("store")); //expired
    }
}
