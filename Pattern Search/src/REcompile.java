public class REcompile {


    // The first of these must accept a regexp pattern as a command-line argument
    // (enclosed within quotes—see "Note" below), and produce as standard output a description of the corresponding FSM,
    // such that each line of output includes four things: the state-number,
    // the symbol to be matched or branch-state indicator (or other type of indicator if you find an alternative useful),
    // and two numbers indicating the two possible next states.

    String p = "ab*a+cd"; //globally accessible variable


    //Global state variable, for setting up a new state
    int state= 0;
    String alternation="+";
    String closure="()";
    String concatenation = "*";

    //Character array
    char [] character = new char[p.length()];
    //next state arrays
    int [] n1 = new int[p.length()];
    int [] n2 = new int [p.length()];

    //Increment counter for regexp FSM
    int j =0;


    //Set up a state for regexp
    public void setState(int globalstate,char ch, int nextState, int nextState2){
        character[globalstate]=ch;
        n1[globalstate]=nextState;
        n2[globalstate]=nextState2;
    }

    //Created for testing
    public char[] setArray(String st){
        char[] ch= new char[st.length()];
        for (int i =0; i<st.length();i++){
            ch[i]=st.charAt(i);
        }
        return ch;
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

//T -> F or F* or F+T
public int term(){

    //First execution term will take will be factor, if not F? false or exception etc
    int t1= factor();
    int result= factor();
    int f1= state-1;//last one just built from factor()

        if(character[j]=='*'){
            setState(state,'`',t1,state+1);//branching that 0 or more of t1
            j++;
            result= state;//return this state
            state++;//increment for the next state

        }
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
        }
        //Factor itself
        return result;

}


//F -> literal or expression
    private int factor() {

        int result;
    //Check if looking at literals
    if (isVocab(character[j])){
        setState(state,character[j],n1[j],n2[j]);
        j++;
        result=state;
        state++;
        return result;
    }else {

        //Check if it is an expression
        if(character[j]=='('){
            j++;
            //result=expression();
            if (character[j]==')'){
                j++;
            }else {
                System.err.println("Must have a ')' in the end");
            }
        }else {
            System.err.println("not a factor");
        }

    }return state;//No factor so return start state
    }


    //Check the character is allowed symbol
    public boolean isVocab(char ch){
    if( ch == '+' || ch == '*' ||ch=='?'||ch=='|' )return false;
    return true;

}




//    public static void main(String[] args) {
//
//
//        String st = "ab*a+cd";
//        String [] p=new String[9];
//        for (int i=0; i < p.length; i++)
//
//            System.out.println("Char " + i + " is " + st.charAt(i));
//    }


}