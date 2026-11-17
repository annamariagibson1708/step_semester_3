class TicketCounterMerge {

    // Custom Singly Linked List Node
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // Merges two sorted linked lists in-place using a single dummy node
    public static Node mergeSorted(Node a, Node b) {
        // Single dummy node allowed by constraints to simplify head handling
        Node dummy = new Node(-1);
        Node tail = dummy;

        // Traverse both lists and attach the smaller node to tail
        while (a != null && b != null) {
            if (a.data <= b.data) {
                tail.next = a;
                a = a.next;
            } else {
                tail.next = b;
                b = b.next;
            }
            tail = tail.next;
        }

        // Attach the remaining nodes from whichever list is not empty
        if (a != null) {
            tail.next = a;
        } else {
            tail.next = b;
        }

        return dummy.next;
    }

    // Helper method to print the linked list
    public static void printList(Node head) {
        if (head == null) {
            System.out.println("(empty)");
            return;
        }
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.data);
            if (curr.next != null) {
                System.out.print(" -> ");
            }
            curr = curr.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Sample 1: a = 3 -> 8 -> 15, b = 5 -> 8 -> 12 -> 20
        Node a1 = new Node(3);
        a1.next = new Node(8);
        a1.next.next = new Node(15);

        Node b1 = new Node(5);
        b1.next = new Node(8);
        b1.next.next = new Node(12);
        b1.next.next.next = new Node(20);

        System.out.print("Sample 1 Output: ");
        Node merged1 = mergeSorted(a1, b1);
        printList(merged1); // Expected: 3 -> 5 -> 8 -> 8 -> 12 -> 15 -> 20

        // Sample 2: a = (empty), b = 4
        Node a2 = null;
        Node b2 = new Node(4);

        System.out.print("Sample 2 Output: ");
        Node merged2 = mergeSorted(a2, b2);