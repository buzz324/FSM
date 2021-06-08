public class REcompile {


    // The first of these must accept a regexp pattern as a command-line argument
    // (enclosed within quotes—see "Note" below), and produce as standard output a description of the corresponding FSM,
    // such that each line of output includes four things: the state-number,
    // the symbol to be matched or branch-state indicator (or other type of indicator if you find an alternative useful),
    // and two numbers indicating the two possible next states.

    String p = "ab*a+cd"; //globally accessible variable


    String alternation="+";
    String closure="()";
    String concatenation = "*";
    char []ch = setArray(p);

    public char[] setArray(String st){
        char[] ch= new char[st.length()];
        for (int i =0; i<st.length();i++){
            ch[i]=st.charAt(i);
        }
        return ch;
    }

    int j =0;

    //find an expression (term, E)

    public boolean expression(){

    term();//find the term first

    //check if this is an end of string, null byte
    if(ch[j]==0){
        //Only term in the expression
        return true;

    }else{
        //Look ahead to see if there are more terms which starting with ( or literal
        if(ch[j]!='('||isVocab(ch[j])){
            return false;
        }else {
            expression();
        }
    }
}

//T -> F or F* or F+T
public boolean term(){

    //First execution term will take will be factor, if not F? false or exception etc
    if (factor()){
        if(ch[j]=='*'){
            j++;
            return true;
        }
        if(ch[j]=='+'){
            j++;
            if(term()){

                return true;
            }else {return false;}

        }
    }
    return false;
}


//F -> literal or expression
    private boolean factor() {


    //Check if looking at literals
    if (isVocab(ch[j])){
        j++;
        return true;
    }else {

        //Check if it is an expression
        if(ch[j]=='('){
            j++;
            //expression();@@@@@@@@@@
        }else {return false;}
        if (ch[j]==')'){
            j++;
            //return true, success

            return true;
        }
    }return false;
    }


    //Check the character is allowed symbol
    public boolean isVocab(char ch){
    if( ch == '+' || ch == '*' )return false;
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