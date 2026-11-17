public class TokenMirrorCheck {

    // Custom Singly Linked List Node
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // Checks if the linked list is a palindrome in O(1) extra space and restores the original list
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

        // 3. Compare the first half and the reversed second half node by node
        Node p1 = head;
        Node p2 = secondHalfHead;
        boolean result = true;
        while (p2 != null) {
            if (p1.data != p2.data) {
                result = false;
                break;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        // 4. Reverse the second half again and reattach it to restore the exact original list
        slow.next = reverseList(secondHalfHead);

        return result;
    }

    // Helper method to reverse a linked list in-place
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
        // Sample 1: tokens = 4 -> 7 -> 9 -> 7 -> 4
        Node list1 = buildList(new int[]{4, 7, 9, 7, 4});
        System.out.println("Palindrome: " + isPalindrome(list1));
        System.out.print("List after check: ");
        printList(list1);

        System.out.println();

        // Sample 2: tokens = 4 -> 7 -> 7 -> 9
        Node list2 = buildList(new int[]{4, 7, 7, 9});
        System.out.println("Palindrome: " + isPalindrome(list2));
        System.out.print("List after check: ");
        printList(list2);
    }
}