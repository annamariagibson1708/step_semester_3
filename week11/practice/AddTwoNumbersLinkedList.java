class AddTwoNumbersLinkedList {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static Node addNumbers(Node a, Node b) {
        Node dummy = new Node(0);
        Node tail = dummy;
        int carry = 0;

        while (a != null || b != null || carry != 0) {
            int sum = carry;
            if (a != null) {
                sum += a.data;
                a = a.next;
            }
            if (b != null) {
                sum += b.data;
                b = b.next;
            }

            carry = sum / 10;
            tail.next = new Node(sum % 10);
            tail = tail.next;
        }

        return dummy.next;
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
        // Sample 1: a = 2 -> 4 -> 3 (342), b = 5 -> 6 -> 4 (465)
        Node a1 = new Node(2);
        a1.next = new Node(4);
        a1.next.next = new Node(3);

        Node b1 = new Node(5);
        b1.next = new Node(6);
        b1.next.next = new Node(4);

        System.out.print("Sample 1 Output: ");
        printList(addNumbers(a1, b1)); // Expected: 7 -> 0 -> 8

        // Sample 2: a = 9 -> 9 (99), b = 1 (1)
        Node a2 = new Node(9);
        a2.next = new Node(9);

        Node b2 = new Node(1);

        System.out.print("Sample 2 Output: ");
        printList(addNumbers(a2, b2)); // Expected: 0 -> 0 -> 1
    }
}