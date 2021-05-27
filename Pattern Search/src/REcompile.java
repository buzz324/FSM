public class REcompile {


    // The first of these must accept a regexp pattern as a command-line argument
    // (enclosed within quotes—see "Note" below), and produce as standard output a description of the corresponding FSM,
    // such that each line of output includes four things: the state-number,
    // the symbol to be matched or branch-state indicator (or other type of indicator if you find an alternative useful),
    // and two numbers indicating the two possible next states.

    String p = "ab*a+cd"; //globally accessible variable
    int j =0;

    //find an expression (term, E)
public void expression(){

    term();//find the term first

}

//Find a tern
public void term(){

    //find a factor
    factor();
}

    private void factor() {
    if (isVocab(p[j])){
        j++;
    }else {

    }
    }

    //Check the character is allowed symbol
public boolean isVocab(char c){
    return false;//if open bracket
}
}
