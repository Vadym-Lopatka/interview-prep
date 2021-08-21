package interestingTasks.cache;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LRUCacheTest {

    @Test
    public void shouldInitiateCacheWithSpecificCapacity() {
        // given
        int capacity = 9;

        // when
        LRUCache<String, String> cache = new LRUCache<>(capacity);

        // then
        assertEquals(capacity, cache.capacity());
    }

    @Test
    public void shouldInitiateCacheWithEmptySize() {
        // given
        int capacity = 9;

        // when
        LRUCache<String, String> cache = new LRUCache<>(capacity);

        // then
        assertEquals(0, cache.size());
    }


    @Test
    public void shouldPutToCache() {
        // given
        LRUCache<Integer, Integer> cache = new LRUCache<>(5);
        int key = 123;
        int value = 123;

        // when
        cache.put(key, value);

        // then
        assertEquals(1, cache.size());
    }

    @Test
    public void shouldGetFromCache() {
        // given
        LRUCache<Integer, Integer> cache = new LRUCache<>(5);
        int key = 1;
        int value = 123;
        cache.put(key, value);

        // when
        Integer result = cache.get(key);

        // then
        assertEquals(value, result.intValue());
    }

    @Test
    public void shouldNotOverflowCapacityWhenPutOnFullCapacity() {
        // given
        LRUCache<Integer, Integer> cache = new LRUCache<>(3);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);

        int sizeBefore = cache.size();

        // when
        cache.put(4, 4);

        // then
        int sizeAfter = cache.size();
        assertEquals(sizeBefore, sizeAfter);
    }

    @Test
    public void shouldRemoveTheLastRecentUsedElementWhenPutOnFullCapacity() {
        // given
        LRUCache<Integer, Integer> cache = new LRUCache<>(3);
        cache.put(1, 1); // will be Least Recently Used element
        cache.put(2, 2);
        cache.put(3, 3);

        // when
        cache.put(4, 4);

        // then
        assertNull(cache.get(1));
        assertNotNull(cache.get(4));
    }

}