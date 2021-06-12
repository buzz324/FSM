Name and Student IDs:
Buzz Kho: 1174261
Zachary Cui: 1364880

Notes about this assignment:

Start states doesn't start where it's supposed to be, which may result in:
- Complex expressions not working properly. 

For String search, the following issues exist:
- Ability to search basic concat strings, however last string always ends in failure
- General inconsistent performance, particually when length of comparison strings is very short (i.e. < 2). 

For testing, it is suggested that the REsearch and REcomplile components are tested indvidually. 
The REsearch component can be tested by simply calling 'java REsearch'. It uses the test.txt for its tests, and testFsms.txt for its states.