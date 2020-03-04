import java.util.Scanner;

public class Flattening {
	
	public class Group{
		int id;
		int size;
		int height;
		int start;
		int end;
		
		public Group(int id, int size, int height, int start, int end) {
			super();
			this.id = id;
			this.size = size;
			this.height = height;
			this.start = start;
			this.end = end;
		}
	}
	
	private int findMinChange(int [] array, int limit) {
		int ans = 0;
		int diff = 0;
		Group [] arrayGrouped = new Group[array.length];
		
		arrayGrouped[0] = new Group(0,1,array[0],0,0);
		//find differences
		for (int i = 1; i < array.length; i++) {
			if (array[i-1] != array[i]) { 
				++diff;
				arrayGrouped[i] = new Group(i,1,array[i],i,i);
			}
			else {
				++arrayGrouped[i-1].size;
				arrayGrouped[i-1].end = i;
				arrayGrouped[i] = arrayGrouped[i-1];
			}
			//arrayCopy[i-1] = array[i-1]; 
			//if (i == array.length - 1 ) arrayCopy[i] = array[i]; 
		}
		//System.out.println(diff);
		
		//decrease min element(s)
		while(diff > limit) {
			int min = Integer.MAX_VALUE;
			Group minGroup = null;
			int k = 0;
			for(int i = 0; i < array.length; i++) {
				//chose min group, check if it has 2 same elements on the side
				if (arrayGrouped[i].size < min) {
					minGroup = arrayGrouped[i];
					min = arrayGrouped[i].size;
					k = 1; //decrease in difference
				}
				if (arrayGrouped[i].start > 0 && arrayGrouped[i].end < array.length-1 &&
						arrayGrouped[i].size == min &&
							arrayGrouped[arrayGrouped[i].start-1].height == arrayGrouped[arrayGrouped[i].end+1].height) { //if groups on two sides are equal
					minGroup = arrayGrouped[i];
					k = 2; //decrease in difference
				}
				
			}
			//System.out.println("minGroup" + minGroup.id + " k " + k);
			
			if (minGroup == null) throw new IllegalArgumentException("minGroup is null!");
			diff -= k;
			
			//add min group to left 
			//System.out.println("minGroup.end" + (minGroup.end +1)+ " minGroup.start "+ minGroup.start);
			if(minGroup.start - 1 > 0) {
				//System.out.println("test1");
				minGroup.height = arrayGrouped[minGroup.start - 1].height;
			}
			//to the right
			else if (minGroup.end +1 < array.length) {
				//System.out.println("test");
				minGroup.height = arrayGrouped[minGroup.end + 1].height;
			}
			
			//check groups
			for(int i = 1; i < arrayGrouped.length; i++) {
				//merge different neighbour groups with same height 
				if(arrayGrouped[i].height == arrayGrouped[i-1].height &&
						arrayGrouped[i].id != arrayGrouped[i-1].id ) {
					arrayGrouped[i-1].size += arrayGrouped[i].size;
					arrayGrouped[i-1].end = arrayGrouped[i].end;
					//System.out.println(arrayGrouped[i].start + " " + arrayGrouped[i].end);
					for(int j = arrayGrouped[i].start ; j <= arrayGrouped[i].end ; j++) {
						//System.out.println(j + " " + arrayGrouped.length);
						arrayGrouped[j] = arrayGrouped[i-1];
					}
				}
			}
		}
		//System.out.println();
		//find how many elements were changed from initial values
		for (int i = 0; i < array.length; i++) {
			//System.out.printf("n %d, i %d id%d| ",arrayGrouped[i].height,array[i],arrayGrouped[i].id);
			if (arrayGrouped[i].height != array[i])
				++ans;
		}
		//System.out.println();
		
		return ans;
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

          int min = (new Flattening()).findMinChange(array1, limit);
         
          System.out.println("Case #" + i + ": " + min );
          //max = 0;

        }
        in.close();
      }
}
