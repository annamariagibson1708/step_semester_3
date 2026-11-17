import java.util.HashMap;
import java.util.Map;

public class RecentCache {

    // Doubly Linked List Node
    private static class Node {
        int key;
        String page;
        Node prev;
        Node next;

        Node(int key, String page) {
            this.key = key;
            this.page = page;
        }
    }

    private final int capacity;
    private final Map<Integer, Node> cache;
    private final Node head; // Dummy head (most recent side)
    private final Node tail; // Dummy tail (least recent side)

    public RecentCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.head = new Node(-1, null);
        this.tail = new Node(-1, null);
        head.next = tail;
        tail.prev = head;
    }

    // Retrieves the page for the given key in O(1) time and marks it as most recently used
    public String get(int key) {
        Node node = cache.get(key);
        if (node == null) {
            System.out.println("get(" + key + ") -> -1");
            return "-1";
        }
        moveToFront(node);
        System.out.println("get(" + key + ") -> " + node.page);
        return node.page;
    }

    // Inserts or updates the key-page pair in O(1) time, evicting the LRU entry if full
    public void put(int key, String page) {
        Node existing = cache.get(key);
        if (existing != null) {
            existing.page = page;
            moveToFront(existing);
            System.out.println("put(" + key + "," + page + ") updated");
            return;
        }

        if (cache.size() == capacity) {
            Node lru = tail.prev; // Node just before dummy tail is least recently used
            removeNode(lru);
            cache.remove(lru.key);

            Node newNode = new Node(key, page);
            addToFront(newNode);
            cache.put(key, newNode);
            System.out.println("put(" + key + "," + page + ") evicted " + lru.key);
        } else {
            Node newNode = new Node(key, page);
            addToFront(newNode);
            cache.put(key, newNode);
            System.out.println("put(" + key + "," + page + ")");
        }
    }

    // Detaches a node from its current position in the doubly linked list in O(1)
    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // Inserts a node right after the dummy head (most recent position) in O(1)
    private void addToFront(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    // Moves an existing node to the most recent position in O(1)
    private void moveToFront(Node node) {
        removeNode(node);
        addToFront(node);
    }

    // Prints all cached entries from most recent to least recent
    public void printCache() {
        StringBuilder sb = new StringBuilder("Cache (most recent first): ");
        Node curr = head.next;
        while (curr != tail) {
            sb.append(curr.key).append("=").append(curr.page);
            if (curr.next != tail) {
                sb.append(", ");
            }
            curr = curr.next;
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        RecentCache cache = new RecentCache(2);

        cache.put(1, "Home");
        cache.put(2, "Notes");
        cache.get(1);
        cache.put(3, "Quiz");
        cache.get(2);
        cache.put(1, "Home2");
        cache.put(4, "Mail");
        cache.get(3);
        cache.get(1);
        cache.printCache();
    }
}