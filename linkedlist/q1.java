class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class Solution {
    public static Node removeKthFromEnd(Node head, int k) {
        // Create a dummy node pointing to the head
        Node dummy = new Node(0);
        dummy.next = head;

        Node lead = dummy;
        Node trailing = dummy;

        // Advance the lead pointer k + 1 steps ahead
        for (int i = 0; i <= k; i++) {
            lead = lead.next;
        }

        // Move both pointers together until lead reaches the end (null)
        while (lead != null) {
            lead = lead.next;
            trailing = trailing.next;
        }

        // Skip the k-th node from the end
        trailing.next = trailing.next.next;

        // Return the updated list head
        return dummy.next;
    }
}
