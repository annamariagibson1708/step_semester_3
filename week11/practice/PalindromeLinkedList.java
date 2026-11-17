class PalindromeLinkedList {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static boolean isPalindrome(Node head) {
        if (head == null || head.next == null) {
            return true;
        }

        // 1. Find the end of the first half
        Node slow = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 2. Reverse the second half in-place
        Node secondHalfHead = reverseList(slow.next);

        // 3. Compare first half with reversed second half
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

        // 4. Restore the original list order
        slow.next = reverseList(secondHalfHead);

        return result;
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

    public static void main(String[] args) {
        // Sample 1: 1 -> 2 -> 2 -> 1
        Node list1 = new Node(1);
        list1.next = new Node(2);
        list1.next.next = new Node(2);
        list1.next.next.next = new Node(1);
        System.out.println("Sample 1 Output: " + isPalindrome(list1)); // Expected: true

        // Sample 2: 1 -> 2 -> 3
        Node list2 = new Node(1);
        list2.next = new Node(2);
        list2.next.next = new Node(3);
        System.out.println("Sample 2 Output: " + isPalindrome(list2)); // Expected: false
    }
}