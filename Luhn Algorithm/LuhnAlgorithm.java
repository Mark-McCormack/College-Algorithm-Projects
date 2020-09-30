package LuhnsAlgorithm;
public class LuhnAlgorithm {

	public static void main(String[] args) {
		String num = "12345678913";
		System.out.println(checkValid(num));
	}
	
	public static String checkValid(String num)	{
		int total = 0;
		int count = 1;
		
		for(int i = num.length()-1; i >= 0; i--) {
			//Gets value of current card digit
			int currentNum = Character.getNumericValue(num.charAt(i));
			//When its an even positioned number, add double it to the total sum. 
			//If that value exceeds 9, take away 9.
			if(count%2 == 0) {
				if((currentNum*2)> 9){
					total += (currentNum * 2) - 9;
				}
				else {
					total += currentNum * 2;
				}
			}
			//When its an odd positioned number, add it to the total sum.
			else {
				total += currentNum;
			}
			count++;
		}
		//If the sum is divisible by 10, it is valid. Otherwise it isn't
		if(total%10 == 0) {
			return "VALID";
		}
		else{
			return "INVALID";
		}
	}
}
