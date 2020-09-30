import java.util.Scanner;

public class Spaghetti {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Please enter % of Spaghetti ate: ");
		int n = sc.nextInt();
		sc.close();
		
		int success = 0;
		int failure = 0;
		
		double a=0;
		double b=0;
		
		//Monte Carlo to Generate Spaghetti Pieces
		for(int i = 0; i < 5000000; i++) {
			//Generated Spaghetti
			a = Math.random()*100;
			b = Math.random()*(100-a);
			
			double nibble = a*(n/100.0);
			a -= nibble;

			//Using the Triange Inequality to see if Spaghetti forms a Triangle
			if(a<b) {
				if(b>50 && b < a + 50 && a < 50) {	success++;	}
				else{ failure++; }
			}
			else if(a>b) {
				if(a>50 && a < b + 50 && b < 50) {	success++;	}
				else{ failure++; }
			}
		}
		System.out.println((double) success/(success+failure));
	}
}