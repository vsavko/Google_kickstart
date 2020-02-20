import java.util.PriorityQueue;
import java.util.Scanner;

public class DiagonalPuzzle {
	/*
	1. go through all points 
		-if point is white, color its corresponding diagonal to white
	2. iterate over every "white diagonal" recursivly
		stop if exceeds minmum found allready or no more unused diagonals
		NOT all points on the diagonal
		add this diagonal to used ones
		
	*/
	private int solver(boolean[][] puzzle) {
		boolean [] whiteDiagonals = new boolean[4*puzzle.length-2];
		boolean [] usedDiagonals = new boolean[4*puzzle.length-2];
		
		//diagonals - 1 to 2n-1 -left, 2n to 4n-2 - right
		//for right / - [0][0] - is first, [1][2] && [2][1] - second 
		//for left \ - [n-1][0] is - 2n, [n-2][0] && [n-1][1] - is 2n +1 , etc...
		
		
		
	}
	
	private int getRightDiagonal(int x, int y) {
		return x + y;
	}
	
	private int getLeftDiagonal(int x, int y, int n) {
		return (2*n - 1) + (n -x) +y;
	}
	
	
	
	
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 0; i < t; ++i) {
          int n = in.nextInt();
          int min = 0;

          for (int j = 0 ; j < n; j++) { 
        	  min = solver(in.nextInt(), pq, min);
        	  sb.append(" ").append(min);
          }
          System.out.println(sb);
        }
      
        in.close();
      }
}
