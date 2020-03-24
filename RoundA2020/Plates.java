import java.util.Arrays;
import java.util.Scanner;

public class Plates {
	
	//brute force
	public static int findMaxPlates(int [][] stacks, int row, int col, int platesLeft, int ans) {
		if(platesLeft == 0) {
			//System.out.println("ANS" + ans);
			return ans;
		}
		
		int rec1 = 0 , rec2 = 0;
		//System.out.println(row + " " + col + " " + ans + " platesLeft " + platesLeft);
		
		if(row < stacks.length -1) {
			//System.out.println(" row " + row + " platesLeft " + platesLeft + " " +  stacks.length);
			rec1 = findMaxPlates(stacks, row+1, col, platesLeft-1, ans + stacks[row+1][col]);
		}
		if(col < stacks[0].length -1) {
			//System.out.println("col " + col + " platesLeft " + platesLeft);
			rec2 = findMaxPlates(stacks, -1, col+1, platesLeft, ans);
		}
		
		ans= Math.max(rec1, rec2);
		//System.out.println(platesLeft + " " + ans  + " " +  rec1 + " " + rec2 + " platesLeft " + platesLeft); 
		
		return ans;
	}
	
	//dp solution
	public static int findMaxPlates2(int [][] stacks,int platesLeft) {
		//limit platesLeft
		int [] dp = new int[platesLeft+1];
		int [] dp2;
		int totalCells = 0;
		
		//dynamic table construction
		for(int col = 0; col < stacks[0].length; col++) {
			totalCells += stacks.length;
			int rowSum = 0;
			dp2 = new int[platesLeft+1];
			for(int row = 0; row < stacks.length; row ++) {
				rowSum += stacks[row][col];
				for(int cells = row+1; cells <= totalCells-(stacks.length - row - 1) && cells <= platesLeft; cells++) {
					//System.out.println("cells" + cells + " " + rowSum + " " + dp[cells- row-1] + " " + Math.max(dp[cells], rowSum + dp[cells- row-1]));
					dp2[cells] = Math.max(Math.max(dp[cells], dp2[cells]), rowSum + dp[cells- row-1]);
				}
				
			}
			dp = dp2;
			//System.out.println(Arrays.toString(dp));
		}
		
		
		//System.out.println("platesLeft" + platesLeft);
		return dp[platesLeft];
	}
	
	public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt(); // num of cases
        in.nextLine();
        int stacks= 0 ;
        int numInStack = 0;
        int plates = 0;
        
        for (int i = 1; i <= t; ++i) { //cases
          stacks = in.nextInt();
          numInStack = in.nextInt();
          plates = in.nextInt();
          in.nextLine();

          int[][] array1 = new int[numInStack][stacks];
          for(int z = 0; z < stacks ; z++) {
	          for (int j = 0 ; j < numInStack; j++) { 
	        	  int tmp = in.nextInt();
	        	  array1[j][z] = tmp;
	          }
          }
          in.nextLine();
          //System.out.println(Arrays.deepToString(array1));
         // int max = findMaxPlates(array1, -1,0, plates,0);
          int max2 = findMaxPlates2(array1, plates);
          //System.out.println("Case #" + i + ": " + max );
          System.out.println("Case #" + i + ": " + max2 );
          //max = 0;

        }
        in.close();
      }
}
