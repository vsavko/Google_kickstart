import java.util.Arrays;
import java.util.Scanner;

public class Allocation {

	public static int findMaxHouses(int [] array1, int limit) {
		Arrays.sort(array1);
		int ans = 0;
		for(int i = 0; i < array1.length; i++) {
			if(array1[i] <= limit) {
				++ans;
				limit -= array1[i]; 
			}
			else break;
		}
		
		return ans;
	}
	
	public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt(); // num of cases
        in.nextLine();
        int houses= 0 ;
        int limit = 0;
        
        for (int i = 1; i <= t; ++i) {
          houses = in.nextInt();
          limit = in.nextInt();
          in.nextLine();

          int[] array1 = new int[houses];
          for (int j = 0 ; j < houses; j++) { 
        	  int tmp = in.nextInt();
        	  array1[j] = tmp;
          }
          in.nextLine();

          int max = findMaxHouses(array1, limit);
         
          System.out.println("Case #" + i + ": " + max );
          //max = 0;

        }
        in.close();
      }
}
