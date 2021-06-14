public class Deque {

    SMnode head; // Creates a reference to our 'head' client
    SMnode last; // Creates a reference to our last 'client' in the queue
    int count = 0; // Creating a global int variable to store length of our queue

    static final int SCAN = -1;

    public void put(int nodeIn) { // PUT is going in the BACK of the QUEUE

        SMnode temp = new SMnode(nodeIn);

        if (head == null) { // If the queue is empty to begin with 
            head = temp; // Set our new node as the head
            last = head; // Last should also point to the same thing as there is only 1 in the queue
        } else {
            last.next = temp; // Connect our new node to the end of the queue
            last = temp; // The new node also becomes the last
        }

        count++;
        return;
    }

    public int get() {
        SMnode temp = head; // Make a temp reference to head
        head = temp.next; // Make our head point to the next item in queue 
        temp.next = null; // And set our temp's .next to null to safely disconnect.

        if (head == null) { // If we happened to delete the only item in our queue
            last = null; // Then set last to null too.
        }
        count--;
        return temp.node;
    }

    public Boolean peek() {
        // Returns whether or not there's a value left
        return head != null;
    }

    public void push (int nodeIn) { // PUSH is going in the FRONT of the QUEUE

        SMnode temp = new SMnode(nodeIn);

        if (head == null) { // If the queue is empty to begin with, perform the same operations as push
            head = temp;
            last = head; 
        } else {
            temp.next = head; // Set our new frontmost nodes' .next to the current head
            head = temp; // The new mode becomes our head
        }

    }

    public int length() {
        return count;
    }

    public void traverse() {

        SMnode temp = head;
        while(temp != null) {
            System.out.println(temp.node);
            temp = temp.next; 
        }
    }

    public void initialise() {
        head = null;
        last = null;
        count = 0;
        push(SCAN);
    }

    public class SMnode {
        public int node; 
        public SMnode next;

        public SMnode(int nodeIn) {
            node = nodeIn;
        }

    }
}
