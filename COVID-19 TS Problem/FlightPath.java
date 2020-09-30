import java.util.Arrays;
import COVID19.FileIO;

public class FlightPath {
	public static double [] shortestPath = new double[1000];		//Stores the shortest path.
	public static boolean [] visited = new boolean[1000];			//Stores if node is visited or not.
	
	public static void main(String args[]) {
		//Initialization
		double [][] points = getPoints(); 				//Extracts Coordinates from CSV.
		double [][] distances = getDistances(points);	//Calculates all point distances.
		
		//Algorithm
		shortestPath[0] = 0;							//Start on 0 node (Maynooth University)
		
		for(int i = 1; i < 1000; i++) {					
			visited[i]=false;							//Makes sure all nodes are unvisited.
			shortestPath[i] = Double.MAX_VALUE;			//Sets distance to "Infinity".
		}
		
		for(int i = 0; i < 1000; i++) {
			int node = shortestPath();					//Find adjacent node that's closest.
			visited[node] = true;						//Mark it as visited.
			
			for(int j = 0; j < 1000; j++) {
				//If we haven't visited a node, and the current shortest path is greater than the previous plus the next step, then swap
				if(visited[j] == false && shortestPath[j] > shortestPath[node] + distances[node][j]) {
					shortestPath[j] = shortestPath[i] + distances[node][j];
				}
			}
		}
		
		System.out.println(Arrays.toString(visited));
		//Output
		for(int i = 0; i < 1000; i++) {
			System.out.println((int)shortestPath[i] + "km");
		}
	}
	
	public static double getDistance(double x1, double y1, double x2, double y2) {
        double distanceLA = Math.toRadians(x2-x1);
        double distanceLO = Math.toRadians(y2-y1);

        double latitude1 = Math.toRadians(x1);
        double latitude2 = Math.toRadians(x2);

        double a = Math.pow(Math.sin(distanceLA/2),2) + Math.cos(latitude1) * Math.cos(latitude2)* Math.pow(Math.sin(distanceLO/2),2);
        double c = 2 * Math.asin(Math.sqrt(a));
        
        double ans = 6372.8*c;
        
        return ans;
	}
	
	public static double[][] getPoints() {
		//Import CSV
		FileIO reader = new FileIO();
		String[] inputs = reader.load("./data.csv");
		
		double [][] points = new double[1000][2];
		
		for(int i = 0; i < 1000; i++) {
			inputs[i] = inputs[i].replace(",", "_");
			points[i][0] = Double.parseDouble(inputs[i].substring(1,inputs[i].indexOf("_")));
			points[i][1] = Double.parseDouble(inputs[i].substring(inputs[i].indexOf("_")+1));
		}
		return points;
	}
	
	public static double[][] getDistances(double [][] points) {
		//Creates Adjacency Matrix
		double [][] distances = new double[1000][1000];
		
		for (int i = 0; i < distances.length; i++) {
			for (int j = 0; j < distances.length; j++) {
				distances[i][j] = getDistance(points[i][0], points[i][1], points[j][0], points[j][1]);
			}
		}
		return distances;
	}
	
	public static int shortestPath() {
		double smallestDistance = Double.MAX_VALUE;
		int index = 0;
		
		for(int i = 0; i < 1000; i++) {
			if(!visited[i] && shortestPath[i] <= smallestDistance) {
				smallestDistance = shortestPath[i];
				index = i;
			}
		}
		return index;
	}
}