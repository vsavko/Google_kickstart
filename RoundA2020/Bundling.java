import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class Bundling {
	
	public static int findMaxStrings(String [] arr, int K){
		String [] arr2 = new String[arr.length];
		for(int i = 0; i < arr.length; i++) {
			arr2[i] = arr[i];
		}
		int maxGroup = 0;
		int max = 0, countK;
		for(String string: arr2) {
			if(string.length() > max) max = string.length();
		}
		//System.out.println(max);
		if (K > 1) {
			for(int i = max; i > 0; i--) {
				countK = K-1;
				int pointer1 = -1;
				
				for(int j = 0; j < arr2.length; j++) {

					if (arr2[j] == "") continue;
					
					if (pointer1 == -1 || arr2[pointer1] == "") {
						pointer1 = j;
						continue;
					}

					//find groups of K length and remove them from table
					if(arr2[j].length() < i || arr2[pointer1].length() < i) {
						continue;
					}
								
					if(arr2[j].subSequence(0, i).equals(arr2[pointer1].subSequence(0, i))) {
						--countK;
					}
					else
						countK = K-1; //reset 0if different string
					
					//group found
					if (countK == 0) {
						countK = K-1;
						maxGroup += i;
						int l = j;
						for(int z = 0; z < K; z++ ) {
							while(arr2[l] == "") l--;
							arr2[l] = "";
						}
					}
					
					pointer1 = j;
				}
			}
		}
		else {
			for(String string: arr2) {
				maxGroup += string.length();
			}	
		}
		
		return maxGroup;
	}
	
	public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt(); // num of cases
        in.nextLine();
        int strings= 0 ;
        int groups = 0;
        
        for (int i = 1; i <= t; ++i) {
          strings = in.nextInt();
          groups = in.nextInt();
          in.nextLine();

          String[] array1 = new String[strings];
          for (int j = 0 ; j < strings; j++) { 
        	  String tmp = in.nextLine();
        	  array1[j] = tmp;
          }
         // in.nextLine();
          
          Arrays.sort(array1, Collections.reverseOrder());

          int max = findMaxStrings(array1, groups);
          System.out.println("Case #" + i + ": " + max);

        }
        in.close();
      }

}
