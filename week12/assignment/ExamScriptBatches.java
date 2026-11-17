public class ExamScriptBatches {

    // Custom Singly Linked List Node
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // Reverses the linked list in groups of k iteratively in O(1) additional space
    public static Node reverseInGroups(Node head, int k) {
        if (head == null || k <= 1) {
            return head;
        }

        Node dummy = new Node(0);
        dummy.next = head;
        Node prevGroupTail = dummy;

        while (true) {
            // 1. Check if there are at least k nodes left to reverse
            Node kthNode = prevGroupTail;
            for (int i = 0; i < k && kthNode != null; i++) {
                kthNode = kthNode.next;
            }
            if (kthNode == null) {
                break; // Fewer than k nodes remain; leave in original order
            }

            // 2. Identify the start of the current group and the start of the next group
            Node groupHead = prevGroupTail.next;
            Node nextGroupHead = kthNode.next;

            // 3. Reverse exactly the k nodes in the current group
            Node prev = nextGroupHead; // Connects the new tail (old groupHead) directly to nextGroupHead
            Node curr = groupHead;
            while (curr != nextGroupHead) {
                Node temp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = temp;
            }

            // 4. Connect the previous group's tail to the new group head (kthNode)
            prevGroupTail.next = kthNode;
            prevGroupTail = groupHead; // Advance prevGroupTail to the end of this reversed group
        }

        return dummy.next;
    }

    // Helper method to build a linked list from an array
    private static Node buildList(int[] values) {
        if (values.length == 0) return null;
        Node head = new Node(values[0]);
        Node curr = head;
        for (int i = 1; i < values.length; i++) {
            curr.next = new Node(values[i]);
            curr = curr.next;
        }
        return head;
    }

    // Helper method to print the list
    private static void printList(Node head) {
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.data + " -> ");
            curr = curr.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        // Sample 1: scripts = 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8, k = 3
        Node list1 = buildList(new int[]{1, 2, 3, 4, 5, 6, 7, 8});
        System.out.print("Sample 1 Output: ");
        printList(reverseInGroups(list1, 3));
        // Expected: 3 -> 2 -> 1 -> 6 -> 5 -> 4 -> 7 -> 8 -> null

        // Sample 2: scripts = 11 -> 12, k = 3
        Node list2 = buildList(new int[]{11, 12});
        System.out.print("Sample 2 Output: ");
        printList(reverseInGroups(list2, 3));
        // Expected: 11 -> 12 -> null
    }
}