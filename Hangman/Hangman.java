import java.util.*;

public class Hangman {
	public static void main(String[] args) {
		//Create Both Scanner, Random Generator & File Reading Objects
		FileIO reader = new FileIO();
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		
		//Load in "Dictionary.txt" with all English words
		String[] inputs = reader.load("./dictionary.txt");
		
		//Chooses a random word for Game
        int index = random.nextInt(216555);
        String currentWord = inputs[index];
		
		//Initialise Variables
        int attempts = 10;
        int correct = 0;
        
        char [] blank = new char[currentWord.length()];
		
		//While we still have attempts, scan and check for a letter
        while(attempts != 0) {
        	char guess = sc.nextLine().charAt(0);
        	
        	//If the character is in the array, say so and replace it in array
        	if(currentWord.indexOf(guess) >= 0) {
        		System.out.println("It's in the word");
        		for(int i = 0; i < currentWord.length(); i++) {
        			if(currentWord.charAt(i) == guess) {
        				blank[i] = guess;
        			}
        		}
        		correct++;
        		System.out.println(Arrays.toString(blank));
        	}
        	//Otherwise use reduce the attempts allowed
        	else {
        		System.out.println("Not in the word");
        		attempts--;
        		System.out.println(Arrays.toString(blank));
        	}
        	//If they have all letters, win and break from the loop.
        	if(correct == currentWord.length()) {
        		System.out.println("YOU WIN! The word was " + currentWord);
        		break;
        	}
        	if(attempts == 0) {
        		System.out.println("YOU LOSE! The word was " + currentWord);
        		break;
        	}
        }
        sc.close();
	}
}

