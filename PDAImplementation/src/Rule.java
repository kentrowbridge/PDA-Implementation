
public class Rule {
	public String currentState;
	public String nextState;
	public String input;
	public String pushValue;
	public String popValue;
	
	public Rule(String currentState, String nextState, String input, String popValue, String pushValue)
	{
		this.currentState = currentState;
		this.nextState = nextState;
		this.input = input;
		this.pushValue = pushValue;
		this.popValue = popValue;
	}
}
