import java.util.Arrays;
import java.util.Scanner;

public class DiagonalPuzzle {
	
	private int minMoves = Integer.MAX_VALUE;
	
	private int solver(boolean [][] puzzle) {
		boolean [] whiteDiagonals = new boolean[4*puzzle.length-2];
		
		//diagonals - 1 to 2n-1 -left, 2n to 4n-2 - right
		//for right / - [0][0] - is first, [1][2] && [2][1] - second 
		//for left \ - [n-1][0] is - 2n, [n-2][0] && [n-1][1] - is 2n +1 , etc...
		boolean [] usedDiagonals = new boolean[4*puzzle.length-2]; // false - unused
		
		/*1. go through all points 
		-if point is white, color its corresponding diagonal to white*/
		
		for(int i = 0 ; i < puzzle.length; i ++) {
			for(int j = 0 ; j < puzzle[i].length; j ++) {
				if(puzzle[i][j] == true) { 
					int diagonal = getRightDiagonal(i,j);
					whiteDiagonals[diagonal] = true;
					diagonal = getLeftDiagonal(i,j,puzzle.length);
					whiteDiagonals[diagonal] = true;
				}
			}
		}
		
		/*	2. iterate over every "white diagonal" recursivly
		stop if exceeds minmum found allready or no more unused diagonals
		NOT all points on the diagonal
		add this diagonal to used ones*/
		return findSolution(puzzle,whiteDiagonals,usedDiagonals,4*puzzle.length-2,0);
	}
	
	private int findSolution(boolean [][] puzzle, boolean [] whiteDiagonals,
			boolean [] usedDiagonals, int leftUnusedDiagonals, int numMoves) {
		//if (numMoves <=3)
		//System.out.println("test");
		
		/*System.out.println("numMoves" + numMoves);
		for (int i = 0; i < puzzle.length; i++) {
			for(int j = 0; j < puzzle.length; j++) {
				System.out.printf("%b ",puzzle[i][j]);
			}
			System.out.println();
		}
		System.out.println(" before " + Arrays.toString(whiteDiagonals));*/
		
		

		if(numMoves >= minMoves || leftUnusedDiagonals == 0) {
			return minMoves;
		}
		
		
		if(leftWhiteDiagonals(whiteDiagonals) == 0 ) {
			System.out.println("TEEEEEEEEEEEEEEEEEEEEEEESR");
			minMoves = numMoves;
			return numMoves;
		}
		
		for(int i = 0; i < 4*puzzle.length-2; i++) {
			//System.out.println("i " + i);
			if(usedDiagonals[i] == false && whiteDiagonals[i] == true ) {
				//System.out.println("i " + i);
				//NOTDiagonal[i];
				//usedDiagonals[i] = true;
				boolean [] usedDiagonalsTemp = usedDiagonals.clone();
				usedDiagonalsTemp[i] = true;
				boolean [] whiteDiagonalsTemp = whiteDiagonals.clone();
				boolean[][] puzzleTmp = puzzle.clone();
				//System.out.println();
				//System.out.println("numMoves" + numMoves);
				NotDiagonals(puzzleTmp, whiteDiagonalsTemp, i);
				findSolution(puzzleTmp, whiteDiagonalsTemp, usedDiagonalsTemp, 
						leftUnusedDiagonals - 1, numMoves+1);
			}
		}
		return minMoves;
	}
	
