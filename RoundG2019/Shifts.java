
import java.util.Scanner;

public class Shifts {
	static int max = 0;
	
	public static void checkRecursivly(int[] arrays1, int[] array2, int cursor, int targetHap, 
			long hap1, long hap2) {
		//System.out.println(arrays1.length + " " + cursor);
		if (cursor+1 >= arrays1.length) {
			if (hap1 >= targetHap && hap2 >= targetHap)
				++max;
			return;
		}
		
		checkRecursivly(arrays1, array2, cursor+1,targetHap, hap1 + arrays1[cursor+1], hap2);  //true/false
		checkRecursivly(arrays1, array2, cursor+1,targetHap, hap1, hap2  + array2[cursor+1]);  //false/true
		checkRecursivly(arrays1, array2, cursor+1,targetHap, hap1 + arrays1[cursor+1], hap2  + array2[cursor+1]);	//true/true
		
	}
	
	public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt(); // num of cases
        in.nextLine();
        int shifts= 0 ;
        int happines = 0;
        
        for (int i = 1; i <= t; ++i) {
          shifts = in.nextInt();
          happines = in.nextInt();
          in.nextLine();

          int[] array1 = new int[shifts];
          int[] array2 = new int[shifts];
          for (int j = 0 ; j < shifts; j++) { 
        	  int tmp = in.nextInt();
        	  array1[j] = tmp;
          }
          in.nextLine();
          for (int j = 0 ; j < shifts; j++) { 
        	  int tmp = in.nextInt();
        	  array2[j] = tmp;
          }
          in.nextLine();
          
          checkRecursivly(array1,array2,-1,happines,0,0);
          
          //System.out.println(Arrays.toString(array1));
          //System.out.println(Arrays.toString(array2));

          System.out.println("Case #" + i + ": " + max );
          max = 0;

        }
        in.close();
      }
}
