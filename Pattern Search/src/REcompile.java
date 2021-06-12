import java.util.ArrayList;


public class REcompile {

    public static String p = "";
    public static String[] character;
    public static int[] n1;
    public static int[] n2;

    public static void main(String[] args) {


        p = args[0];

        //Array length is string length + start&end state
        character = new String[p.length() + 2];
        n1 = new int[p.length() + 2];
        n2 = new int[p.length() + 2];

        REcompile complie = new REcompile();

        complie.output(p);

    }


    //Global state variable, for setting up a new state
    int state = 0;


    //Receive String from terminal and set FSM
    public void output(String regex) {

        REcompile compile = new REcompile();

        int initial ;
        for (int i = 0; i < regex.length() + 2; i++) {
            initial=compile.expression();

        }
        compile.dump();
    }



    //Increment counter for regexp FSM
    int j = 0;

    //Set up a state for regexp
    public void setState(int globalstate, String ch, int nextState, int nextState2) {
        character[globalstate] = ch;
        n1[globalstate] = nextState;
        n2[globalstate] = nextState2;
    }

    //Created for testing
    public int stringLength() {
        return p.length();
    }

    //Check the character is allowed symbol
    public boolean isVocab(char ch) {
        if (ch == '+' || ch == '*' || ch == '?' || ch == '|' || ch == '[' || ch == '(') return false;
        return true;
    }

    //F -> literal or expression
    private int factor() {

        int result;

        //While state is not reaching to the end of array size, and character counter of string never reaches the end, then keep adding states
        if (state < stringLength() + 1 && j < p.length()) {

            //set start state
            if (state == 0) {
                setState(state, "Start", 1, 1);

                result = state;
                state++;//increment for the next state
                return result;

            }

            //Set a state only if we have a space in the array
            if (j < character.length + 1) {


                //Case with "\": anything comes after "\", treat as a literal how to get elements from arraylist in java
                if (p.charAt(j) == '\\') {

                    //Set a state with any character after "\"
                    setState(state, String.valueOf(p.charAt(j + 1)), state + 1, state + 1);
                    j += 2;
                    result = state;
                    state++;//increment for the next state

                    return result;
                }

                //Check if looking at a literal (concatenation)
                if (isVocab(p.charAt(j))) {

                    setState(state, String.valueOf(p.charAt(j)), state + 1, state + 1);
                    j++;
                    result = state;
                    state++;//increment for the next state

                    return result;

                } else {

                    //Check if it is an expression
                    if (String.valueOf(p.charAt(j)).equals("(")) {
                        j++;

                        result = expression();

                        if (String.valueOf(p.charAt(j)).equals(")")) {
                            j++;
                        } else {
                            System.err.println("Must have a ')' in the end");
                        }

                    } else {
                        System.err.println("not a factor");
                    }
                }

            }
        } //End of array so set final state

        //Final state
        if (j == stringLength()) {

            setState(state, "End", -1, -1);
            result = state;
            j++;
            state++;
            return result;
        }

        //No factor so return start state
        return state - 1;
    }

    //T -> F or F* or F+T
    public int term() {

        //First execution term will take will be factor, if not F? false or exception etc
        int t1 = factor();
        int result = t1;

        int f1 = state - 1;//last one just built from factor()

        if (j < p.length()) {
            if (String.valueOf(p.charAt(j)).equals("*")) {

                setState(state, "BR", state - 1, state + 1);//branching that 0 or more of t1
                j++;
                result = state;
                state++;//increment for the next state

                return result;

            } else if (String.valueOf(p.charAt(j)).equals("+")) {

                //Set branch state for "+"
                setState(state, "BR", state - 1, state + 1);
                j++;
                result = state;
                state++;//increment for the next state

                return result;

            }
            //the preceding regexp can occur zero or one time
            else if (String.valueOf(p.charAt(j)).equals("?")) {

                //Set a branch state for "?"
                setState(state, "BR", state - 1, state + 1);

                //Update preceding symbol

                if (n1[t1] == n2[t1]) {
                    //for non-branching state
                    setState(t1, character[t1], state, state);

                } else {
                    //for branching state
                    setState(t1, character[t1], n1[t1], state);

                }
                j++;
                result = state;
                state++;//increment for the next state
            }

            //A wildcard symbol that matches any literal
            else if (String.valueOf(p.charAt(j)).equals(".")) {

                setState(state, String.valueOf(p.charAt(j + 1)), state + 1, state + 1);
                j += 2;
                result = state;
                state++;//increment for the next state

            }


            //Square brackets
            else if (String.valueOf(p.charAt(j)).equals("[")) {

                if (!String.valueOf(p.charAt(j + 1)).equals("]")) {
                    String combinedLiterals = "";

                    j++;
                    while (!String.valueOf(p.charAt(j)).equals("]")) {

                        combinedLiterals = combinedLiterals + combineCh(p.charAt(j));

                        j++;
                        //Reached the end of the string
                        if (j == p.length()) {
                            System.err.println("not a regular expression: no ']'");
                            break;
                        }
                    }

                    setState(state, combinedLiterals, state + 1, state + 1);

                    j++;
                    result = state;
                    state++;

                } else {
                    System.err.println("Empty bracket");
                }

            } else if (String.valueOf(p.charAt(j)).equals("|")) {

                //Disjunction branch
                setState(state, "BR", t1, state + 1);

                j++;
                result = state;
                state++;

                int t2 = term(); //b, t2=4, t2's state is 4


                System.out.println(t1);
                //Check if preceding term is non-branching state by comparing two next states
                if (n1[t1] == n2[t1]) {

                    //Updating the preceding term's states
                    setState(t1, character[t1], state, state);

                } else {
                    //Branching so only one state to merge
                    setState(t1, character[t1], n1[t1], state);

                }

                return result;
            }
            //Factor itself
        }
        return result;
    }


    //All passed character is concatenated to form a string
    public String combineCh(char ch) {

        String stringChar = String.valueOf(ch);
        ArrayList<String> charStrings = new ArrayList<String>();

        charStrings.add(stringChar);
        String sum = "";

        for (int i = 0; i < charStrings.toArray().length; i++) {

            sum = sum + "" + charStrings.get(i);
        }

        return sum;
    }


    //find an expression (term, E)
    public int expression() {

        int result = term();//find the term first

        if (j < p.length()) {
            //check if this is an end of string, null byte
            if (p.charAt(j) == 0) {
                //Only term in the expression
                return result;

            } else {
                //Look ahead to see if there are more terms which starting with ( or literal
                if (!String.valueOf(p.charAt(j)).equals("(") || isVocab(p.charAt(j))) {
                    return result;
                } else {
                    expression();
                }
            }
        }

        return result;
    }

    //Print out all the states in the FSM
    public void dump() {
        for (int i = 0; i < character.length; i++) {
            System.out.println( i + " " + character[i] + " " + n1[i] + " " + n2[i]);
        }

    }
}