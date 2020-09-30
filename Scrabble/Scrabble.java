package Scrabble;
import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.regex.*;

public class Scrabble {
	//Initialise values of each letter in "Scrabble"
	public static int [] values = new int[]{1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,2,10,1,1,1,1,4,4,8,4,10};
	
	public static void main(String[] args) {
		//Initialise Reader
		FileIO reader = new FileIO();
		System.out.println("Enter your letters: ");
		Scanner sc = new Scanner(System.in);
		
		//Imports the English Dictionary
        String[] inputs = reader.load("./dictionary.txt");

        //Take in our Current Letters and forms a Regular Expression from them
		String currentState = sc.nextLine();
		String regularExpression = ("[" + currentState + "]").repeat(currentState.length());

		//Creates a Priority Queue for our words
        PriorityQueue<String> queue = new PriorityQueue<String>(1000000);
        
        //Adds only elements that match Regular Expression
		for(int i = 0; i < 216555; i++) {
			if(Pattern.matches(regularExpression, inputs[i])) {
				queue.add(inputs[i]);
			}
		}
		
		//Converts PQ To A Stack
		String[] sortedArray = new String[queue.size()];
		int q = 0;
		while (!queue.isEmpty() ) {
		    sortedArray[q] = queue.remove();
		    q++;
		}
		
		//Sorts the Array based on Score
		for (int i = 0; i < sortedArray.length - 1; i++) {
			for (int j = 0; j < sortedArray.length - i - 1; j++) {
				if (scrabbleValue(sortedArray[j]) < scrabbleValue(sortedArray[j + 1])) {
					String temp = sortedArray[j];
					sortedArray[j] = sortedArray[j + 1];
					sortedArray[j + 1] = temp;
				}
			}
		}
        
        sc.close();
        
        //Print out top 10 values
        System.out.println("Here are the top 10 suggestions: ");
        for(int i = 0; i < 10; i++) {
        	System.out.println(sortedArray[i] + ": " + scrabbleValue(sortedArray[i]));
        }
	}
	
	public static int scrabbleValue(String word) {
		int value = 0;
		word.toLowerCase();
		
		//Runs through each letter and gets its value from the value array
		for(int i = 0; i < word.length(); i++) {
			int find = (int) word.charAt(i);
			value += values[find - 97];
		}
		return value;
	}
}

