package backtrackingSearch;

import java.util.ArrayList;

/*
 * 
 * This state is used to make sure manipulation of the state goes smooth and without errors.
 * It also makes it easier for me to resue code.
 * 
 */

public class State {

	private int boardSize;	// Size of the board
	private ArrayList<ArrayList<Integer>> D;		// Queens
	private ArrayList<ArrayList<ArrayList<Integer>>> C;		// Collisions
	private ArrayList<Integer> queensPlaced = new ArrayList<Integer>();
	
	public State(int boardSize) {	// Create an empty boardstate
		this.boardSize = boardSize;
		
		C = new ArrayList<ArrayList<ArrayList<Integer>>>();
		D = new ArrayList<ArrayList<Integer>>();
		for(int i=0;i<boardSize;i++) {
			C.add(new ArrayList<ArrayList<Integer>>());
			C.get(i).add(new ArrayList<Integer>());
			D.add(new ArrayList<Integer>());
			for(int j=0;j<boardSize;j++) {
				D.get(i).add(j);
			}
		}
	}
	
	public boolean canHaveChildren() {
		for(ArrayList<Integer> d:D) if(d.isEmpty()) return false;
		return true;
	}
	
	public boolean addQueen() {
		if(!canHaveChildren()) return false; // Illegal states will be prevented
		
		int queen = getQueenToAdd();
		int field = getFieldForQueenPlacement(queen);
		
		for(int i=0;i<boardSize;i++) {			
			C.get(i).add(new ArrayList<Integer>());
		}
		
		// Doing the operations on the row
		for(int collition:D.get(queen)) {
			if(collition!=queen) C.get(queen).get(C.get(queen).size()-1).add(collition);
			D.get(queen)
		}
		// Doing the operations on the column
		for(int i=0;i<boardSize;i++){
			if(i!=queen && D.get(i).contains(queen)) C.get(i).get(C.get(i).size()-1).add(queen);
		}
		return true; // hey! It worked!
	}

	

	/*
	 * Using methods for these two tasks are overkill, but I will be able to change them to something better later on without much extra work.
	 */
	private int getFieldForQueenPlacement(int queen) {
		return D.get(queen).get(0);
	}
	private int getQueenToAdd() { 
		if(queensPlaced.size()==0) return 0;
		else return queensPlaced.get(queensPlaced.size()-1)+1;
	}
}
