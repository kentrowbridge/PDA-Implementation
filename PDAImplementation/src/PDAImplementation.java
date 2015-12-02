import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class PDAImplementation {
	
	private static String[] states;
	private static List<String> alphabet = new ArrayList<String>();
	private static List<String> stackAlphabet = new ArrayList<String>();
	private static int numRules;
	private static Rule[] rules;
	private static String startState;
	private static List<String> acceptStates = new ArrayList<String>();
	
	private static String split = ",";
	
	public static void main(String args[])
	{
		////////////// Read PDA description from file //////////////
		try {
			FileReader fr = new FileReader("input.txt");
			BufferedReader br = new BufferedReader(fr);
			
			//save list of states
			states = br.readLine().split(split);
			
			//save the alphabet
			String[] temp = br.readLine().split(split);
			for(int i = 0; i < temp.length; i++)
				alphabet.add(temp[i]);
			
			//save the stack alphabet
			temp = br.readLine().split(split);
			for(int i = 0; i < temp.length; i++)
				stackAlphabet.add(temp[i]);
			
			//read the number of rules
			numRules = Integer.parseInt(br.readLine());
			
			rules = new Rule[numRules];
			
			//save the rules
			for(int i = 0; i < numRules; i++)
			{
				String[] rule = br.readLine().split(split, -1);						
				/*
				 * Index values
				 * [0] - current state
				 * [1] - state we will move to
				 * [2] - input that is read
				 * [3] - value to push to the stack
				 * [4] - value to pop from the stack
				 */
				Rule newRule = new Rule(rule[0], rule[1], rule[2], rule[3], rule[4]);
				rules[i] = newRule;
			}
			
			//save start state
			startState = br.readLine();
			
			//save accept states
			temp = br.readLine().split(split);
			for(int i = 0; i < temp.length; i++)
				acceptStates.add(temp[i]);
			
			br.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File not found.");
		}
		catch(IOException e)
		{
			System.out.println("IO Exception");
			e.printStackTrace();
		}
		
		////////////// Read input string from prompt //////////////
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter the string to process.");
		while(sc.hasNextLine())
		{
			Stack stack = new Stack();
			System.out.println(evaluateString(sc.nextLine(), startState, stack));
			
		}
		sc.close();
	}

	/**
	 * evaluateString()
	 * Run the string on the PDA given from the input file
	 * @param str - string to evaluate
	 * @param currentState - the state the PDA is currently in
	 * @param stack - the stack in the current computation path
	 * @return T/F if that string is accepted by the PDA
	 */
	private static boolean evaluateString(String str, String currentState, Stack stack) {
		
		//Base Case: the string has been completely processed.
		if(str.equals("") && acceptStates.contains(currentState))
		{
			return true;
		}
		
		//Read the first character of the string and process it
		String input = "";
		String newStr = "";
		if(!str.isEmpty())
		{
			input = str.substring(0, 1);
			newStr = str.substring(1);
		}
		
		
		for(Rule rule : rules)
		{
			//Check the rule is valid for the current configuration
			if(!rule.currentState.equals(currentState))
			{
				continue;
			}
			if(!(rule.input.equals("") || rule.input.equals(input)))
			{
				continue;
			}
			if(stack.empty())
			{
				if(!rule.popValue.equals(""))
				{
					continue;
				}
			}
			else if(!rule.popValue.equals("") && !rule.popValue.equals(stack.peek()))
			{
				continue;
			}
			
			//Create computations for each valid rule
			Stack newStack = (Stack) stack.clone();
			if(!rule.popValue.equals(""))
			{
				newStack.pop();
			}
			if(!rule.pushValue.equals(""))
			{
				newStack.push(rule.pushValue);
			}
			String newState = rule.nextState;
			boolean accepts = false;
			if(rule.input.equals(""))
			{
				accepts = evaluateString(str, newState, newStack);
			}
			else
			{
				accepts = evaluateString(newStr, newState, newStack);
			}
			if(accepts)
			{
				return true;
			}
		}
		
		return false;
	}
}
