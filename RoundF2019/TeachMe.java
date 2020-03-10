import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class TeachMe {
	
	//brute force
	public static int findPairs(HashMap<Integer,Integer>[] array1 ) {
		int ans = 0;
		for(int i = 0; i < array1.length; i++) {
			for(int j = 0; j < array1.length; j++) {
				if(i == j) continue;
				//iterate hashmap
				for (int key : array1[i].keySet()) {
					if (!array1[j].containsKey(key)) {
						++ans;
						break;
					}
				}
			}
		}
		return ans;
	}
	
	private static void findAllsubsets(LinkedList<ArrayList<Integer>> ans, ArrayList<Integer> keySet, int position, int skipped ) {
		if (position == keySet.size()) {
			//System.out.println(Arrays.toString(keySet));
			if (skipped == keySet.size()) {
				return; //remove empty array
			}
			ArrayList<Integer> tmpArr = new ArrayList<Integer>();
			for(Integer key: keySet) {
				if (key > -1)
				tmpArr.add(key);
			}
			ans.add(tmpArr);
			return;
		}
		
		findAllsubsets(ans, keySet, position +1, skipped);
		int tmpKeySet = keySet.get(position);
		keySet.set(position,-1);
		findAllsubsets(ans, keySet, position +1, skipped+1);	
		keySet.set(position,tmpKeySet);
		return;
	}
	
	//for each employee find the sum of other employees which have subsets of his skills
	//subtract them from total employee and get pairs from them to the target employee
	public static int findPairs2(HashMap<Integer,ArrayList<Integer>> employees, HashMap<ArrayList<Integer>,Integer> skills) {
		int ans = 0, subset;
		for (int key : employees.keySet()) {
			//make all subsets of skills
			LinkedList<ArrayList<Integer>> tmpArr = new LinkedList<>();
			findAllsubsets(tmpArr, employees.get(key),0,0);
			//System.out.println("tmpArray" + tmpArr.toString() + " empl " + employees.get(key));
			
			subset = 0 ;	
			/*System.out.println("skills");
			for (ArrayList<Integer> key2: skills.keySet()) {
				System.out.println(skills.get(key2) + key2.toString());
			}*/
			
			for (ArrayList<Integer> key2: tmpArr) {
				//System.out.println("key2" + key2.toString());
				if (skills.containsKey(key2)) {
					subset += skills.get(key2);
				}	
			}
			//System.out.println(employees.size() + " " + subset);
			ans += (employees.size() - subset);
		}

		return ans;
	}
	
	public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt(); // num of cases
        in.nextLine();
        int workers= 0 ;
        int skills = 0;
        
        for (int i = 1; i <= t; ++i) {
          workers = in.nextInt();
          skills = in.nextInt();
          in.nextLine();

         @SuppressWarnings("unchecked")
		 //HashMap<Integer,Integer>[] array1 = new HashMap[workers];
         HashMap<Integer,ArrayList<Integer>> array2 = new HashMap<>();
         HashMap<ArrayList<Integer>,Integer> array3 = new HashMap<>();
          for (int j = 0 ; j < workers; j++) { 
        	  int skill = in.nextInt();
        	  ArrayList<Integer> tmpArr = new ArrayList<>();
        	  for(int z = 0; z< skill; z++) {
        		  int tmp = in.nextInt();
        		 /* if (array1[j] == null) array1[j] = new HashMap<Integer,Integer>();
        		  array1[j].put(tmp,j);*/
        		  tmpArr.add(tmp);
        	  }
    		  if( !array2.containsKey(j) ) {
    			  array2.put(j, tmpArr);
    		  }
    		  
    		  if( !array3.containsKey(tmpArr) ) {
    			  array3.put(tmpArr, 1);
    		  }
    		  else array3.put(tmpArr, array3.get(tmpArr)+1);
          }
          in.nextLine();

          int ans = findPairs2(array2, array3);
         
          System.out.println("Case #" + i + ": " + ans );
          //max = 0;

        }
        in.close();
      }
}
