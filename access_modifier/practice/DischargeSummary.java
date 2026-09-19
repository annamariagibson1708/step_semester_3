package step_semester_3.access_modifier.practice;

import java.util.Arrays;

// Base class representing a permanent, unmodifiable summary
// Note: To allow CriticalCareDischargeSummary to extend it as shown in the batch example, 
// the base class cannot be marked final, but all its fields are strictly final.
public class DischargeSummary {
    private final String patientId;
    private final String[] medicationCodes;

    // One-time shared system initialization block
    static {
        System.out.println("MediTrack Discharge Registry System Active.");
    }

    // Constructor enforcing structural gate verification and defensive copying
    public DischargeSummary(String patientId, String[] medicationCodes) {
        if (patientId == null || patientId.trim().isEmpty()) {
            throw new IllegalArgumentException("Patient ID cannot be empty.");
        }
        if (medicationCodes == null) {
            throw new IllegalArgumentException("Medication codes cannot be null.");
        }

        // Validate format: "MED-" followed by exactly one uppercase letter
        for (String code : medicationCodes) {
            if (code == null || !code.matches("^MED-[A-Z]$")) {
                throw new IllegalArgumentException("Invalid medication code format encountered.");
            }
        }

        this.patientId = patientId;
        // Defensive copy on the way in to prevent external array mutation
        this.medicationCodes = medicationCodes.clone();
    }

    // Accessor utilizing defensive copy on the way out
    public String[] getMedicationCodes() {
        return this.medicationCodes.clone();
    }

    public String getPatientId() {
        return this.patientId;
    }

    // With-style modifier returning an entirely new immutable instance
    public DischargeSummary withCorrectedMedication(int index, String newCode) {
        if (index < 0 || index >= this.medicationCodes.length) {
            throw new IndexOutOfBoundsException("Invalid index provided.");
        }
        
        String[] updatedCodes = this.medicationCodes.clone();
        updatedCodes[index] = newCode;
        
        // Construction will automatically validate the new code structure
        return new DischargeSummary(this.patientId, updatedCodes);
    }
}

// Critical-care variant subclass locked against modification using final
final class CriticalCareDischargeSummary extends DischargeSummary {
    private final int icuDays;

    public CriticalCareDischargeSummary(String patientId, String[] medicationCodes, int icuDays) {
        super(patientId, medicationCodes);
        if (icuDays < 0) {
            throw new IllegalArgumentException("ICU days cannot be negative.");
        }
        this.icuDays = icuDays;
    }

    public int getIcuDays() {
        return this.icuDays;
    }
}

// Core analytical reconciliation pipeline
class NightlyLedgerEngine {
    
    public static String processNightlyBatch(DischargeSummary[] summaries) {
        int processedCount = 0;
        int nullSkippedCount = 0;
        int criticalCareCount = 0;
        int routineCount = 0;

        if (summaries == null) {
            return "0 processed | 0 null skipped | 0 critical-care | 0 routine";
        }

        // Single-pass processing block with O(1) auxiliary telemetry counters
        for (DischargeSummary summary : summaries) {
            if (summary == null) {
                nullSkippedCount++;
                continue;
            }

            processedCount++;

            // Differentiating object variants using the instanceof operator
            if (summary instanceof CriticalCareDischargeSummary) {
                criticalCareCount++;
            } else {
                routineCount++;
            }
        }

        // Returns telemetry summary text matching specifications exactly
        return String.format("%d processed | %d null skipped | %d critical-care | %d routine",
                processedCount, nullSkippedCount, criticalCareCount, routineCount);
    }
}
