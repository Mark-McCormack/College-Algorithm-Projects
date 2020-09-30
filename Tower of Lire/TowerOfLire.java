import java.util.Scanner;

public class TowerOfLire {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		//Get Tower Piece Thickness
		System.out.print("Enter Thickness: ");
		double thickness = sc.nextDouble();
		
		//Get Target Distance from Center of Tower
		System.out.print("Enter Target Distance: ");
		double targetDistance = sc.nextDouble();
		
		//Enter Radius of Tower Piece Thickness
		System.out.print("Enter Radius: ");
		double radius = sc.nextDouble();
		
		sc.close();
		
		//Initialise Variables
		double diameter = radius*2;
		double currentDistance = diameter;
		int count = 1;
		
		//Keep adding coins until we reach our target distance
		while(targetDistance > currentDistance) {
			currentDistance = currentDistance + diameter*(1/(2*count));
			count++;
		}
		
		//Print the Tower Height
		double result = count * thickness;
		System.out.println(result);
	}

}
