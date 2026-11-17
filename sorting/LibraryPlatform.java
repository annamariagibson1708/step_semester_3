// Capability Interfaces
interface Reservable {
    void reserve(String memberId);
}

interface Downloadable {
    void download();
}

// Base Resource Class
abstract class Resource {
    protected String id;

    public Resource(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}

// Concrete Resource Types
class Book extends Resource implements Reservable {
    public Book(String id) {
        super(id);
    }

    @Override
    public void reserve(String memberId) {
        System.out.println(id + " reserved for " + memberId);
    }
}

class EBook extends Resource implements Downloadable {
    public EBook(String id) {
        super(id);
    }

    @Override
    public void download() {
        System.out.println(id + " downloaded");
    }
}

// Library Platform Controller
public class LibraryPlatform {
    private Resource[] resources;
    private int count;

    public LibraryPlatform(int capacity) {
        this.resources = new Resource[capacity];
        this.count = 0;
    }

    // Binary search by ID; returns index if found, or -(insertionPoint + 1) if not found
    private int binarySearch(String id) {
        int low = 0, high = count - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int cmp = resources[mid].getId().compareTo(id);
            if (cmp == 0) {
                return mid;
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -(low + 1);
    }

    public void addResource(Resource resource) {
        int idx = binarySearch(resource.getId());
        if (idx >= 0) {
            System.out.println("duplicate rejected");
            return;
        }
        if (count == resources.length) {
            System.out.println("Library capacity full");
            return;
        }

        // Shift elements right to maintain sorted order by ID
        int insertPos = -(idx + 1);
        for (int i = count; i > insertPos; i--) {
            resources[i] = resources[i - 1];
        }
        resources[insertPos] = resource;
        count++;
        System.out.println(resource.getId() + " added");
    }

    public void reserveResource(String id, String memberId) {
        int idx = binarySearch(id);
        if (idx < 0) {
            System.out.println(id + " not found");
            return;
        }
        Resource r = resources[idx];
        if (r instanceof Reservable) {
            ((Reservable) r).reserve(memberId);
        } else {
            System.out.println(id + " rejected: reserve unsupported");
        }
    }

    public void downloadResource(String id) {
        int idx = binarySearch(id);
        if (idx < 0) {
            System.out.println(id + " not found");
            return;
        }
        Resource r = resources[idx];
        if (r instanceof Downloadable) {
            ((Downloadable) r).download();
        } else {
            System.out.println(id + " rejected: download unsupported");
        }
    }

    public void findResource(String id) {
        int idx = binarySearch(id);
        if (idx >= 0) {
            System.out.println(id + " found at index " + idx);
        } else {
            System.out.println(id + " not found");
        }
    }

    public static void main(String[] args) {
        LibraryPlatform library = new LibraryPlatform(10);

        library.addResource(new Book("B1"));   // B1 added
        library.addResource(new Book("B1"));   // duplicate rejected
        library.addResource(new EBook("E1"));  // E1 added
        library.reserveResource("B1", "M1");   // B1 reserved for M1
        library.downloadResource("B1");        // B1 rejected: download unsupported
        library.downloadResource("E1");        // E1 downloaded
        library.findResource("E1");            // E1 found at index 1
    }
}