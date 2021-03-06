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
	private ArrayList<ArrayList<Integer>> D;							// Queens
	private ArrayList<ArrayList<ArrayList<Integer>>> C;					// Collisions
	private ArrayList<Integer> queensPlaced = new ArrayList<Integer>();
	
	public State(int boardSize) {	// Create an empty boardstate
		this.boardSize = boardSize;
		
		C = new ArrayList<ArrayList<ArrayList<Integer>>>();
		D = new ArrayList<ArrayList<Integer>>();
		for(int i=0;i<boardSize;i++) {
			C.add(new ArrayList<ArrayList<Integer>>());
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
		queensPlaced.add(queen);						// Add the queen to the placed queens
		                   
		for(int i=0;i<boardSize;i++) {			
			C.get(i).add(new ArrayList<Integer>());
		}
		
		//First we add all collitions
		
		// Doing the operations on the row
		for(int collition:D.get(queen)) if(collition!=field) C.get(queen).get(C.get(queen).size()-1).add(collition);	// Add new collitions in an (at first) empty arraylist
		//D.get(queen).clear();									// Clear the row in the queen table
		//D.get(queen).add(field);								// Add the queen to the now empty list of possible queen locations in the row
		
		// Doing the operations on the column
		for(int i=0;i<boardSize;i++) if(i!=queen && D.get(i).contains(field)) C.get(i).get(C.get(i).size()-1).add(field);
		
		// Doing operations on the diagonals
		for(int i=0;i<boardSize;i++) {
			for(int j=-1;j<2;j+=2) {
				if(((i-queen)*j)+field>=0 && ((i-queen)*j)+field < boardSize && i!=queen && D.get(i).contains(((i-queen)*j)+field)) C.get(i).get(C.get(i).size()-1).add(((i-queen)*j)+field);
			}
		}
		
		// Fjerner colisjonene fra D
		for(int i=0;i<boardSize;i++) {
			for(int collition:C.get(i).get(C.get(i).size()-1)) {
				D.get(i).remove((Integer)collition);
			}
		}
		
		return true; // hey! It worked!
	}

	public boolean removeQueen() {
		if(queensPlaced.isEmpty()) return false;	// If the board is empty, no queen can be removed.
		
		int queen = queensPlaced.remove(queensPlaced.size()-1);
		
		for(int i=0;i<boardSize;i++) {
			for(int collition:C.get(i).remove(C.get(i).size()-1)) {
				D.get(i).add(collition);
			}
		}
		
		// To make sure that the possition will not occur again we add the queenstate we just tried to C and remove it from D
		int field = getFieldForQueenPlacement(queen);
		C.get(queen).get(C.get(queen).size()-1).add(field);
		D.get(queen).remove((Integer)field);
		return true; // Hey! It worked!
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
	
	@Override
	public String toString() {
		String string = "";
		
		string += "D: " + System.getProperty("line.separator");
		
		for(int i=0;i<boardSize;i++) {
			string += "( ";
			for(int queen:D.get(i)) {
				string += queen + " ";
			}
			string += ")" + System.getProperty("line.separator");
		}
		
		string += "C: " + System.getProperty("line.separator");
		for(int i=0;i<boardSize;i++) {
			for(ArrayList<Integer> collitions:C.get(i)) {
				string += "( ";
				for(int collition:collitions) {
					string += collition + " ";
				}
				string += ")";
			}
			string += System.getProperty("line.separator");
		}
		
		return string;
	}

	public void paint() {
		for(int i=0;i<boardSize;i++) {
			for(int j=0;j<boardSize;j++) {
				System.out.print(" " + (j==D.get(i).get(0)?"O":"."));
			}
			System.out.print(System.getProperty("line.separator"));
		}
	}
}
