import java.awt.Color;

// Burgard Lu (jl4nq)
// HW 7
// Sources:
// https://www.geeksforgeeks.org/ways-to-represent-a-number-as-a-sum-of-1s-and-2s/
// TA's OH--Joshua 
// Piazza
public class Recursion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(reverseString("hello")); // expected: olleh; printed: olleh
		System.out.println(reverseString("Hi")); // expected: iH; printed: iH
		System.out.println(countWays(5)); // expected: 8; printed: 8 
		System.out.println(countWays(3)); // expected: 3; printed: 3 
		System.out.println(Ackermann(1, 1)); // expected: 3; printed: 3
		System.out.println(Ackermann(0, 3)); // expected: 4; printed: 4
		System.out.println(handShakes(3)); // expected: 3; printed: 3
		System.out.println(handShakes(5)); // expected: 10; printed: 10
		System.out.println(squareRoot(9)); // expected: 3.000015360039322; printed: 3.000015360039322
		System.out.println(squareRoot(4)); // expected: 2.0; printed: 2.0

		
		
		World myWorld = new World(900,900,Color.WHITE);
		Turtle bob = new Turtle(myWorld);
		bob.setDelay(0);
		snowflake(bob, 3, 150);
		snowflake(bob, 1, 200);
		
	}
	
	public static String reverseString(String s) {
		if (s.length() <= 1) { // base case
			return s;
		}
		else {
			return reverseString(s.substring(1)) + s.charAt(0);// split a string into the initial character and the rest of the string
		}
	}
	
	public static int countWays(int numStairs) { // The number of ways forms fibonacci series
		if (numStairs <= 3) { // base case
			return numStairs;
		}
		else
			return countWays(numStairs - 1) + countWays(numStairs - 2);
	}
	
	public static int Ackermann(int m, int n) {
		if (m < 0 || n < 0) return -1; // bad input
		
		 if (m == 0) {
			return n+1;
		}
		
		else if (m > 0 && n == 0) {
			return Ackermann(m-1, 1);
		}
		
		else {
			return Ackermann(m-1, Ackermann(m, n-1));
		}

	}
	
	public static int handShakes(int n) {
		if (n == 1) return 0; // base case 1
		else if (n == 2) return 1; // base case 2
		else {
			return n - 1 + handShakes(n-1); // n - 1 = number of handshakes one extra person coming in needs to make
											// handShakes(n-1) = the number handshakes happened between the people who are already in the room excluding the new person
		}
	}
	
	public static double squareRootGuess(double x, double g) { // helper method
		if (Math.abs(g*g - x) <= 0.0001) return g; //base case
		else {
			return squareRootGuess(x, (g+(x/g))/2);
		}
	}
	
	public static double squareRoot(double x) {
		return squareRootGuess(x, 2); // 2 is randomly picked
	}
	
	public static void snowflake(Turtle t, int depth, double length) {
		snowflakeHelper(t, depth, length); // draw the three sides 
		t.right(120);
		snowflakeHelper(t, depth, length);
		t.right(120);
		snowflakeHelper(t, depth, length);
	}
	
	public static void snowflakeHelper(Turtle t, int n, double length) {// Helper method; n is the depth, length is the size length of the greatest triangle
		if (n == 0) {// base case
			t.forward(length);
		}
		else {
			snowflakeHelper(t, n-1, length/3);
			t.left(60);
			snowflakeHelper(t, n-1, length/3);
			t.right(120);
			snowflakeHelper(t, n-1, length/3);
			t.left(60);
			snowflakeHelper(t, n-1, length/3);
		}
	}
	
	
	
	
	
	
	
	
	

}
