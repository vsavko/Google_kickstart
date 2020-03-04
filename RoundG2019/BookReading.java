import java.util.Scanner;

public class BookReading {
	
	public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt(); // num of cases
        in.nextLine();
        int readers= 0 ;
        int number = 0;
        
        for (int i = 1; i <= t; ++i) {
          number = in.nextInt();
          int numTornPages = in.nextInt();
          readers = in.nextInt();
          in.nextLine();
          
          boolean [] arr = new boolean[number+1];

          //HashMap<Integer,Integer> hm = new HashMap<>();
          for (int j = 1 ; j <= numTornPages; j++) { 
        	  int tmp = in.nextInt();
        	  arr[tmp] = true;
        	  //hm.put(tmp, 0);
          }
          in.nextLine();

          int max = 0;
          
          for (int j = 1 ; j <= readers; j++) { 
        	  int tmpReader = in.nextInt();
        	  int tmp = 0;
        	  for(int z = tmpReader; z <= number; z += tmpReader){ //iterate torn pages
        		 // if (hm.containsKey(z)) ++tmp;
        		  if (arr[z] == true) ++tmp;
        	  }
        	  max += (number / tmpReader) - tmp;
          }
          //in.nextLine();

          System.out.println("Case #" + i + ": " + max);

        }
        in.close();
      }
}
