import java.util.*;
import Scrabble.FileIO;

public class Scrabble_Solver {
    public static void main(String args[] ) throws Exception {
        //Scans in letters we can use in Scrabble
        Scanner myscanner = new Scanner(System.in);
        String letters = myscanner.nextLine();
        FileIO reader = new FileIO();
        
        //Reads in "Dictionary.txt", with all English words
        String[] array = reader.load(".//dictionary.txt");
        System.out.println(findLength(letters,array));
    }
        

    public static int findLength(String letters, String[] array){
        int length = 0;

        for(int i = 0; i < array.length; i++){
            //Check if the current word contains our letters and if it is longer
            if(containsLetters(array[i], letters) && array[i].length() > length){
                length = array[i].length();
            }
        }
        return length;
    }

    public static boolean containsLetters(String word, String cases){
        //We assume the word uses all letters to begin with
        boolean answer = true;
        String letter;

        for(int i = 0; i < cases.length(); i++){
            //If it contains the current letter, remove it from the word
            if(word.indexOf(cases.charAt(i)) != -1){
                letter = Character.toString(cases.charAt(i));
                word.replace(letter, "");
            }
            //Otherwise, return that the word cannot be made with these letters
            else{
            	System.out.println(word + " doesn't contain" + cases.charAt(i));
                return false;
            }
        }
        return answer;
    }
}