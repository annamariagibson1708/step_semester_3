class PalindromeTrainCode {

    // Custom Singly Linked List Node
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // Checks if the linked list is a palindrome in O(n) time and O(1) extra space,
    // restoring the list to its original state before returning.
    public static boolean isPalindrome(Node head) {
        if (head == null || head.next == null) {
            return true;
        }

        // 1. Find the end of the first half using slow and fast pointers
        Node slow = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 2. Reverse the second half of the list
        Node secondHalfHead = reverseList(slow.next);

        // 3. Compare the first half and the reversed second half
        Node p1 = head;
        Node p2 = secondHalfHead;
        boolean isPalin = true;
        while (p2 != null) {
            if (p1.data != p2.data) {
                isPalin = false;
                break;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        // 4. Restore the second half back to its original order
        slow.next = reverseList(secondHalfHead);

        return isPalin;
    }

    // Helper method to reverse a singly linked list in-place
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

    // Helper method to build a list from an array
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
            System.out.print(curr.data);
            if (curr.next != null) System.out.print(" -> ");
            curr = curr.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Sample 1: 1 -> 2 -> 3 -> 2 -> 1
        Node list1 = buildList(new int[]{1, 2, 3, 2, 1});
        System.out.println("Sample 1 Output: " + isPalindrome(list1)); // Expected: true

        // Sample 2: 1 -> 2 -> 3 -> 2
        Node list2 = buildList(new int[]{1, 2, 3, 2});
        System.out.println("Sample 2 Output: " + isPalindrome(list2)); // Expected: false

        // Sample 3: 1 -> 2 -> 2 -> 1
        Node list3 = buildList(new int[]{1, 2, 2, 1});
        System.out.print("Sample 3 Output: " + isPalindrome(list3) + " | List afterwards: ");
        printList(list3); // Expected: true | 1 -> 2 -> 2 -> 1
    }
}