public class BusBay {
    private int[] ring;
    private int front;
    private int size;
    private int capacity;

    // Initializes the bus bay ring buffer with a fixed capacity
    public BusBay(int capacity) {
        this.capacity = capacity;
        this.ring = new int[capacity];
        this.front = 0;
        this.size = 0;
    }

    // Inserts a bus at the front of the bay in O(1) time
    public boolean insertFront(int bus) {
        if (isFull()) {
            return false;
        }
        front = (front - 1 + capacity) % capacity;
        ring[front] = bus;
        size++;
        return true;
    }

    // Inserts a bus at the rear of the bay in O(1) time
    public boolean insertLast(int bus) {
        if (isFull()) {
            return false;
        }
        int rear = (front + size) % capacity;
        ring[rear] = bus;
        size++;
        return true;
    }

    // Removes a bus from the front of the bay in O(1) time
    public boolean deleteFront() {
        if (isEmpty()) {
            return false;
        }
        front = (front + 1) % capacity;
        size--;
        return true;
    }

    // Removes a bus from the rear of the bay in O(1) time
    public boolean deleteLast() {
        if (isEmpty()) {
            return false;
        }
        size--;
        return true;
    }

    // Returns the bus at the front without removing it, or -1 if empty
    public int getFront() {
        if (isEmpty()) {
            return -1;
        }
        return ring[front];
    }

    // Returns the bus at the rear without removing it, or -1 if empty
    public int getRear() {
        if (isEmpty()) {
            return -1;
        }
        int rear = (front + size - 1) % capacity;
        return ring[rear];
    }

    // Checks whether the bay is empty in O(1) time
    public boolean isEmpty() {
        return size == 0;
    }

    // Checks whether the bay is full in O(1) time
    public boolean isFull() {
        return size == capacity;
    }

    public static void main(String[] args) {
        BusBay b = new BusBay(3);

        System.out.println(b.insertLast(101));   // true
        System.out.println(b.insertLast(102));   // true
        System.out.println(b.insertFront(100));  // true
        System.out.println(b.insertFront(99));   // false (bay is full)
        System.out.println(b.getRear());         // 102
        System.out.println(b.isFull());          // true
        System.out.println(b.deleteLast());      // true
        System.out.println(b.insertFront(98));   // true
        System.out.println(b.getFront());        // 98
    }
}
