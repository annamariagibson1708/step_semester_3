package class_problems;

public class BusRoute {
    private String routeCode;
    private String routeName;
    private int priority;

    // 1. Primary constructor resolving field naming clashes using 'this'
    public BusRoute(String routeCode, String routeName, int priority) {
        this.routeCode = routeCode;
        this.routeName = routeName;
        this.priority = priority;
    }

    // 2. Chained constructor defaulting priority to 3 (matching example baseline)
    public BusRoute(String routeCode, String routeName) {
        this(routeCode, routeName, 3);
    }

    // 3. Multi-level deterministic comparison logic
    public int compareTo(BusRoute other) {
        // Tie-breaker 1: Higher priority comes first (descending order)
        if (this.priority != other.priority) {
            return Integer.compare(other.priority, this.priority);
        }

        // Tie-breaker 2: Case-insensitive route code comparison (ascending order)
        int caseInsensitiveCompare = this.routeCode.compareToIgnoreCase(other.routeCode);
        if (caseInsensitiveCompare != 0) {
            return caseInsensitiveCompare;
        }

        // Tie-breaker 3: Strict case comparison for codes differing only in letter case
        return this.routeCode.compareTo(other.routeCode);
    }

    // 4. Stable in-place Insertion Sort routine to honor original arrival order on total ties
    public static BusRoute[] rankRoutes(BusRoute[] routes) {
        if (routes == null || routes.length <= 1) {
            return routes;
        }

        // Insertion sort is chosen here because it is inherently stable O(n²)
        for (int i = 1; i < routes.length; i++) {
            BusRoute key = routes[i];
            int j = i - 1;

            // Move elements that should follow 'key' one position ahead
            while (j >= 0 && routes[j].compareTo(key) > 0) {
                routes[j + 1] = routes[j];
                j--;
            }
            routes[j + 1] = key;
        }

        return routes;
    }

    // Optional getters for verification
    public String getRouteCode() { return routeCode; }
    public String getRouteName() { return routeName; }
    public int getPriority() { return priority; }
}
