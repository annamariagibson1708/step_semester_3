package step_semester_3.access_modifier.practice;

public class PatientVitals {
    // Encapsulated data fields locked to private access
    private double[] readings;
    private int size;

    // Constructor seeding history by calling recordReading directly
    public PatientVitals(double[] initialReadings) {
        // Initialize history tracking array with a baseline capacity
        this.readings = new double[10];
        this.size = 0;

        if (initialReadings != null) {
            // Reuses instance-method logic directly to prevent range check duplication
            for (double reading : initialReadings) {
                this.recordReading(reading);
            }
        }
    }

    // Centrally managed validation gate that filters out invalid telemetry readings
    public void recordReading(double reading) {
        // Silently reject impossible body temperature values
        if (reading <= 0 || reading > 45.0) {
            return;
        }

        // Manual dynamic growth block using fundamental array skills
        if (size == readings.length) {
            double[] expanded = new double[readings.length * 2];
            System.arraycopy(readings, 0, expanded, 0, size);
            readings = expanded;
        }

        readings[size++] = reading;
    }

    // Computes mathematical mean across valid captured logs
    public double getAverage() {
        if (size == 0) {
            return 0.0;
        }

        double total = 0.0;
        for (int i = 0; i < size; i++) {
            total += readings[i];
        }
        return total / size;
    }

    // Accessor that enforces a definitive defensive copy architecture
    public double[] getAllReadings() {
        // Create an exact-sized snapshot array to prevent pointer exposure
        double[] snapshot = new double[size];
        System.arraycopy(this.readings, 0, snapshot, 0, size);
        return snapshot;
    }
}
