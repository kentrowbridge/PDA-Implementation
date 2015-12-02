# PDA-Implementation
Push Down Automata implementation done  for theory of computation at University of Portland

## How to format input.txt to reflect the PDA you want to check. 

- **First Line** - state alphabet. List of states separated by commas with no spaces.  
  * ex: q0,q1,q2,q3,q4  
- **Second Line** - alphabet. List of transition characters 
  * ex: a,b,c  
- **Third Line** - stack alphabet. List of all characters used in the stack  
  * ex: x,y,$  
- **Fourth Line** - the number,n ,of transition rules for the PDA the following n lines are the rules for transitions between states in the PDA   
- **Next n Lines** - transition rules for PDA 
   Format of a rule - <current state>,<end state>,<transition character>,<value to pop>,<value to push>  
   NOTE - the program does not handle epsilon transitions from a state to itself  
       - epsilon is represented by the empty string  
        ex. q0,q1,a,, represents a transition from q0 to q1 after reading an a with a pop and push of epsilon  
- **Second to Last Line** - start state  
- **Last Line** - list of accept states  
  * ex. q0,q3  
  
For more assistance see the example file in the PDAImplementation project.
  
