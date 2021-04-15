import java.util.Scanner;
/**
 * This class inherits the Words class and is the class for the main gameplay of the chatbot. This class uses methods to start the chatbot and generate a 
 * starting question. It then takes the users response and reads it find trigger words from the arrays in Words. Then it trims the users response
 * and generates a follow up question for the user. It then repeats that process for a number of times until the user says quit or gameplay has reached
 * an adequate number of questions.
 * @author Nathan Butler
 * @version 1.0
 * Programming Project 4 - ChatBox class
 * 4/15/2021
 * Spring 2021
 */
public class ChatBox extends Words{
	Scanner in = new Scanner(System.in);
	private String resp = "";
	private String answer = "";
	private String response = "";
	
	/**
	 * This method is the chatbox starting point. This method will start the chatbox and initiate the first question to be asked. It will then take
	 * the users responses and read the response to generate a new question to ask. 
	 */
	public void Chat() {
		int play = 0;
		while(play<4 && !resp.equals("quit")) { //quit is kill word
			startPhrase();
			resp = in.nextLine();
			resp.toLowerCase();
			resp.trim();
			if(resp.contains("."))
				resp.replace(".", "");
			int count = 0;
			while(!resp.equals("quit") && count!=10){//whichever comes first 10 plays or quit
				answer = "";
				response = "";
				followup();
				count++;
				resp = in.nextLine();
				resp.toLowerCase();
				resp.trim();			
				if(resp.contains("."))
					resp.replace(".", "");
			}//end while
			play++;
		}//end while
	}//end Chat();
	
	/**
	 * This method reads the users response and looks for any verbs from the array verbs. If there is a verb it will trim the 
	 * users response from the verb to the end of their response. If there are no verbs in the response, this method calls the 
	 * stuck method from the Words class to generate a question for the user to explain the problem more, to try to have a trigger verb.
	 */
	public void searchResponse() {
		int index = 0;
		while(index<verbs.length && answer.equals("")) {
			if(resp.contains(verbs[index])) {
				int cut = resp.indexOf(verbs[index]);
				int end = resp.length();
				answer = resp.substring(cut, end);
				index++;
			}//end if
			else if(!resp.contains(verbs[index])) {
				index++;
			}//end else if
		}//end while
		if(answer.equals("")) {//no verbs in users response
			answer = stuck(); 
		}//end if
	}//end searchResponse
	
	/**
	 * This method takes the trimmed sentence from searchResponse method and looks for positive or negative trigger words to generate a 
	 * question for the user. This method also will generate specific questions if the user uses certain words in his response; otherwise 
	 * generates a random question.
	 */
	public void followup() {
		searchResponse();
		int index = 0;
		while(index<positive.length && response.equals("")) {//while there has not been a trigger word
			if(answer.contains(positive[index])) {
				response = "That's good. ";
				response = response + response();
				response = response + answer;
				response = response + "?" ;
			}//end if
			else {
				index++;
			}//end else
		}//end while
		
		int neg = 0;
		while(neg<negative.length && response.equals("")) {//while there has not been a trigger word
			if(answer.contains(negative[neg])) {
				response = "Oh no. ";
				response = response + response();
				response = response + answer;
				response = response + "?" ;
			}// end if
			else if(!answer.contains(negative[neg])) {
				neg++;
			}//end else if	
		}//end while
		
		if(answer.contains("yes") || answer.contains("no"))//looks for yes or no to generate special questions
			response = "Why?";
		
		if(answer.contains("am ")) {
			int cut = resp.indexOf("am ") + 3;
			int end = resp.length();
			answer = resp.substring(cut, end);
			response = predicates[2];
			response = response + answer;
			response = response + "?" ;
			response=response.trim();
		}//end if
		else if(answer.contains("got ")) {//specific question for got
			int cut = resp.indexOf("got ") + 4;
			int end = resp.length();
			answer = resp.substring(cut, end);
			response = "Why did you get ";
			response = response + answer;
			response = response + "?" ;
		}//end else if
		else if(answer.contains("have ")) {//specific question for have
			int cut = resp.indexOf("have ");
			int end = resp.length();
			answer = resp.substring(cut, end);
			response = predicates[0];
			response = response + answer;
			response = response + "?" ;
			response=response.trim();
		}//end else if
		else if(answer.contains("hate ")) { //specific question for hate
			int	cut = resp.indexOf("hate ");
			int end = resp.length();
			answer = resp.substring(cut, end);
			response = predicates[0];
			response = response + answer;
			response = response + "?" ;
			response=response.trim();
		}//end else if
		else if(answer.contains(" is ")) {//specific question for is
			int	cut = resp.indexOf(" is ");
			int end = resp.length();
			answer = resp.substring(cut, end);
			response = "What";
			response = response + answer;
			response = response + " about that?" ;
		}//end else if
		else if(answer.contains("feel ")) {
			int	cut = resp.indexOf("feel ");
			int end = resp.length();
			answer = resp.substring(cut, end);
			response = predicates[0];
			response = response + answer;
			response = response + "?" ;
			response=response.trim();
		}//end else if
		if(answer.equals(stuckQ[0]) || answer.equals(stuckQ[1]) || answer.equals(stuckQ[2]) || answer.equals(stuckQ[3])) {//no verbs were used in sentence
			response = answer;
		}//end if
		if(response.equals("")) {//verbs were used but no trigger words happened
			response = response();
			response = response + answer;
			response = response + "?" ;;
		}//end if		
		if(response.contains("my ")) {//changes my to your
			response = response.replace("my ", "your ");
		}//end if	
		System.out.println(response);
	}//end followup

}//end ChatBox class
