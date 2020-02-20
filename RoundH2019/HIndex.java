import java.util.*;

    public class HIndex {
      
      private static int solver(int num, PriorityQueue<Integer> pq, int min) {
    	  if (num <= min) return min;
    	  pq.add(num);
    	  if (pq.size() > min && pq.peek() > pq.size()) {
    		  min = pq.size();
    	  }
    	  else if(pq.peek() == pq.size()) {
    		  min = pq.size();
    		  while(pq.size() > 0 && pq.peek() == min) pq.poll();
    	  }
    	  return min;
      }
    	
      public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 0; i < t; ++i) {
          int n = in.nextInt();
          int min = 0;
          PriorityQueue<Integer> pq = new PriorityQueue<>();
          StringBuilder sb = new StringBuilder(100000);
          sb.append("Case #").append(i+1).append(":");
          for (int j = 0 ; j < n; j++) { 
        	  min = solver(in.nextInt(), pq, min);
        	  sb.append(" ").append(min);
          }
          System.out.println(sb);
        }
      
        in.close();
      }
}
