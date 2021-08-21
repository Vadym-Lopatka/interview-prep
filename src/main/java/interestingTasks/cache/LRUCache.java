package interestingTasks.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * Least Recently Used (LRU) cache
 * The functions get(K key) and put(K key, V value) must each run in O(1) average time complexity.
 */
public class LRUCache<K, V> implements Cache<K, V> {

    private final int capacity;
    private final DoublyLinkedNode<K, V> head;
    private final DoublyLinkedNode<K, V> tail;
    private final Map<K, DoublyLinkedNode<K, V>> keyToNode;
    private int size;


    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;

        this.keyToNode = new HashMap<>(capacity);

        this.head = new DoublyLinkedNode<K, V>();
        this.tail = new DoublyLinkedNode<K, V>();

        this.head.next = tail;
        this.tail.prev = head;
    }

    @Override
    public V get(K key) {
        DoublyLinkedNode<K, V> result = keyToNode.get(key);
        if (result == null) {
            return null;
        }

        moveToHead(result);
        return result.value;
    }

    @Override
    public void put(K key, V value) {
        DoublyLinkedNode<K, V> node = keyToNode.get(key);

        if (node != null) {
            node.value = value;
            moveToHead(node);
        } else {
            node = new DoublyLinkedNode<K, V>(key, value);
            addNode(node);
        }

        if (size == capacity) {
            DoublyLinkedNode<K, V> tail = popTail();
            keyToNode.remove(tail.key);
            size = size - 1;
        }

        keyToNode.put(key, node);
        size = size + 1;
    }

    private DoublyLinkedNode<K, V> popTail() {
        DoublyLinkedNode<K, V> result = tail.prev;
        removeNode(result);

        return result;
    }

    private void moveToHead(DoublyLinkedNode<K, V> node) {
        removeNode(node);
        addNode(node);
    }

    private void addNode(DoublyLinkedNode<K, V> node) {
        /**
         * Always add the new node right after head.
         */
        node.prev = head;
        node.next = head.next;

        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(DoublyLinkedNode<K, V> node) {
        DoublyLinkedNode<K, V> prev = node.prev;
        DoublyLinkedNode<K, V> next = node.next;

        prev.next = next;
        next.prev = prev;
    }

    @Override
    public int capacity() {
        return this.capacity;
    }

    @Override
    public int size() {
        return keyToNode.size();
    }


    private class DoublyLinkedNode<K, V> {
        private DoublyLinkedNode<K, V> prev;
        private DoublyLinkedNode<K, V> next;
        private K key;
        private V value;

        public DoublyLinkedNode() {

        }

        public DoublyLinkedNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
