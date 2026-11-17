public class DeliveryRouteLoopDetector {

    // Custom Singly Linked List Node
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // Detects loop, finds its starting stop, and calculates loop length in O(1) extra space
    public static String detectLoop(Node head) {
        if (head == null) {
            return "Loop: no";
        }

        Node slow = head;
        Node fast = head;
        boolean hasLoop = false;

        // 1. Detect if a loop exists using Floyd's Cycle-Finding Algorithm
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                hasLoop = true;
                break;
            }
        }

        if (!hasLoop) {
            return "Loop: no";
        }

        // 2. Count the number of stops in the loop
        int loopLength = 1;
        Node temp = slow.next;
        while (temp != slow) {
            loopLength++;
            temp = temp.next;
        }

        // 3. Find the starting node of the loop
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return "Loop: yes, starts at stop " + slow.data + ", length " + loopLength;
    }

    // Helper method to build a linked list with an optional loop at 0-based index 'pos'
    private static Node buildRoute(int[] stops, int pos) {
        if (stops.length == 0) return null;

        Node head = new Node(stops[0]);
        Node curr = head;
        Node loopTarget = (pos == 0) ? head : null;

        for (int i = 1; i < stops.length; i++) {
            curr.next = new Node(stops[i]);
            curr = curr.next;
            if (i == pos) {
                loopTarget = curr;
            }
        }

        if (pos != -1 && loopTarget != null) {
            curr.next = loopTarget;
        }

        return head;
    }

    public static void main(String[] args) {
        // Sample 1: stops = 10 -> 20 -> 30 -> 40 -> 50, pos = 2
        Node route1 = buildRoute(new int[]{10, 20, 30, 40, 50}, 2);
        System.out.println("Sample 1 Output: " + detectLoop(route1));
        // Expected: Loop: yes, starts at stop 30, length 3

        // Sample 2: stops = 10 -> 20 -> 30, pos = -1
        Node route2 = buildRoute(new int[]{10, 20, 30}, -1);
        System.out.println("Sample 2 Output: " + detectLoop(route2));
        // Expected: Loop: no

        // Sample 3: stops = 7, pos = 0
        Node route3 = buildRoute(new int[]{7}, 0);
        System.out.println("Sample 3 Output: " + detectLoop(route3));
        // Expected: Loop: yes, starts at stop 7, length 1
    }
}