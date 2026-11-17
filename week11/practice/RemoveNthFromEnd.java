class RemoveNthFromEnd {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // Removes the n-th node from the end in a single pass
    public static Node removeNthFromEnd(Node head, int n) {
        Node dummy = new Node(0);
        dummy.next = head;

        Node fast = dummy;
        Node slow = dummy;

        // Advance fast pointer n steps ahead
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        // Move both pointers until fast reaches the end of the list
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // Skip the target node
        slow.next = slow.next.next;

        return dummy.next;
    }

    // Helper method to print the list
    private static void printList(Node head) {
        if (head == null) {
            System.out.println("(empty list)");
            return;
        }
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.data);
            if (curr.next != null) System.out.print(" -> ");
            curr = curr.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Sample 1: head = 1 -> 2 -> 3 -> 4 -> 5, n = 2
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);

        System.out.print("Sample 1 Output: ");
        printList(removeNthFromEnd(head1, 2)); // Expected: 1 -> 2 -> 3 -> 5

        // Sample 2: head = 1, n = 1 (removing the head node)
        Node head2 = new Node(1);

        System.out.print("Sample 2 Output: ");
        printList(removeNthFromEnd(head2, 1)); // Expected: (empty list)
    }
}