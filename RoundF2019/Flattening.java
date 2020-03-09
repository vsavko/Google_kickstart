import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

public class Flattening2 {
	
	public static int findMinChange(int [] array1, int limit) {
		if (limit > array1.length) return 0;
		int [][] cost = new int[array1.length+1][array1.length+1];
		int [][] dp = new int[array1.length+1][array1.length+1];
		
		HashMap<Integer, Integer> hm;
		
		//find cost
		for(int i = 0; i < array1.length; i++) {
			
			for(int j = i; j < array1.length; j++) {
			   dp [i][j] = Integer.MAX_VALUE;
			   hm = new HashMap<Integer, Integer>(); 
			   for(int l = i; l <= j ; l++) {
				   int key = array1[l]; 
		           if(hm.containsKey(key)) hm.put(key, hm.get(key)+1); 
		           else hm.put(key, 1); 
				}
			   
			    int max_count = 0; 
		        for(Entry<Integer, Integer> val : hm.entrySet()) 
		        { 
		            if (max_count < val.getValue())
		                max_count = val.getValue(); 
		        } 
		        cost[i][j] = j - i + 1 - max_count;
			}
		}
		
		//min dp[k][j-1] + cost [k+1][i] for all i
		int min= Integer.MAX_VALUE;
		for(int j = 0; j <=  limit; j++) {
			for (int k = 0; k < array1.length; k++) { //k - divides the array into arbitrary two
				if(j == 0 ) {
					dp[k][j] = cost[0][k];
				}
				else {
					min = Integer.MAX_VALUE;
					int tmp =0;
					for(int z = 0; z <= k; z++) {
						tmp = dp[z][j-1] + cost[z+1][k];
						if (min > tmp) min = tmp;
					}
					dp[k][j] = min;
				}	
			}	
		}

		return dp[array1.length-1][limit];
		
	}
	
	public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt(); // num of cases
        in.nextLine();
        int numInts= 0 ;
        int limit = 0;
        
        for (int i = 1; i <= t; ++i) {
          numInts = in.nextInt();
          limit = in.nextInt();
          in.nextLine();

          int[] array1 = new int[numInts];
          for (int j = 0 ; j < numInts; j++) { 
        	  int tmp = in.nextInt();
        	  array1[j] = tmp;
          }
          in.nextLine();
          
          int [] dynamic = new int[numInts];
          Arrays.fill(dynamic, Integer.MAX_VALUE);
          int min = findMinChange(array1, limit);
         
          System.out.println("Case #" + i + ": " + min );

        }
        in.close();
      }
}
