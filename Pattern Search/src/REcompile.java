public class REcompile {


    // The first of these must accept a regexp pattern as a command-line argument
    // (enclosed within quotes—see "Note" below), and produce as standard output a description of the corresponding FSM,
    // such that each line of output includes four things: the state-number,
    // the symbol to be matched or branch-state indicator (or other type of indicator if you find an alternative useful),
    // and two numbers indicating the two possible next states.

    //String p = "a*bB*"; //globally accessible variable
    //String p = "ab*a+cd"; //globally accessible variable

    String p = "ab"; //globally accessible variable
    //String p = "\\aabb"; //globally accessible variable

    //Global state variable, for setting up a new state
    int state= 0;
    int finalState;
    String alternation="+";
    String closure="()";
    String concatenation = "*";


    int lengthOfString = p.length();
    //Character array
    char [] character = new char[lengthOfString+2];
    //next state arrays
    int [] n1 = new int[lengthOfString+2];
    int [] n2 = new int [lengthOfString+2];

    //Increment counter for regexp FSM
    int j =0;

    //Set up a state for regexp
    public void setState(int globalstate,char ch, int nextState, int nextState2){
        character[globalstate]=ch;
        n1[globalstate]=nextState;
        n2[globalstate]=nextState2;
    }

    //Created for testing
    public int stringLength(){
        return p.length();
    }

    //Check the character is allowed symbol
    public boolean isVocab(char ch){
        if( ch == '+' || ch == '*' ||ch=='?'||ch=='|' )return false;
        return true;
    }

    //F -> literal or expression
    private int factor() {

        int result;

        //set
        if(state==0){
            setState(state,'`',1,1);

            //@Testing
            System.out.println("Start state: "+state+", ch= "+ character[state]+ ", n1: "+n1[state]+", n2: "+n2[state]);

            result=state; state++;
            return result;

        }

        //Set a state only if we have a space in the array
        if (j < character.length+1) {

            //Case with "\": anything comes after "\", treat as a literal
            if (p.charAt(j) == '\\') {

                //Set a state with any character after "\"
                setState(state, p.charAt(j+1), state + 2, state + 2);
                //@Testing
                System.out.println("Current state with '\\': "+state+", ch= "+ character[state]+ ", n1: "+n1[state]+", n2: "+n2[state]);
                //Increment since a state has been created
                j+=2; result = state; state++;

                return result;
            }

            //Check if looking at a literal (concatenation)
            if (isVocab(p.charAt(j))) {



                setState(state, p.charAt(j), state+1, state+1);
                //@Testing
                System.out.println("Current state adding normal literal: "+state+", ch= "+ character[state]+ ", n1: "+n1[state]+", n2: "+n2[state]);
                //Increment since a state has been created
                j++; result = state; state++;


                //End of array
                if (state==stringLength()+1) {
                    //Final state to end
                    setState(state, 'F', -1, -1);
                    //@Testing
                    System.out.println("End state: "+state+", ch= "+ character[state]+ ", n1: "+n1[state]+", n2: "+n2[state]);
                    result=state;
                    return result;

                }
                return result;

            } /*else {

                //Check if it is an expression
                if (p.charAt(j) == '(') {
                    j++;

                    //@@NEED MODIFICATION
                    //result=expression();

                    if (p.charAt(j) == ')') {
                        j++;
                    } else {
                        System.out.println("Must have a ')' in the end");
                    }

                } else {
                    System.out.println("not a factor");
                }
            }*/

        }
            return state;//No factor so return start state

    }




//T -> F or F* or F+T
public int term(){

    //First execution term will take will be factor, if not F? false or exception etc
    int t1= factor();
    int result=t1;
    int f1= state-1;//last one just built from factor()

  /*  if(p.charAt(j)=='*'){

            setState(state,'`',state-1,state+1);//branching that 0 or more of t1
            System.out.println("Current state with closure symbol: "+state+", ch= "+ character[state]+ ", n1: "+n1[state]+", n2: "+n2[state]);

            j++; result= state; state++;//increment for the next state
            return result;

        }*/

        /*
        if(character[j]=='+'){
            int t2=term();
            setState(state,'`',t1,t2);
            result=state;
            j++;

            //Check if preceding term is non-branching state by comparing two next states
            if(n1[f1]==n2[f1]){
                //Updating the preceding term's states
                setState(f1,character[f1],state,state);
                state++;
            }else {
                //Branching so only one state to merge
                setState(f1,character[f1],n1[f1],state);
                state++;
            }

            return result;
        }*/
        //Factor itself

    return result;
}


    //find an expression (term, E)
/*    public int expression(){

    int result =term();//find the term first

    //check if this is an end of string, null byte
    if(character[j]==0){
        //Only term in the expression
        return

    }else{
        //Look ahead to see if there are more terms which starting with ( or literal
        if(character[j]!='('||isVocab(character[j])){
            return
        }else {
            expression();
        }
    }
    return
}*/


}