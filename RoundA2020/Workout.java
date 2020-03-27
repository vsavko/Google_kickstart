import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Workout {
	
	
	public static int findMinTrainingDifficulty(int [] arr, int limit) {
		PriorityQueue<Integer> queue = new PriorityQueue<>(10, Collections.reverseOrder());
		if (arr.length == 1) return 0;
		int element = 0;
		for(int i = 1; i < arr.length; i++) {
			queue.add(arr[i] - arr[i-1]);
		}
		element = queue.poll();
		//System.out.println(element + " " + limit);
		while(limit > 0) {
			if(element > 1) {
				int el1 = element / 2;
				int el2 = element % 2 + element / 2;
				queue.add(el1);
				queue.add(el2);
			}
			else return 1;
			element = queue.poll();
			--limit;
		}
		return element;
	}
	
	private static int findLimit(int [] arr, int optimalRange, int limit ) {
		int tmpLimit = limit;
		
		for(int i =1; i < arr.length; i++) {
			//need to find k for each range to get d optimal
			int x = Math.max((int)Math.ceil((double)(arr[i] - arr[i-1])/optimalRange)-1, 0);
			//System.out.println(i + " x " + x + " "+  arr[i] + " "+ arr[i-1]);
			tmpLimit -= x;
		}
		
		return tmpLimit;
	}
	
	public static int findMinTrainingDifficulty2(int [] arr, int limit) {
		//range between 2
		int max = 0;
		if (arr.length == 1) return 0;
		for(int i =1; i < arr.length; i++) {
			if (arr[i]-arr[i-1] > max) max = arr[i]-arr[i-1];
		}

		int tmpLimit1, tmpLimit2, k = 1, optimalRange = max, min = 1;
		
		while(true) {
			optimalRange = min + (max - min)/2;
			
			tmpLimit1 = findLimit(arr,optimalRange,limit);
			if(optimalRange == 1 && tmpLimit1 >= 0 )  break;
			tmpLimit2 = findLimit(arr,optimalRange-1,limit);
			if(tmpLimit2 < 0 && tmpLimit1 >= 0) break;
			
			if (tmpLimit1 >= 0) {
				max = optimalRange-1;
			}
			
			if (tmpLimit1 < 0) {
				min = optimalRange+1;
			}
		}
		
		return optimalRange;
	}
	
	public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int t = in.nextInt(); // num of cases
        in.nextLine();
        int sessions= 0 ;
        int limit = 0;
        
        for (int i = 1; i <= t; ++i) {
          sessions = in.nextInt();
          limit = in.nextInt();
          in.nextLine();

          int[] array1 = new int[sessions];
          for (int j = 0 ; j < sessions; j++) { 
        	  int tmp = in.nextInt();
        	  array1[j] = tmp;
          }
          in.nextLine();

        //  int min = findMinTrainingDifficulty(array1, limit);
          int min2 = findMinTrainingDifficulty2(array1, limit);
          
          //System.out.println("Case #" + i + ": " + min );
          System.out.println("Case #" + i + ": " + min2 );
          //max = 0;

        }
        in.close();
      }
}
