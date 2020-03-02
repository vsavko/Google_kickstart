import java.util.Scanner;

public class TheEquations {
	
	private static long checkMaxXor(long [] arr, long max) {
		long k = 0;
		int maxBit = 0;
		int bit = 0;
		long maxNum = 0;
		
		//find max digit
		for(int i = 0; i < arr.length; i++) {
			long tmp = arr[i];
			if (tmp > maxNum) maxNum = tmp;
			
			while(tmp > 0) {
				tmp =  tmp >> 1;
				++bit;
			}
			if (bit > maxBit) maxBit =  bit;
		}
		
		//check with max
		long tmp = max;
		bit = 0;
		while(tmp > 0) {
			tmp =  tmp >> 1;
			++bit;
		}
		if (bit > maxBit) maxBit =  bit;
		
		//start adding digits to k from maxbit
		for(int i = maxBit; i > 0; i--) {
			k = k | (long)(1 << i-1);
			//check k
			long sum = 0;
			long temp;
			for(int j = 0 ; j < arr.length; j++) {
				//extract that bit from every value
				//check that after xoring its less or equal to Max's
				temp = arr[j] & (long)(1 << i-1);
				sum += temp ^ (k & (long)(1 << i-1));
			}
			
			//System.out.println(sum + " " + (max & (1 << i-1)) + " " + max);
			if (sum > (max & (1 << i-1))) 
				k = k & ~(1 << i-1);
			//System.out.println(Long.toBinaryString(k));
		}
		
		if (k ==0) k = -1;
		return k;
	}
	
	public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int t = in.nextInt(); // num of cases
        in.nextLine();
        int numInts= 0 ;
        long limit = 0;
        
        for (int i = 1; i <= t; ++i) {
          numInts = in.nextInt();
          limit = in.nextInt();
          in.nextLine();

          long[] array1 = new long[numInts];
          for (int j = 0 ; j < numInts; j++) { 
        	  long tmp = in.nextInt();
        	  array1[j] = tmp;
          }
          in.nextLine();

          long max = checkMaxXor(array1, limit);
                   
          //System.out.println(Arrays.toString(array1));
          //System.out.println(Arrays.toString(array2));
          

          System.out.println("Case #" + i + ": " + max );
          //max = 0;

        }
        in.close();
      }
}
