package step_semester_3.access_modifier.assignment;

// Access control rule engine and linter tool
class MembershipAccessEngine {

    /**
     * Classifies a single access attempt based on canonical Java visibility rules
     * across the basic contexts (without cross-package inheritance).
     */
    public static String classifyAccess(String fieldModifier, String accessorContext) {
        if (fieldModifier == null || accessorContext == null) {
            return "DENIED";
        }

        switch (fieldModifier) {
            case "private":
                return "SAME_CLASS".equals(accessorContext) ? "ALLOWED" : "DENIED";

            case "default":
            case "protected":
                // In these three basic package boundaries, protected and default 
                // behave identically (subclass access requires a different package context).
                return ("SAME_CLASS".equals(accessorContext) || "SAME_PACKAGE".equals(accessorContext)) 
                        ? "ALLOWED" : "DENIED";

            case "public":
                return "ALLOWED";

            default:
                return "DENIED";
        }
    }

    /**
     * Groups and logs the total number of allowed vs denied access patterns per modifier.
     */
    public static String summarizeByModifier(String[][] attempts) {
        // Trackers for each modifier category
        int privAllowed = 0, privDenied = 0;
        int defAllowed = 0, defDenied = 0;
        int protAllowed = 0, protDenied = 0;
        int pubAllowed = 0, pubDenied = 0;

        if (attempts != null) {
            for (String[] attempt : attempts) {
                if (attempt != null && attempt.length >= 2) {
                    String modifier = attempt[0];
                    String context = attempt[1];
                    String outcome = classifyAccess(modifier, context);

                    if ("private".equals(modifier)) {
                        if ("ALLOWED".equals(outcome)) privAllowed++; else privDenied++;
                    } else if ("default".equals(modifier)) {
                        if ("ALLOWED".equals(outcome)) defAllowed++; else defDenied++;
                    } else if ("protected".equals(modifier)) {
                        if ("ALLOWED".equals(outcome)) protAllowed++; else protDenied++;
                    } else if ("public".equals(modifier)) {
                        if ("ALLOWED".equals(outcome)) pubAllowed++; else pubDenied++;
                    }
                }
            }
        }

        // Return exact string formatting specified by the example output
        return String.format(
            "private: %d allowed / %d denied | default: %d allowed / %d denied | protected: %d allowed / %d denied | public: %d allowed / %d denied",
            privAllowed, privDenied, defAllowed, defDenied, protAllowed, protDenied, pubAllowed, pubDenied
        );
    }
}

// Data modeling class representing a library membership record
public class LibraryMember {
    // Standard encapsulation: lock state variables using private visibility
    private String membershipId;
    private String branchCode;
    private double finesOwed;
    private String displayName;

    // Single parameterized constructor acting as a strict construction gate
    public LibraryMember(String membershipId, String branchCode, double finesOwed, String displayName) {
        // Hint: Trim first, then check blankness and minimum length boundary in a single pass
        if (membershipId == null || membershipId.trim().length() < 4) {
            throw new IllegalArgumentException("construction rejected");
        }

        this.membershipId = membershipId.trim();
        this.branchCode = branchCode;
        this.finesOwed = finesOwed;
        this.displayName = displayName;
    }

    // Explicitly omit any no-argument constructors to prevent unvalidated initialization
    
    // Standard JavaBean-compliant accessors
    public String getMembershipId() { return membershipId; }
    public String getBranchCode() { return branchCode; }
    public double getFinesOwed() { return finesOwed; }
    public String getDisplayName() { return displayName; }
}
