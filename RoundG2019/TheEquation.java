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
			bit = 0;
			if (tmp > maxNum) maxNum = tmp;
			//System.out.println("temp" + Long.toBinaryString(tmp));
			while(tmp > 0) {
				tmp =  tmp >> 1;
				++bit;
			}
			if (bit > maxBit) maxBit =  bit;
			//System.out.println("maxBit "+ maxBit);
		}
		
		//find number of ones in each bit of summing numbers
		int [] numOfOnes = new int[maxBit+1];
		for (int i = 1; i < maxBit+1; i++) {
			for(int j = 0; j < arr.length; j++) {
				numOfOnes[i] += (arr[j] & (1L << i-1)) >> i-1;
			}
		}
		//System.out.println(Arrays.toString(numOfOnes));
		
		//find min possible xor k
		long minK = 0;
		for(int i = 1; i < numOfOnes.length; i++) {
			long tmpK = 0;
			int numZeros = arr.length - numOfOnes[i];
			if (numOfOnes[i] > numZeros) tmpK = 1L;
			minK |= (tmpK << i-1);
		}

		//check with max
		long tmp = max;
		bit = 0;
		while(tmp > 0) {
			tmp =  tmp >> 1;
			++bit;
		}
		if (bit > maxBit) maxBit =  bit;
		long mask = 0;
		
		//start adding digits to k from maxbit
		for(int i = maxBit; i > 0; i--) {
			k = k | (1L << i-1);
			mask = mask | (1L << i-1);
			//check k
			long sum = 0;
			long temp;
			for(int j = 0 ; j < arr.length; j++) {
				//extract that bit from every value
				//check that after xoring its less or equal to Max's
				temp = arr[j] & mask;
				sum += temp ^ k;
			}

			long remainingSum = 0;
			for(int j = 0 ; j < arr.length; j++) {
				//extract that bit from every value
				//check that after xoring its less or equal to Max's
				temp = arr[j] & ~mask;
				remainingSum += temp ^ (minK & ~mask);
			}	
			
			//check if added bit works
			if (sum > max || (max - sum - remainingSum < 0)) { 
				k = k & ~(1L << i-1);
			}

			if(i == 1) { //check if k == 0 doesn't fit
				sum = 0;
				for(int j = 0 ; j < arr.length; j++) {
					sum += arr[j]^k;
				}
				if( sum> max) k = -1;
			}
		}
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
          limit = in.nextLong();
          in.nextLine();

          long[] array1 = new long[numInts];
          for (int j = 0 ; j < numInts; j++) { 
        	  long tmp = in.nextLong();
        	  array1[j] = tmp;
          }
          in.nextLine();

          long max = checkMaxXor(array1, limit);
         
          System.out.println("Case #" + i + ": " + max );
          //max = 0;

        }
        in.close();
      }
}
