package step_semester_3.access_modifier.assignment;

public class PatronAccount {
    private String accountId;
    private String fullName;
    private boolean vipStatus;
    private String encryptedSecurityAnswer; // Stored target for one-way security property

    // 1. No-argument constructor required by JavaBean compliance frameworks
    public PatronAccount() {
        this(null, null);
    }

    // 2. Name-only intermediate initialization constructor
    public PatronAccount(String fullName) {
        this(null, fullName);
    }

    // 3. Primary constructor serving as the single real initialization path
    public PatronAccount(String accountId, String fullName) {
        this.accountId = accountId;
        this.fullName = fullName;
        this.vipStatus = false;
        this.encryptedSecurityAnswer = null;
    }

    // Write-once configuration block: locks permanently once set
    public void setAccountId(String accountId) {
        if (this.accountId == null) {
            this.accountId = accountId;
        }
        // Subsequent mutation assignments are silently ignored
    }

    public String getAccountId() {
        return this.accountId;
    }

    // Standard JavaBean getter and setter pairs for the fullName field
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return this.fullName;
    }

    // Standard JavaBean compliant naming patterns for boolean states (is...)
    public void setVipStatus(boolean vipStatus) {
        this.vipStatus = vipStatus;
    }

    public boolean isVipStatus() {
        return this.vipStatus;
    }

    // True Write-Only property: encodes data securely with no matching getter leakage
    public void setSecurityAnswer(String answer) {
        if (answer == null) {
            this.encryptedSecurityAnswer = null;
        } else {
            // Apply a deterministic one-way hash transformation for data safety
            this.encryptedSecurityAnswer = String.valueOf((answer + "ALT_SALT").hashCode());
        }
    }
}
