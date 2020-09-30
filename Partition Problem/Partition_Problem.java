import java.util.Scanner;

public class Partition_Problem {

	public static void main(String[] args) {
		//Initialise Scanners
		Scanner myscanner = new Scanner(System.in);
		myscanner.close();

		//Initialise Array of values
		int items = 40;
		int [] contents = new int[items];
		
		//Set of values found in the Specifications
		contents[0] = 1620243245;
		contents[1] = 676519318;
		contents[2] = 1448562316;
		contents[3] = 588289083;
		contents[4] = 1657685952;
		contents[5] = 1259248075;
		contents[6] = 1249333865;
		contents[7] = 701357539;
		contents[8] = 1124032684;
		contents[9] = 1281294568;
		contents[10] = 1739829499;
		contents[11] = 1715575050;
		contents[12] = 243385769;
		contents[13] = 1476128934;
		contents[14] = 940227405;
		contents[15] = 1231289694;
		contents[16] = 157034931;
		contents[17] = 1928184040;
		contents[18] = 95797696;
		contents[19] = 822469907;
		contents[20] = 1926085728;
		contents[21] = 976959161;
		contents[22] = 558700770;
		contents[23] = 1320671444;
		contents[24] = 1914761640;
		contents[25] = 508815619;
		contents[26] = 1181964935;
		contents[27] = 781134463;
		contents[28] = 224137457;
		contents[29] = 1582237767;
		contents[30] = 956184431;
		contents[31] = 1024094177;
		contents[32] = 527627984;
		contents[33] = 979068468;
		contents[34] = 200891188;
		contents[35] = 889853575;
		contents[36] = 1916225661;
		contents[37] = 602585198;
		contents[38] = 2097149275;
		contents[39] = 1733564654;
		
		char [] solution = solve(contents);
		
		long subset1 = 0;
		long subset2 = 0;
		
		for(int i = 0; i < items; i++) {
			if(solution[i] == '-') {
				subset1+=contents[i];
			}
			else {
				subset2+=contents[i];
			}
		}
		//print(contents);
		System.out.println(subset1+subset2);
	}
	
	//Find which numbers to make negative and positive, so the total sum is close to 0
	public static char[] solve(int [] contents) {
		
		//Create Symbols Array and Sort Contents
		char [] symbol = new char[contents.length];
		descendingBubbleSort(contents);
		
		//Make Buckets 1 & 2 for sorting and 3 for analysis
		int [] bucket1 = new int[contents.length];
		int [] bucket2 = new int[contents.length];
		int [] difference = new int[contents.length];
		
		//Fill Up Buckets with 2 First Numbers
		bucket1[0] = contents[0];
		bucket2[0] = contents[1];
		symbol[0] = '+';
		symbol[1] = '-';
		
		//Put Next Element Into Smallest Sum Bucket
		for(int i = 2; i < contents.length; i++) {
			if(sum(bucket1) < sum(bucket2)) {
				bucket1[i] = contents[i];
				symbol[i] = '-';
			}
			else{
				bucket2[i] = contents[i];
				symbol[i] = '+';
			}
		}	
		
		//Sort The Buckets Themselves
		descendingBubbleSort(bucket1);
		descendingBubbleSort(bucket2);
		
		//Finds Difference Between Buckets And Stores As Array
		refillBucket3(bucket1, bucket2, contents, difference);
		
		//Make Bucket Smaller By Checking Against Current Difference Sum
		for(int i = 0 ; i < difference.length; i++) {
			if(difference[i] < 0 && sum(difference) < 0) {
				difference[i] = difference[i]*-1;
				//Swaps Appropriate Elements To Other Buckets
				int temp = bucket1[i];
				bucket1[i] = bucket2[i];
				bucket2[i] = temp;
			}
			else if(difference[i] > 0 && sum(difference) < 0) {
				difference[i] = difference[i]*-1;
				//Swaps Appropriate Elements To Other Buckets
				int temp = bucket1[i];
				bucket1[i] = bucket2[i];
				bucket2[i] = temp;
			}
		}

		int index1 = 0;
		int index2 = 0;
		int lastDifference = 0;
		
		for (int i = 0;  i< difference.length; i++) {
			for (int j = 0; j < difference.length; j++) {
				if(sum(difference) > difference[i] + difference[j] && difference[i] + difference[j] > lastDifference) {
					index1 = i;
					index2 = j;
					lastDifference = difference[i] + difference[j];
				}
			}
		}
		
		difference[index1] *= -1;
		difference[index2] *= -1;
		
		int temp= bucket1[index1];
		bucket1[index1] = bucket2[index1];
		bucket2[index1] = temp;
		
		temp = bucket1[index2];
		bucket1[index2] = bucket2[index2];
		bucket2[index2] = temp;
		
		//Setting Symbols For Each Number
		for(int i = 0; i < difference.length; i++) {
			for(int j = 0; j < difference.length; j++) {
				if(contents[i] == bucket2[j]) {
					symbol[i] = '-';
				}
				else if(contents[i] == bucket1[j]){
					symbol[i] = '+';
				}
			}
		}
		
		//Prints Out And Returns Relevant Information
		System.out.println("The Dream: " + (5528435 + 168965235 - 174932141));
		printDetails(bucket1, bucket2, difference, symbol);
		return symbol;	
	}
	
	public static void refillBucket3(int [] bucket1, int [] bucket2, int [] contents, int [] difference) {
		for(int i = 0; i < contents.length; i++) {
			difference[i] = bucket1[i] - bucket2[i];
		}
	}
	
	public static void printDetails(int [] bucket1, int [] bucket2, int [] difference, char [] symbols) {
		System.out.println("BUCKET 1: " + sum(bucket1));
		System.out.println("BUCKET 2: " + sum(bucket2));
		System.out.println();
		
		System.out.print("BUCKET 1 CONTENT: ");
		print(bucket1);
		System.out.print("BUCKET 2 CONTENT: ");
		print(bucket2);
		System.out.print("BUCKET 3 CONTENT: ");
		print(difference);
		System.out.print("SYMBOLS CONTENT: ");
		
		for(int i = 0; i < symbols.length; i++) {
			System.out.print(symbols[i]);
		}
		
		System.out.println();
		System.out.println("DIFFERENCE SUM: " + sum(difference));
	}
	
	public static void print(int [] array) {
		for(int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}
	
	public static long sum(int [] array) {
		long result = 0;
		for(int i = 0; i < array.length; i++) {
			result += array[i];
		}
		return result;
	}
	
	public static int[] descendingBubbleSort(int [] array) {
		for(int i = 0; i < array.length-1; i++) {
			for(int j = 0; j < array.length - i - 1; j++) {
				if(array[j] <= array[j+1]) {
					int temp = array[j];
					array[j] = array[j+1];
					array[j+1] = temp;
				}
			}
		}
		return array;
	}	
}
