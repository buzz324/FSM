import java.io.*;
import java.util.List;

public class REsearch {
    //The second program must accept, as standard input, the output of the first program,
    // then it must execute a search for matching patterns within the text of a file whose name is given as a command-line argument.
    // Each line of the text file that contains a substring that matches the regexp is output just once,
    // regardless of the number of times the pattern might be satisfied in that line.
    // (Note also we are just interested in searching text files.)

    public class SM {
        char character;
        int n1;
        int n2;

        public SM (char character, int a, int b) {
            this.character = character;
            this.n1 = a;
            this.n2 = b;
        }
    }
    public static void main (String [] args) {

        REsearch search = new REsearch();

        try {
            if (args[0] != null && args[0] != "") {
                search.read(args[0]); // Pass our filename argument
            } else {
                System.err.println("Usage: REsearch <text filename> \n The file must be within the same directory as this class!");
            }
        } catch (Exception ex) {
            System.err.println("Usage: REsearch <text filename> \n The file must be within the same directory as this class!");
        }

    }

    private void read(String fileName) {

        try {
            // Open the file, using the filename set.
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;

            try{

                while ((line = reader.readLine()) != null) {

                    // Run through the FSM

                    // If it matches, we print out the line

                    // If not, we skip
                }


            } catch (Exception ex) {
                ex.printStackTrace();
            }
            


            reader.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
   
    }

    private void getFSM() {
        // Get all of our states from standard input. Store it in a bunch of global arrays
        // Each state is represented by a SM object. We have an array of these globally.
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
        String line;

        try {
            while((line = inputReader.readLine()) != null) {
                String[] obj = line.split(",");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        // Not sure about the format of the output: is it like this?:
        // <character, state1, state2> 

    }
}
