class PolynomialCalculator {

    // Custom Singly Linked List Node representing a polynomial term
    static class Node {
        int coeff;
        int exp;
        Node next;

        Node(int coeff, int exp) {
            this.coeff = coeff;
            this.exp = exp;
            this.next = null;
        }
    }

    // Adds two polynomials represented as sorted singly linked lists (decreasing exponents)
    public static Node addPolynomials(Node p, Node q) {
        Node dummy = new Node(0, 0);
        Node tail = dummy;

        while (p != null && q != null) {
            if (p.exp > q.exp) {
                if (p.coeff != 0) {
                    tail.next = new Node(p.coeff, p.exp);
                    tail = tail.next;
                }
                p = p.next;
            } else if (q.exp > p.exp) {
                if (q.coeff != 0) {
                    tail.next = new Node(q.coeff, q.exp);
                    tail = tail.next;
                }
                q = q.next;
            } else {
                // Exponents are equal: sum the coefficients
                int sumCoeff = p.coeff + q.coeff;
                // Omit terms whose coefficients add up to 0
                if (sumCoeff != 0) {
                    tail.next = new Node(sumCoeff, p.exp);
                    tail = tail.next;
                }
                p = p.next;
                q = q.next;
            }
        }

        // Copy remaining terms from polynomial p, if any
        while (p != null) {
            if (p.coeff != 0) {
                tail.next = new Node(p.coeff, p.exp);
                tail = tail.next;
            }
            p = p.next;
        }

        // Copy remaining terms from polynomial q, if any
        while (q != null) {
            if (q.coeff != 0) {
                tail.next = new Node(q.coeff, q.exp);
                tail = tail.next;
            }
            q = q.next;
        }

        return dummy.next;
    }

    // Prints the polynomial in standard algebraic form (e.g., 5x^3 + 9x^2 - 3x + 3)
    public static void printPolynomial(Node head) {
        if (head == null) {
            System.out.println("0");
            return;
        }

        StringBuilder sb = new StringBuilder();
        Node curr = head;
        boolean isFirst = true;

        while (curr != null) {
            int c = curr.coeff;
            int e = curr.exp;

            if (c != 0) {
                // Handle sign formatting
                if (isFirst) {
                    if (c < 0) sb.append("-");
                    isFirst = false;
                } else {
                    sb.append(c > 0 ? " + " : " - ");
                }

                int absC = Math.abs(c);

                // Print coefficient (omit '1' if exponent > 0)
                if (absC != 1 || e == 0) {
                    sb.append(absC);
                }

                // Print variable and exponent
                if (e == 1) {
                    sb.append("x");
                } else if (e > 1) {
                    sb.append("x^").append(e);
                }
            }
            curr = curr.next;
        }

        System.out.println(sb.length() == 0 ? "0" : sb.toString());
    }

    // Helper method to insert a term at the end of a list
    private static Node appendTerm(Node head, int coeff, int exp) {
        Node newNode = new Node(coeff, exp);
        if (head == null) return newNode;
        Node curr = head;
        while (curr.next != null) {
            curr = curr.next;
        }
        curr.next = newNode;
        return head;
    }

    public static void main(String[] args) {
        // Sample 1:
        // p = 5x^3 + 4x^2 + 2
        Node p1 = null;
        p1 = appendTerm(p1, 5, 3);
        p1 = appendTerm(p1, 4, 2);
        p1 = appendTerm(p1, 2, 0);

        // q = 5x^2 - 3x + 1
        Node q1 = null;
        q1 = appendTerm(q1, 5, 2);
        q1 = appendTerm(q1, -3, 1);
        q1 = appendTerm(q1, 1, 0);

        System.out.print("Sample 1 Output: ");
        Node res1 = addPolynomials(p1, q1);
        printPolynomial(res1); // Expected: 5x^3 + 9x^2 - 3x + 3

        // Sample 2:
        // p = 3x^2 + 1
        Node p2 = null;
        p2 = appendTerm(p2, 3, 2);
        p2 = appendTerm(p2, 1, 0);

        // q = -3x^2 + 4x
        Node q2 = null;
        q2 = appendTerm(q2, -3, 2);
        q2 = appendTerm(q2, 4, 1);

        System.out.print("Sample 2 Output: ");
        Node res2 = addPolynomials(p2, q2);
        printPolynomial(res2); // Expected: 4x + 1
    }
}