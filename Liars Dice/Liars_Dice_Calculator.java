package LiarsDice;
import java.util.Scanner;

public class Liars_Dice_Calculator {
	
	public static void main(String [] args) {
		//Scan in values for the game
		Scanner sc = new Scanner(System.in);

		System.out.print("How many people are in the game: ");
		int players = sc.nextInt();

		System.out.print("What Dice Would You Like To Predict: ");
		int diceCall = sc.nextInt();

		System.out.print("How Many Of Them Do You Believe Will Appear: ");
		int numCall = sc.nextInt();

		sc.close();
		
		//Initialise Your Dice Rolls
		String myDice = "";
		
		//Generate your Dice Rolls
		for(int i = 0; i < players; i++) {
			int dice = (int) (Math.random()*6 + 1);
			myDice += Integer.toString(dice);
		}
		
		System.out.println("You Rolled: " + myDice);
		System.out.println("Probability: " + callProbability(myDice, players, diceCall, numCall)*100 + "%");
	}
	
	public static double callProbability(String myDice, int players, int dice, int num) {
		int success = 0;
		int total = 0;
		
		//Runs Monte Carlo Simulation with your dice to calculate probability of you winning
		for(int i = 0; i < 100000; i++) {
			//Initialise Opponents Rolls
			String opponentDice = "";
			
			//Generate Opponent Dice Rolls
			for (int j = 0; j < 5*players; j++) {
					int newDice = (int) (Math.random()*6 + 1);
					opponentDice += Integer.toString(newDice);
			}
			
			String allDice = myDice + opponentDice;
			int count = 0;
			
			//Check how many times the number you guessed appears in the dice
			for (int j = 0; j < allDice.length(); j++) {
				//Special Rule: If a one is rolled, it can be counted as the number
				if(allDice.charAt(j) == (char) dice || allDice.charAt(j) == '1') {
					count++;
				}
			}
			
			System.out.println("Opponent Dice: " + allDice);
			
			//If we won, increase our wins and total games, else just add another game
			if(num > count) {
				total++;
			}
			else {
				success++;
				total++;
			}
		}

		//Return the probability of us winning with the given parameters
		double probability = (double) success/total;
		return probability;
	}
}
