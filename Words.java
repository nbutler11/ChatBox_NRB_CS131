import java.util.Random;
/**
 * This class is where all of the word arrays for the code is. There are arrays for questions to ask and arrays for trigger words for the chat box.
 * The methods in this class generate random questions for the chat box when there are responses.
 * @author Nathan Butler
 * @version 1.0
 * Programming Project 4 - Words class
 * 4/15/2021
 * Spring 2021
 */
public class Words {
	public String[] positive = {"good", "great", "happy", "joy", "fine", "amazing", "awesome", };
	public String[] negative = {"bad", "terrible", "not", "unhappy", "sad", "hate", "dislike", "fired", };
	public String[] verbs = {"feel ", "feeling ", "doing ", "am ", "got ", "do ", "going ", "has ", "are ", " is ", 
			"hate ", "dislike ", "love ", "makes ", "need ", "thought ", "have ", "was ", "like ", "can ", "cannot ",};
	public String[] startQuestion = {"How are you feeling?", "How has your day been?" , "What seems to be the problem?", 
			"How is work going?", "What is the issue?", "How can I help you?", };
	public String[] predicates = {"Why do you ", "What makes you ", "Why are you ", };	
	public String[] stuckQ = {"Can you explain more?", "Sorry I do not understand. Tell me more.", "Can you elaborate?", "How did that happen?", };
	
	/**
	 * This method randomly generates a starting question from the array startQuestion
	 */
	public void startPhrase() {
		Random gen = new Random();
		int rand = gen.nextInt(startQuestion.length);
		System.out.println(startQuestion[rand] + " (Type \"quit\" to end)");
	}//end startPhrase
	
	/**
	 * This method randomly generates a response question from the array predicates
	 * @return random question from predicates array
	 */
	public String response() {
		Random gen = new Random();
		int rand = gen.nextInt(predicates.length);
		return predicates[rand];
	}//end response
	
	/**
	 * This method randomly generates a question from the stuckQ array. This method should be used when the code cannot come up with
	 * a response from predicates array
	 * @return random question from stuckQ array
	 */
	public String stuck() {
		Random gen = new Random();
		int rand = gen.nextInt(stuckQ.length);
		return stuckQ[rand];
	}//end stuck
	
}//end Words class
