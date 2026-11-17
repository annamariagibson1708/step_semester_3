class ReorderLinkedList {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static void reorderList(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }

        // 1. Find the middle of the list using fast and slow pointers
        Node slow = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 2. Split the list into two halves and reverse the second half in-place
        Node secondHalf = reverseList(slow.next);
        slow.next = null; // Terminate the first half

        // 3. Merge the two halves alternately in-place
        Node firstHalf = head;
        while (secondHalf != null) {
            Node temp1 = firstHalf.next;
            Node temp2 = secondHalf.next;

            firstHalf.next = secondHalf;
            secondHalf.next = temp1;

            firstHalf = temp1;
            secondHalf = temp2;
        }
    }

    private static Node reverseList(Node head) {
        Node prev = null;
        Node curr = head;
        while (curr != null) {
            Node nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    private static void printList(Node head) {
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.data);
            if (curr.next != null) System.out.print(" -> ");
            curr = curr.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Sample 1: 1 -> 2 -> 3 -> 4 (Even length)
        Node list1 = new Node(1);
        list1.next = new Node(2);
        list1.next.next = new Node(3);
        list1.next.next.next = new Node(4);

        reorderList(list1);
        System.out.print("Sample 1 Output: ");
        printList(list1); // Expected: 1 -> 4 -> 2 -> 3

        // Sample 2: 1 -> 2 -> 3 -> 4 -> 5 (Odd length)
        Node list2 = new Node(1);
        list2.next = new Node(2);
        list2.next.next = new Node(3);
        list2.next.next.next = new Node(4);
        list2.next.next.next.next = new Node(5);

        reorderList(list2);
        System.out.print("Sample 2 Output: ");
        printList(list2); // Expected: 1 -> 5 -> 2 -> 4 -> 3
    }
}