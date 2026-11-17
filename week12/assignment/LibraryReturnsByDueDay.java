public class LibraryReturnsByDueDay {

    // Singly Linked List Node holding a book title and due day
    static class Node {
        String title;
        int dueDay;
        Node next;

        Node(String title, int dueDay) {
            this.title = title;
            this.dueDay = dueDay;
            this.next = null;
        }
    }

    // Sorts the linked list by dueDay in ascending order using stable Merge Sort
    public static Node sortByDueDay(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        // 1. Split the list in half using slow and fast pointers
        Node mid = getMiddle(head);
        Node rightHead = mid.next;
        mid.next = null; // Cut the link after the middle node

        // 2. Recursively sort both halves
        Node leftSorted = sortByDueDay(head);
        Node rightSorted = sortByDueDay(rightHead);

        // 3. Merge the two sorted halves by relinking nodes
        return merge(leftSorted, rightSorted);
    }

    // Returns the first middle node so the list splits cleanly into two halves
    private static Node getMiddle(Node head) {
        Node slow = head;
        Node fast = head.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // Merges two sorted lists by changing links only; preserves drop order on ties
    private static Node merge(Node left, Node right) {
        Node dummy = new Node("", 0);
        Node tail = dummy;

        while (left != null && right != null) {
            // Use '<=' so the left half wins on ties, keeping the sort stable
            if (left.dueDay <= right.dueDay) {
                tail.next = left;
                left = left.next;
            } else {
                tail.next = right;
                right = right.next;
            }
            tail = tail.next;
        }

        // Attach remaining nodes from whichever list is non-empty
        if (left != null) {
            tail.next = left;
        } else {
            tail.next = right;
        }

        return dummy.next;
    }

    // Helper method to print the list
    private static void printList(Node head) {
        if (head == null) {
            System.out.println("null");
            return;
        }
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.title + ":" + curr.dueDay + " -> ");
            curr = curr.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        // Sample 1: Java:15 -> DSA:3 -> OS:9 -> DBMS:3 -> CN:12 -> AI:1
        Node head1 = new Node("Java", 15);
        head1.next = new Node("DSA", 3);
        head1.next.next = new Node("OS", 9);
        head1.next.next.next = new Node("DBMS", 3);
        head1.next.next.next.next = new Node("CN", 12);
        head1.next.next.next.next.next = new Node("AI", 1);

        System.out.print("Sample 1 Output: ");
        printList(sortByDueDay(head1));
        // Expected: AI:1 -> DSA:3 -> DBMS:3 -> OS:9 -> CN:12 -> Java:15 -> null

        // Sample 2: (empty)
        Node head2 = null;
        System.out.print("Sample 2 Output: ");
        printList(sortByDueDay(head2));
        // Expected: null
    }
}