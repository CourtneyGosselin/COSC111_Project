package project5;
import P5.Game;
/* This file includes:
 * 	1. Solution to P3
 *  2. Questions for P4. Comments starting with REQ represent the questions.
 *  
 * Features:
 * 	- We have from 1 to 3 players
 *  - We have many questions. Each player may be asked more than one question.
 *  - User can play many rounds of the game. 
 * 
 * Focus: 
 * 	- Arrays and Methods
 * 
 * Aim:
 * 	- Organize code and avoid code redundancy by the use of methods
 */
/*Features:
	  - We have from 1 to 3 players
	  - We have many questions grouped in 3 categories . Each player chooses a category before being asked. 
	  - Each player may be asked more than one question from different categories. 
	  - User can play many rounds of the game. 
	 
	 Focus: 
	 	- 2-D arrays 
	 
	 Aim:
	 	- Build more complex programs that involves the use of 2-D arrays

	 REQ (for bonus marks):
	 You should build on your code from part P4 and satisfy the following requirements:
	   1.  Create three categories of questions in a 2D array (rows=categories, col=questions). 
	        in each player's turn, ask the player about the required category and then ask a question from that category.
	        make all necessary changes to your program (e.g. shuffle method should now work for 2D array)
	   2.  If we are out of questions in a category (i.e., all questions in this category were offered and answered correctly), inform the player to choose another category.  
*/

public class P5 {				
	static Game game;			
	
	//Two arrays for questions and answers (both are global, i.e., accessible by all code in the class).
	//REQ1:	Replace array values with real questions/answers
	static String[][] questions ={{"Is a wendigo a supernatural and cannabilistic? (Yes/No) ", "Does a djinn poison its prey by physical or airborne contact?", "Is Prometheus a Greek Titan or an angel?", "What is the first action used to kill a ghost?", "What is a banshee's vulnerability?", "What is a skinwalkers vulnerability?", "Where did the idea of a water wraith originate?", "What is a rawheads power?", "Does iron kill or harm ghosts?"},
								  {"How old was Dr. Spencer Reid when he went to university? ", "How many seasons of Criminal Minds are there as of September 2016?", "What year did Criminal Minds air?", "What is the job title of the main characters in Criminal Minds?", "Was Dr. Spencer Reid a drug addict (Yes/No)?", "Does Dr. Spencer Reids Mom have schizophrenia (Yes/No)?", "Does Dr. Spencer Reid prefer an automatic or revolver?", "How old was Dr. Spencer Reid when he joined the BAU?","Was Penelope Garcia a criminal before joining the team (Yes/No)?"},
								  {"Is digiti mini a mucle in your hand or arm?", "For the acronym PRICE what does the 'E' stand for?", "Can the pelvic floor be flexed by going into a bridge? (Yes/No)", "What is the acronym used to detect if someone is having a stroke?", "Is there a difference between aspbergers and HFA? (Yes/No)", "Is the humerus a long bone? (Yes/No)", "Is the zygomatic arch a bone in your face or foot?", "Is anemia an iron deficiency? (Yes/No)", "What bone has the boney landmark called teh tibial tuberosity?"}};

	
	static String[][] answers = {{"Yes", "Physical", "Greek Titan", "Salt and burn", "Gold blade", "Silver", "Scotland", "Superior strength", "Harm"},
								 {"Twelve", "Twelve", "2005", "BAU Special Agent", "Yes", "Yes", "Revolver", "Twenty-two", "Yes"},
								 {"Hand", "Elevate", "Yes", "FAST", "No", "Yes", "Face", "Yes", "Tibia"}
	};
	public static void shuffleQuestions(String[][] q, String[][] a){
		for(int r =0; r< 3; r++){
			for(int s =0; s< q[r].length; s++){
				int randomrow = (int)(Math.random()*q[r].length);
				String temp = q[r][s];
				String temp2 = a[r][s];
				a[r][s]= a[r][randomrow];
				a[r][randomrow] = temp2;
				q[r][s]= q[r][randomrow];
				q[r][randomrow] = temp;
				
			}
		}
		
	}
	

		

	public static void main(String[] args) {
		int numPlayers = 0;
		String ans;
		int numQuestions = 0;
		int catagory;
		int supernatural =0;
		int criminalminds =0;
		int medical =0;
		do{								
			//Reset the game
			game = new Game();			
			
			//Get number of players (from 1 to 3)
			numPlayers = game.askForInt("How many players", 1, 3);

			//Add up to 3 players to the game
			for (int i = 0; i < numPlayers; i++) {
				String name = game.askForText("What is player " + i + " name?");
				game.addPlayer(name);				
			}
			
		//REQ2:	Call a method to shuffle questions and answers. For that, you need to create a method with the header: void shuffleQuestions();
			shuffleQuestions(questions, answers);

				
			//REQ3:	- Calculate the maximum number of questions each player could be asked (equals to the number of available questions divided by numPlayers). Store this value in a variable maxQuestions
			int maxQuestions = (27/numPlayers);
			//	- Ask the user how many questions should be given to each player. The value read from the user should not exceed MaxQuestion. Store this value in a variable numQuestions.
			numQuestions = game.askForInt("How many questions should be given to each player?", 1, maxQuestions);
			//		- Ask each player the next unanswered question (e.g., player 0 gets the first question. 
			//		  If it is answered correctly, then player1 gets the next question in the array, otherwise player1 
			//        gets the same question again, and so on). 
			// 		  Assume that an incorrectly answered question will keep popping up until it is correctly answered.
			//		  Hint: you need to create a for loop that repeats the below code block numQuestions times.
			//		  Hint: you need to have a variable that keeps track of the next question to be offered. 
			int k = 0;
			int t = 0;
			int a = 0;
			int b = 0;
			int c = 0;
			int row = 0;
			game.setCurrentPlayer(k);
			for (int i = 0; i < (numQuestions * numPlayers); i++) {
				t = 0;
				game.setCurrentPlayer(i % numPlayers);
					while (t == 0) {
					int category = game.askForInt("Pick a Category: 0:supernatural, 1:criminal minds, 2:medical",0,2);
					if (category==0){row = a; a++;}
					if (category==1){row = b; b++;}
					if (category==2){row = c; c++;}
					if (a<=questions[0].length && b<=questions[0].length && c<=questions[0].length){
					String ans1 = game.askForText(questions[category][row]);
					if (ans1.equals(answers[category][row])) {
						game.correct(); // display "Correct", increment score,
										// change frame color to green
						t++;
					} else {
						game.incorrect();
					t++;
					} // display "incorrect", change frame color of player to
						// red
					
					game.setCurrentPlayer(k);}
					else {game.print("No more questions in this Category please select another (Press enter to continue)");
				if (a>questions[0].length)a--;
				if (b>questions[0].length)b--;
				if (c>questions[0].length)c--;
					}}
			}
			
			//Do you want to play again? make sure you get valid input
			ans = game.askForText("Play again? (Y/N)"); 
			while(ans != null && !ans.toUpperCase().equals("Y") && !ans.toUpperCase().equals("N"))
				ans = game.askForText("Invalid input. Play again? (Y/N)");
		}while(ans.toUpperCase().equals("Y"));	//play again if the user answers "Y" or "y"

		System.exit(1); 	//This statement terminates the program
		
	}
}
