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

    //check if this is an end of string
    if(p[j]==null){
        //return great
    }

    //Check if the expression is looking at ( or literal after term ----look ahead
    if(p[j]!="("&&isVocab(p[j])){

        //return bad news

    }
    expression();

}

//T -> F or F* or F+T
public void term(){

    //First execution term will take will be factor, if not F? false or exception etc
    if (factor()){
        if(p[j]=="*"){
            j++;
            //return true/success
        }
        if(p[j]=="+"){
            j++;
            if(term()){

            }
                //true return
        }
    }
}


//F -> literal or expression
    private void factor() {

    //Check if looking at literals
    if (isVocab(p[j])){
        j++;
    }else {

        //Check if it is an expression
        if(p[j]=="("){
            j++;
            expression();
        }
        if (p[j]==")"){
            j++;
            //return true, success

        }
    }
    }

    //Check the character is allowed symbol
public boolean isVocab(char c){
    return false;//if open bracket
}




    public static void main(String[] args) {


        String st = "ab*a+cd";
        String [] p=new String[9];
        for (int i=0; i < p.length; i++)

            System.out.println("Char " + i + " is " + st.charAt(i));
    }


}