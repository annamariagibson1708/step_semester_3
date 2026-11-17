import java.util.NoSuchElementException;

public class Order {

    // Static nested class: does not need a reference to an outer Order instance
    public static class LineItem {
        private String productName;
        private int quantity;
        private double unitPrice;

        public LineItem(String productName, int quantity, double unitPrice) {
            this.productName = productName;
            this.quantity = quantity;
            this.unitPrice = unitPrice;
        }

        public String getProductName() {
            return productName;
        }

        public double total() {
            return quantity * unitPrice;
        }
    }

    private LineItem[] items;
    private int count;

    public Order(int capacity) {
        this.items = new LineItem[capacity];
        this.count = 0;
    }

    public void addItem(LineItem item) {
        if (count < items.length) {
            items[count++] = item;
        }
    }

    // Member inner class: bound to a specific Order instance to access its private array
    public class Cursor {
        private int index = 0;

        public boolean hasNext() {
            return index < count;
        }

        public LineItem next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more line items.");
            }
            return items[index++];
        }

        public void reset() {
            index = 0;
        }
    }

    public Cursor cursor() {
        return new Cursor();
    }

    public static void main(String[] args) {
        Order order = new Order(100);
        order.addItem(new Order.LineItem("Pen", 3, 10));
        order.addItem(new Order.LineItem("Book", 2, 250));
        order.addItem(new Order.LineItem("Bag", 1, 700));

        Order.Cursor cursor = order.cursor();
        double orderTotal = 0;

        while (cursor.hasNext()) {
            Order.LineItem item = cursor.next();
            double lineTotal = item.total();
            orderTotal += lineTotal;
            System.out.println(item.getProductName() + " " + (int) lineTotal);
        }

        System.out.println("Total " + (int) orderTotal);
        System.out.println("cursor: inner (reads the order's array)");
        System.out.println("line item: static nested (needs no order)");
    }
}