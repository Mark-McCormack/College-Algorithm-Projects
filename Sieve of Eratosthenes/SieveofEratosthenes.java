public class SieveofEratosthenes {

	public static void main(String[] args) {
		int n = 20;
		System.out.println(getPrimes(n));
	}
		
	public static int getPrimes(int n) {
		int check = 2;
		int [][] allNumbers = new int[n][2];
		int count = 0;
		
		//Generate list of all numbers
		for(int p = 1; p < n; p++) {
			allNumbers[p][0] = p;
			allNumbers[p][1] = 1;
		}
		
		//Test for prime numbers
		while(check < Math.sqrt(n)) {
			for(int i = check*check; i < allNumbers.length; i++) {
				if(allNumbers[i][0] % check == 0) {
					allNumbers[i][1] = 0;
				}
			}
			check++;
		}
		System.out.print("PRIMES BELOW " + n + ": ");
		
		for(int i = 0; i < allNumbers.length; i++) {
			if(allNumbers[i][1] == 1) {
				count++;
			}
		}	
		return count;
	}
}