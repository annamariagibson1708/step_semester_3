package step_semester_3.access_modifier.practice;
public class PatientProfile {
    private String patientId;
    private String name;
    private boolean discharged;
    private String securedPin; // One-way storage for write-only PIN

    // 1. No-argument constructor required by JavaBean reflection frameworks
    public PatientProfile() {
        this(null, null);
    }

    // 2. Name-only intermediate constructor
    public PatientProfile(String name) {
        this(null, name);
    }

    // 3. Primary initialization constructor acting as the central pipeline path
    public PatientProfile(String patientId, String name) {
        this.patientId = patientId;
        this.name = name;
        this.discharged = false;
        this.securedPin = null;
    }

    // Write-once setter rule: lock values permanently once initialized
    public void setPatientId(String patientId) {
        if (this.patientId == null) {
            this.patientId = patientId;
        }
        // Subsequent configuration modifications are silently discarded
    }

    public String getPatientId() {
        return this.patientId;
    }

    // Ordinary field standard JavaBean getters and setters
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setDischarged(boolean discharged) {
        this.discharged = discharged;
    }

    // Compliant boolean prefix naming convention
    public boolean isDischarged() {
        return this.discharged;
    }

    // True Write-Only property: validation gate with no paired getter leakage
    public void setLockerPin(String pin) {
        if (pin == null || !pin.matches("^\\d{4,6}$")) {
            throw new IllegalArgumentException("PIN must be a 4-6 digit numeric string.");
        }
        
        // Deterministic one-way cryptographic hash simulation for storage validation
        this.securedPin = String.valueOf((pin + "MED_SALT").hashCode());
    }
}
