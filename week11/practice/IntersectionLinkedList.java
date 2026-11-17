class IntersectionLinkedList {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static Node findIntersection(Node a, Node b) {
        if (a == null || b == null) return null;

        int lenA = getLength(a);
        int lenB = getLength(b);

        Node currA = a;
        Node currB = b;

        // Align starting points by advancing the longer list
        while (lenA > lenB) {
            currA = currA.next;
            lenA--;
        }
        while (lenB > lenA) {
            currB = currB.next;
            lenB--;
        }

        // Traverse simultaneously comparing node references
        while (currA != null && currB != null) {
            if (currA == currB) {
                return currA;
            }
            currA = currA.next;
            currB = currB.next;
        }

        return null;
    }

    private static int getLength(Node head) {
        int length = 0;
        Node curr = head;
        while (curr != null) {
            length++;
            curr = curr.next;
        }
        return length;
    }

    public static void main(String[] args) {
        // Sample 1: Intersecting at node with value 8
        Node common = new Node(8);
        common.next = new Node(4);
        common.next.next = new Node(5);

        Node a1 = new Node(4);
        a1.next = new Node(1);
        a1.next.next = common;

        Node b1 = new Node(5);
        b1.next = new Node(6);
        b1.next.next = new Node(1);
        b1.next.next.next = common;

        Node intersect1 = findIntersection(a1, b1);
        System.out.println("Sample 1 Output: " + (intersect1 != null ? "Node with value " + intersect1.data : "null"));

        // Sample 2: Non-intersecting lists
        Node a2 = new Node(2);
        a2.next = new Node(6);
        a2.next.next = new Node(4);

        Node b2 = new Node(1);
        b2.next = new Node(5);

        Node intersect2 = findIntersection(a2, b2);
        System.out.println("Sample 2 Output: " + (intersect2 != null ? "Node with value " + intersect2.data : "null"));
    }
}