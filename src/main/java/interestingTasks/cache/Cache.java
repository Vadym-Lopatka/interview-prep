package interestingTasks.cache;

public interface Cache<K, V> {

    V get(K key);

    void put(K key, V value);

    int capacity();

    int size();
}