	private void NotDiagonals(boolean [][] puzzleTmp, boolean [] WhiteDiagonals, int changedDiagonal) {
		//get all points of a diagonal
		System.out.println();
		System.out.println("changed " + changedDiagonal);
		System.out.println(" before " + Arrays.toString(WhiteDiagonals));

		for (int i = 0; i < puzzleTmp.length; i++) {
			for(int j = 0; j < puzzleTmp.length; j++) {
				System.out.printf("%b ",puzzleTmp[i][j]);
			}
			System.out.println();
		}

			for(int x = 0; x < puzzleTmp.length; x++) {
				int y;
				if (changedDiagonal < 2*puzzleTmp.length -1 ) //right diagonal
					y = changedDiagonal - x ;
				else //left diagonal
					y = changedDiagonal +1 + x - 3*puzzleTmp.length ;
				
				if (y >= 0 && y < puzzleTmp.length) {
					puzzleTmp[x][y] = !puzzleTmp[x][y];
				}
			}
			System.out.println();
			for (int i = 0; i < puzzleTmp.length; i++) {
				for(int j = 0; j < puzzleTmp.length; j++) {
					System.out.printf("%b ",puzzleTmp[i][j]);
				}
				System.out.println();
			}
			
			/*for(int i = 0 ; i < puzzleTmp.length; i ++) {
				for(int j = 0 ; j < puzzleTmp[i].length; j ++) {
					int diagonalLeft = getRightDiagonal(i,j);
					int diagonalRight = getLeftDiagonal(i,j,puzzleTmp.length);
					if(puzzleTmp[i][j] == true) { 
						WhiteDiagonals[diagonalLeft] = true;
						WhiteDiagonals[diagonalRight] = true;
					}
					else {
						WhiteDiagonals[diagonalLeft] = false;
						WhiteDiagonals[diagonalRight] = false;
					}
				}
			}*/
			
			for(int x = 0; x < puzzleTmp.length; x++) {
				int y;
				if (changedDiagonal < 2*puzzleTmp.length -1 ) //right diagonal
					y = changedDiagonal - x ;
				else //left diagonal
					y = changedDiagonal +1 + x - 3*puzzleTmp.length ;
				
				if (y >= 0 && y < puzzleTmp.length) {

					
					if (checkWhiteDiagonal(getLeftDiagonal(x,y,puzzleTmp.length),puzzleTmp) == true)
						WhiteDiagonals[getLeftDiagonal(x,y,puzzleTmp.length)] = true;
					else WhiteDiagonals[getLeftDiagonal(x,y,puzzleTmp.length)] = false;
					
					if (checkWhiteDiagonal(getRightDiagonal(x,y),puzzleTmp) == true) 
						WhiteDiagonals[getRightDiagonal(x,y)] = true;
					else WhiteDiagonals[getRightDiagonal(x,y)] = false;
				}
			}
			
			System.out.println("Puzzle " + Arrays.toString(WhiteDiagonals));
			
			/*for(int x = 0; x < puzzleTmp.length; x++) {
				int y;
				if (changedDiagonal < 2*puzzleTmp.length -1 ) //right diagonal
					y = changedDiagonal - x ;
				else //left diagonal
					y = changedDiagonal +1 + x - 3*puzzleTmp.length ;
				
				if (y >= 0 && y < puzzleTmp.length) {

					
					if (checkWhiteDiagonal(getLeftDiagonal(x,y,puzzleTmp.length),puzzleTmp) == true)
						WhiteDiagonals[getLeftDiagonal(x,y,puzzleTmp.length)] = true;
					else WhiteDiagonals[getLeftDiagonal(x,y,puzzleTmp.length)] = false;
					
					if (checkWhiteDiagonal(getRightDiagonal(x,y),puzzleTmp) == true) 
						WhiteDiagonals[getRightDiagonal(x,y)] = true;
					else WhiteDiagonals[getRightDiagonal(x,y)] = false;
				}
			}*/

	}

	//check if has white
	private boolean checkWhiteDiagonal(int changedDiagonal, boolean puzzle [][]) {
		for(int x = 0; x < puzzle.length; x++) {
			int y;
			if (changedDiagonal < 2*puzzle.length -1 ) //right diagonal
				y = changedDiagonal - x ;
			else //left diagonal
				y = changedDiagonal +2 + x - 3*puzzle.length ;
			
			//(2*n - 1) + (n -1 -x) +y;
			
			if (y >= 0 && y < puzzle.length) {
				if (puzzle[x][y] == true) return true;
			}
		}
		return false;
	}	
	
	private int leftWhiteDiagonals(boolean [] diagonals) {
		int count = 0;
		for(int i = 0 ; i < diagonals.length; i++) {
			if(diagonals[i] == true)
				++count;
		}
		return count;
	}
	
	private int getRightDiagonal(int x, int y) {
		return x + y;
	}
	
	private int getLeftDiagonal(int x, int y, int n) {
		return (2*n - 1) + (n -1 -x) +y;
	}

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 0; i < t; ++i) {
          int n = in.nextInt();
          int min = 0;
          boolean [][] arr = new boolean[n][n];
          for (int j = 0 ; j < n; j++) { 
        	  String tmp = in.next();
        	  int z = 0;
        	  while (z < tmp.length()) {	  
        		  if(tmp.charAt(z) == '.') //white
        			  arr[j][z] = true;
        		  else if (tmp.charAt(z) == '#')
        			  arr[j][z] = false;
        		  z++;
        	  }
          }
          
          min = (new DiagonalPuzzle()).solver(arr);
          System.out.println("Case #" + i +": " +min);
        }
      
        in.close();
      }
}
