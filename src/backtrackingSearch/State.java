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
	
	
	
}
