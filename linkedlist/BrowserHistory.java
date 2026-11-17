class BrowserHistory {

    // Custom Doubly Linked List Node
    private class Node {
        String url;
        Node prev;
        Node next;

        Node(String url) {
            this.url = url;
            this.prev = null;
            this.next = null;
        }
    }

    private Node current;

    // Initializes the browser with the homepage
    public BrowserHistory(String homepage) {
        current = new Node(homepage);
    }

    // Visits a new URL and clears all forward history
    public void visit(String url) {
        Node newNode = new Node(url);
        current.next = newNode; // Severing any existing forward history
        newNode.prev = current;
        current = newNode;
    }

    // Moves back up to 'steps' in history and returns the current URL
    public String back(int steps) {
        while (steps > 0 && current.prev != null) {
            current = current.prev;
            steps--;
        }
        return current.url;
    }

    // Moves forward up to 'steps' in history and returns the current URL
    public String forward(int steps) {
        while (steps > 0 && current.next != null) {
            current = current.next;
            steps--;
        }
        return current.url;
    }

    // Main method to test the provided example
    public static void main(String[] args) {
        BrowserHistory browserHistory = new BrowserHistory("srm.edu");
        browserHistory.visit("a.com");
        browserHistory.visit("b.com");
        browserHistory.visit("c.com");

        System.out.println(browserHistory.back(1));      // Output: b.com
        System.out.println(browserHistory.back(1));      // Output: a.com
        System.out.println(browserHistory.forward(1));   // Output: b.com

        browserHistory.visit("d.com");                   // Clears c.com from forward history

        System.out.println(browserHistory.forward(2));   // Output: d.com (cannot move forward)
        System.out.println(browserHistory.back(2));      // Output: a.com
        System.out.println(browserHistory.back(7));      // Output: srm.edu (stops at first page)
    }
}