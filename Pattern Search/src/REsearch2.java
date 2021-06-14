import java.io.*;
import java.util.*;

// NOTE: This is a new REsearch class which was developed after submission. Mainly created to solve issues in the 1st implementation.
// Submitted on 14/06
public class REsearch2 {

    int mark = 0;
    int pointer = 0;

    char BRANCH = '#';
    char FINAL_STATE_CHAR = '$';
    static final int SCAN = -1;

    Deque deq = new Deque(); // Our deque
    ArrayList<SM> states = new ArrayList<>(); // List to store all the states being piped in


    public static void main (String [] args) {
        REsearch2 search = new REsearch2();
        search.importFsms();
        try {
            if (args[0] != null && args[0] != "") {
                search.scan(args[0]); // Pass our filename argument
            } else {
                System.err.println("Usage: REsearch <text filename> \n The file must be within the same directory as this class!");
            }
        } catch (Exception ex) {
            System.err.println("Usage: REsearch <text filename> \n The file must be within the same directory as this class!");
        }

    }

    private void scan(String fileName) {

        try {

            String line;
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            
            deq.initialise(); // Gets rid of anything in the deq, puts a scan

            while ((line = reader.readLine()) != null) { // While we have lines to match

                // Initialise values
                Boolean finalStateReached = false;
                Boolean emptyDeque = false;
                Boolean lineFinished = false;
                pointer = 0;
                mark = 0;

                char[] characters = line.toCharArray(); // Turn the line we're looking at into a char array
                SM first = states.get(0); // Get the starting state
                push(first); // Push it onto deque

                while (!lineFinished && !finalStateReached) { // Line hasn't finished reading AND a final state has not been reached

                    while (!emptyDeque && !finalStateReached) { // In additon, deque isn't empty yet. If final state reached it can jump out

                        int justPopped = deq.get(); // Pop off a current state NUMBER
                        switch (justPopped) {
        
                            case SCAN: // It's a scan
                            emptyDeque = !deq.peek(); // See if it's the last thing left, if so, the deque is empty
                            deq.put(SCAN); // Replace the deque we just popped off
                            break;
        
                            default: 
                            SM test = states.get(justPopped); // Get the state from our list
                            if (test.character == FINAL_STATE_CHAR) { 
                                finalStateReached = true; // Jump out of the while loops, we're done
                                break;
                            } else if (test.character == BRANCH) { // To account for those dummy start states.
                                push(test);
                                break;
                            }
                            else if (test.character == characters[pointer]) {
                                push(test); // Do something with it's next states
                                pointer++; // Move to next character
                            }
                            break;
                        }

                    }

                    deq.initialise(); //Just to ensure everything is clean before we try again

                    if (mark < (characters.length - 1) && !finalStateReached) { // If we can still try again, mark is still not at end of line.
                        mark++;
                        pointer = mark;
                        emptyDeque = false; // Reset value so we can jump into a while loop again
                        push(first); // Start over, push the starting states into curr
                    } else if (mark == (characters.length - 1)) { // Don't try to increment again, it will go out of bounds
                        lineFinished = true;
                    }

                }

                //DEBUG to find out which ones matched, and which ones skipped
                //String match = (finalStateReached ? "Matched " : "Skipped ") + line; // Determine whether it matched or not
                //System.out.println(match + '\n');
                if (finalStateReached) {
                    System.out.println(line);
                }
            }
            reader.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void push(SM stateIn) {
        if (stateIn.character == BRANCH && stateIn.n1 != stateIn.n2) { // If it's of type branch (verify n1 != n2)
            deq.push(stateIn.n1);
            deq.push(stateIn.n2);
        } else if (stateIn.character == BRANCH && stateIn.n1 == stateIn.n2) {
            deq.push(stateIn.n1);
        } else {
            deq.put(stateIn.n1); // Otherwise we do a put
        }
    }


    private void importFsms() {
        
        String line;

        try {
            BufferedReader inputReader = new BufferedReader(new FileReader("testFsms.txt"));

            while((line = inputReader.readLine()) != null) { // While we can read lines
                String[] state = line.split(",");
                if (state.length == 3) {
                    SM newSM = new SM(state[0].charAt(0), Integer.parseInt(state[1]), Integer.parseInt(state[2])); // Grab the char, n1, n2 and turn it into an SM object
                    states.add(newSM); // Add it to our list
                } else {
                    throw new Exception("Each state needs to have 3 values, we're getting " + state.length + " values");
                }
            }
            inputReader.close();

            // For testing purposes (Print out all the SM's in our arrayList)
            for (int i = 0; i < states.size(); i++) {
                SM ithSM = states.get(i);
                System.out.println(ithSM.character + Integer.toString(ithSM.n1) + Integer.toString(ithSM.n2));
            } 

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error occured in importFsms method.");
        }
    }

}
