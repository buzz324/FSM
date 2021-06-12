import java.io.*;
import java.util.*;

public class REsearch {
    // The second program must accept, as standard input, the output of the first program,
    // then it must execute a search for matching patterns within the text of a file whose name is given as a command-line argument.
    // Each line of the text file that contains a substring that matches the regexp is output just once,
    // regardless of the number of times the pattern might be satisfied in that line.
    // (Note also we are just interested in searching text files.)
    // Output the whole line if there's a match. 

    // Global variables
    int mark = 0;
    int pointer = 0;
    char BRANCH = '#';
    char START = '@';
    char FINAL_STATE_CHAR = '$';
    int SCAN = -1;
    char NOTHING_LEFT = '%';

    Boolean lineFinished = false;
    Deque deq = new Deque();
    ArrayList<SM> FSMs = new ArrayList<>();

    public static void main (String [] args) {

        REsearch search = new REsearch();
        search.getAllFsms();

        /*try {
            if (args[0] != null && args[0] != "") {
                search.read(args[0]); // Pass our filename argument
            } else {
                System.err.println("Usage: REsearch <text filename> \n The file must be within the same directory as this class!");
            }
        } catch (Exception ex) {
            System.err.println("Usage: REsearch <text filename> \n The file must be within the same directory as this class!");
        } */

        //DEBUG
        search.read("");
    }

    private void read(String fileName) {

        

        try {
            // Open the file, using the filename set.
            BufferedReader reader = new BufferedReader(new FileReader("test.txt"));
            String line;

            try{
                deq.push(SCAN);
                while ((line = reader.readLine()) != null) {
                    System.out.println("A new line: " + line);
                    char[] characters = line.toCharArray();
                    SM temp = FSMs.get(0); // Grab our start state
                    
                    // Run our first push, get the starting state on our deque
                    pusher(temp);

                    Boolean matched = evaluator(characters);
                    while(!matched) {
                        if (lineFinished && pointer >= characters.length - 1) {
                            mark++;
                            pointer = mark;
                            lineFinished = false;
                        }

                        if (mark >= characters.length && characters.length != 1) {
                            break;
                        }

                        matched = evaluator(characters);

                    }
                    if (matched) {
                        System.out.println("Matched: " + line);
                    }

                    pointer = 0;
                    mark = 0;
                }


            } catch (Exception ex) {
                ex.printStackTrace();
            }

            reader.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
   
    }

    private void pusher(SM stateIn) {

        switch (stateIn.character) {
            case '@': 
            deq.push(stateIn.n1);
            break;
            case '#':
            deq.push(stateIn.n1);
            deq.push(stateIn.n2);
            break;
            default: 
            deq.put(stateIn.n1);
            break;
        }
    }

    private Boolean evaluator(char[] characters) {
        int inNodeNum = deq.get();        
        if (inNodeNum == SCAN && deq.peek()) {
            deq.put(SCAN);
            return false;
        } else {
            if (inNodeNum == SCAN && !deq.peek()) {
                return false;
            }
            SM evaluating = FSMs.get(inNodeNum);
            switch (evaluating.character) {
                case '#':
                // Branch state, so we want to put on our new current states
                pusher(evaluating);
                //index = Character.getNumericValue(deq.get());
                //evaluating = FSMs.get(index);
                return false;

                case '$':
                return true;
    
                default:
                // Matching state, so we want 
                if (evaluating.character == characters[pointer]) {
                    pusher(evaluating);
                    if (pointer <= characters.length - 1) {
                        if (characters[pointer] == characters[characters.length - 1]) {
                            lineFinished = true;
                        }
                        pointer++; // moves to next character IF we've got any more to go
                        // Is our line finished? Aka we're on the last char on the array

                    }
                }

                return false;
            }

        }




    }

    private void getAllFsms() {
        // Get all of our states from standard input. Store it in a bunch of global arrays
        // Each state is represented by a SM object. We have an array of these globally.
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
        
        String line;

        try {
            inputReader = new BufferedReader(new FileReader("testFsms.txt"));
            while((line = inputReader.readLine()) != null) {
                String[] obj = line.split(",");
                if (obj.length == 3) {
                    SM newSM = new SM(obj[0].charAt(0), Integer.parseInt(obj[1]), Integer.parseInt(obj[2]));
                    FSMs.add(newSM);
                } else {
                    throw new Exception("Each state needs to have 3 values, we're getting " + obj.length + " values");
                }
            }

            // For testing purposes (Print out all the SM's in our arrayList)
            /*for (int i = 0; i < FSMs.size(); i++) {
                SM ithSM = FSMs.get(i);
                System.out.println(ithSM.character + Integer.toString(ithSM.n1) + Integer.toString(ithSM.n2));
            } */

        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }
}
