
# Finite State Machine (FSM)
The intention of this project to builda regular expression (regexp) FSM compiler and corresponding pattern matcher.



<ins>Overview</ins>:     Implement a regexp pattern searcher using the FSM,
deque and compiler techniques. The solution consists of two programs: one called 
REcompile.java and the other called REsearch.java.

<ins>First task</ins>:     

```
The first of these must accept a regexp pattern as a command-line argument 
(enclosed within quotes—see "Note" below), and produce as standard output a description of the corresponding FSM,
such that each line of output includes four things: the state-number, the symbol to be matched or branch-stat
e indicator (or other type of indicator if you find an alternative useful), and two numbers indicating the
two possible next states. The second program must accept, as standard input, the output of the first program,
then it must execute a search for matching patterns within the text of a file whose name is given as a 
command-line argument. Each line of the text file that contains a substring that matches the regexp is
output just once, regardless of the number of times the pattern might be satisfied in that line. 
```


<ins>Regexp specification</ins>:   a wellformed regexp is specified as follows:

any symbol that does not have a special meaning (as given below) is a literal that matches itself
. is a wildcard symbol that matches any literal
adjacent regexps are concatenated to form a single regexp
* indicates closure (zero or more occurrences) on the preceding regexp
+ indicates that the preceding regexp can occur one or more times
+  '?' indicates that the preceding regexp can occur zero or one time
+ '|' is an infix alternation operator such that if r and e are regexps, then r|e is a regexp that matches one of either r or e
+ '(' and ')' may enclose a regexp to raise its precedence in the usual manner; such that if e is a regexp, then (e) is a regexp and is equivalent to e. e cannot be empty.
+  '\' is an escape character that matches nothing but indicates the symbol immediately following the backslash loses any special meaning and is to be interpretted as a literal symbol
+  square brackets '[' and ']' enclose a list of symbols of which one and only one must match (i.e. a shorthand for multi-symbol alternation); all special symbols lose their special meaning within the brackets, and if the closing square bracket is to be a literal then it must be first in the enclosed list; and the list cannot be empty.
+  operator precedence is as follows (from high to low):
+  escaped characters (i.e. symbols preceded by \)
+  parentheses (i.e. the most deeply nested regexps have the highest precedence)
+  repetition/option operators (i.e. *, + and ?)
+  concatenation
+ alternation (i.e. | and [ ])
