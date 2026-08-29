class HostelRoom {
    String roomNo;
    int beds;
    int occupied;

    public HostelRoom(String roomNo, int beds) {
        this.roomNo = roomNo;
        this.beds = beds;
        this.occupied = 0;
    }

    public void allot(String studentName) {
        if (occupied < beds) {
            occupied++;
            System.out.println(studentName + " allotted to room " + roomNo);
        } else {
            System.out.println("Waiting list: Room " + roomNo + " is full.");
        }
    }
}

public class hostel {
    public static void main(String[] args) {
        HostelRoom room214 = new HostelRoom("C-214", 2);
        HostelRoom sameRoom = room214;

        sameRoom.allot("Ravi");

        System.out.println("room214 occupied (seen via first variable): " + room214.occupied);

        HostelRoom separate = new HostelRoom("C-214", 2);
        separate.occupied = room214.occupied;

        System.out.println("sameRoom == room214: " + (sameRoom == room214));
        System.out.println("separate == room214: " + (separate == room214));
    }
}
