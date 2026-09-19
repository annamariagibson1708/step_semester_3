package step_semester_3.access_modifier.practice;

// Core rule engine for static access level analysis
class AccessRuleEngine {

    /**
     * Classifies a single access attempt based on canonical Java visibility rules
     * across basic contexts (without inheritance).
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
                // As noted in the problem explanation, protected behaves identically 
                // to default until inheritance is factored in.
                return ("SAME_CLASS".equals(accessorContext) || "SAME_PACKAGE".equals(accessorContext)) 
                        ? "ALLOWED" : "DENIED";

            case "public":
                return "ALLOWED";

            default:
                return "DENIED";
        }
    }

    /**
     * Processes a batch matrix of access queries and prints the summary metrics.
     */
    public static String summarizeBatch(String[][] attempts) {
        int allowedCount = 0;
        int deniedCount = 0;

        if (attempts == null) {
            return "Allowed: 0 | Denied: 0";
        }

        for (String[] attempt : attempts) {
            if (attempt != null && attempt.length >= 2) {
                String result = classifyAccess(attempt[0], attempt[1]);
                if ("ALLOWED".equals(result)) {
                    allowedCount++;
                } else {
                    deniedCount++;
                }
            }
        }

        return "Allowed: " + allowedCount + " | Denied: " + deniedCount;
    }
}

// Data modeling class representing a single protected patient record
public class PatientRecord {
    // Standard encapsulation practices lock fields against direct wild modification
    private String patientId;
    private String wardCode;
    private double vitalsScore;
    private String facilityName;

    // Single parameterized constructor acting as a strict validation gate
    public PatientRecord(String patientId, String wardCode, double vitalsScore, String facilityName) {
        // Single-pass string verification handling null, blank, spaces, and length threshold rules
        if (patientId == null || patientId.trim().length() < 4) {
            throw new IllegalArgumentException("Construction rejected: Patient ID must be at least 4 non-whitespace characters.");
        }

        this.patientId = patientId.trim();
        this.wardCode = wardCode;
        this.vitalsScore = vitalsScore;
        this.facilityName = facilityName;
    }

    // Accessors (Getters) to allow safe read operations
    public String getPatientId() { return patientId; }
    public String getWardCode() { return wardCode; }
    public double getVitalsScore() { return vitalsScore; }
    public String getFacilityName() { return facilityName; }
}
