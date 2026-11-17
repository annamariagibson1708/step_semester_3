import java.util.*;

// Capability Interfaces
interface Dimmable {
    void dim(int level);
}

interface Schedulable {
    void schedule(String time);
}

interface EnergyMonitorable {
    String getEnergyUsage();
}

// Base Device Class
abstract class Device {
    protected String id;
    protected boolean isOn;

    public Device(String id) {
        this.id = id;
        this.isOn = false;
    }

    public String getId() {
        return id;
    }

    public void turnOn() {
        this.isOn = true;
        System.out.println(id + " is ON");
    }

    public void turnOff() {
        this.isOn = false;
        System.out.println(id + " is OFF");
    }
}

// Concrete Device Classes
class Light extends Device implements Dimmable, Schedulable {
    private int brightness;

    public Light(String id) {
        super(id);
        this.brightness = 100;
    }

    @Override
    public void dim(int level) {
        this.brightness = level;
        System.out.println(id + " dimmed to " + level);
    }

    @Override
    public void schedule(String time) {
        System.out.println(id + " scheduled " + time);
    }
}

class Fan extends Device implements Schedulable {
    public Fan(String id) {
        super(id);
    }

    @Override
    public void schedule(String time) {
        System.out.println(id + " scheduled " + time);
    }
}

class SmartPlug extends Device implements EnergyMonitorable {
    private String energyUsage;

    public SmartPlug(String id, String energyUsage) {
        super(id);
        this.energyUsage = energyUsage;
    }

    @Override
    public String getEnergyUsage() {
        return energyUsage;
    }
}

// Smart Home Controller
public class SmartHomePlatform {
    private Map<String, Device> devices = new HashMap<>();

    public void addDevice(Device device) {
        devices.put(device.getId(), device);
    }

    public void executeCommand(String commandLine) {
        String[] parts = commandLine.split(" ");
        String action = parts[0];
        String id = parts[1];

        Device device = devices.get(id);
        if (device == null) {
            System.out.println(id + " not found");
            return;
        }

        switch (action) {
            case "ON":
                device.turnOn();
                break;
            case "OFF":
                device.turnOff();
                break;
            case "DIM":
                if (device instanceof Dimmable) {
                    int level = Integer.parseInt(parts[2]);
                    ((Dimmable) device).dim(level);
                } else {
                    System.out.println(id + " rejected: DIM unsupported");
                }
                break;
            case "SCHEDULE":
                if (device instanceof Schedulable) {
                    ((Schedulable) device).schedule(parts[2]);
                } else {
                    System.out.println(id + " rejected: SCHEDULE unsupported");
                }
                break;
            case "ENERGY":
                if (device instanceof EnergyMonitorable) {
                    System.out.println(id + " energy " + ((EnergyMonitorable) device).getEnergyUsage());
                } else {
                    System.out.println(id + " rejected: ENERGY unsupported");
                }
                break;
            default:
                System.out.println("Unknown command: " + action);
        }
    }

    public static void main(String[] args) {
        SmartHomePlatform home = new SmartHomePlatform();

        // Register sample devices
        home.addDevice(new Light("L1"));
        home.addDevice(new Fan("F1"));
        home.addDevice(new SmartPlug("P1", "12 kWh"));

        // Execute sample commands
        String[] commands = {
            "ON L1",
            "DIM L1 40",
            "DIM F1 30",
            "SCHEDULE F1 22:00",
            "ENERGY P1",
            "ENERGY L1"
        };

        for (String cmd : commands) {
            home.executeCommand(cmd);
        }
    }
}