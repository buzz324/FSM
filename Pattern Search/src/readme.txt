Name and Student IDs:
Buzz Kho: 1174261
Zachary Cui: 1364880

Notes about this assignment:

UPDATE: Assignment was updated on 14/06, ADDED REsearch2. This fixes the problems in REsearch. 
- Use by piping in states, and providing text comparison file as argument. 
- A tested example is found in 'testFsms.txt'
- Due to inconsistancies in REcompile, it is NOT recommended to pass output of REcomplie into REsearch.
- I suggest evaluating these indvidually. Call REcompile by "java REcompile "regex here" ". It will output the states
- Call REsearch2 by piping in states, and giving test file as parameter, i.e. "cat test.txt | java REsearch2 testFsms.txt"
- The states provided in testFsms represent (ab)|c
- The start state is inferred by being the 1st line, just use # for branch. $ represents final state character. So these cannot be matching characters.

For compile, single expressions are working (incl. all operators).
Start states doesn't start where it's supposed to be, which may result in:
- Complex expressions not working properly. 

For String search, the following issues exist (IN REsearch.java ONLY, NOT REsearch2.java):
- Ability to search basic concat strings, however last string always ends in failure
- General inconsistent performance, particually when length of comparison strings is very short (i.e. < 2). 

For testing, it is suggested that the REsearch and REcomplile components are tested indvidually. 
The REsearch component can be tested by simply calling 'java REsearch'. It uses the test.txt for its tests, and testFsms.txt for its states.