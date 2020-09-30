
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class Liars_Dice_Solver {
	
	public static void main(String [] args) {
		Scanner sc = new Scanner(System.in);
		
		LocalDateTime time = LocalDateTime.now();
		
		//Scans In Number Of Players
		System.out.print("How many people are in the game: ");
		int players = sc.nextInt();
		sc.close();
		
		//Sets Your Dice Up
		String myDice = "63515";
		
		//Sets up values for next function
		double overallBest = 100;
		int bestDice = 0;
		int bestGuess = 0;
		
		//Runs a Monte Carlo SImulation and picks best odds
		for (int i = 1; i <= 6; i++) {
			for (int j = 1; j <= 5*players; j++) {
				double current = callProbability(myDice, players, i, j);
				//If next probability is better than last and still above 50%, set it
				if(overallBest > current && current > .50) {
					bestDice = i;
					bestGuess = j;
					overallBest = current;
				}
				//If the probabilities get to low, break out of loop
				else if(current < .5) {
					break;
				}
				System.out.println("Dice Face " + i + " with " + j + " guesses = " + current);
			}
		}
		
		long seconds = ChronoUnit.SECONDS.between(time, LocalDateTime.now());
		System.out.println("TIME TAKEN: " + seconds);
		
		//Prints Best Guess and Your Odds
		System.out.println("Your Best Call Is: Dice " + bestDice + " & Guess " + bestGuess + " at " + overallBest);
	}
	
	public static double callProbability(String myDice, int players, int dice, int num) {
		//Sets up variables for Probability
		int success = 0;
		int total = 0;
		
		//Guesses 100000 different ways opponents dice may be
		for(int i = 0; i < 40000; i++) {
			String opponentDice = "";
			
			//Creates all opponents dice
			for (int j = 0; j < 5*players; j++) {
					int newDice = (int) (Math.random()*6 + 1);
					opponentDice += Integer.toString(newDice);
			}
			
			String allDice = myDice + opponentDice;
			int count = 0;
			
			//Checks opponents dice against current guess
			for (int j = 0; j < allDice.length(); j++) {
				if(allDice.charAt(j) == (char) dice || allDice.charAt(j) == '1') {
					count++;
				}
			}
			
			if(num > count) {
				total++;
			}
			else {
				success++;
				total++;
			}
		}
		//Calculates and returns the probability
		double probability = (double) success/total;
		return probability;
	}
}