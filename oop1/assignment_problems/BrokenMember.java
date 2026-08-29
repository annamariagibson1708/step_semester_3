class BrokenMember {
    static String name;

    public BrokenMember(String n) {
        name = n;
    }
}

class LibraryMember {
    String name;
    String memberId;
    int booksIssued;
    static int memberCount = 0;

    public LibraryMember(String name, int booksIssued) {
        this.name = name;
        this.booksIssued = booksIssued;
        memberCount++;
        this.memberId = "LM-" + (1000 + memberCount);
    }

    public void printMemberCard() {
        System.out.println(name + " | " + memberId);
    }

    public static void printTotalMembers() {
        System.out.println("Total members: " + memberCount);
    }
}

public class libbound {
    public static void main(String[] args) {
        System.out.println("Broken version:");
        BrokenMember m1 = new BrokenMember("Aditi");
        BrokenMember m2 = new BrokenMember("Rohan");
        System.out.println(BrokenMember.name);
        System.out.println(BrokenMember.name);
        System.out.println("(Aditi's data was overwritten — both members now show \"Rohan\")\n");

        System.out.println("Fixed version:");
        LibraryMember fixed1 = new LibraryMember("Aditi", 3);
        LibraryMember fixed2 = new LibraryMember("Rohan", 5);
        fixed1.printMemberCard();
        fixed2.printMemberCard();
        LibraryMember.printTotalMembers();
    }
}

