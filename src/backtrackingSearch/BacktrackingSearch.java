package backtrackingSearch;

public class BacktrackingSearch {
	
	public BacktrackingSearch() {
	}
	
	public State solve(int boardSize) {
		State state = new State(boardSize);
		for(int depth=0;depth<boardSize;depth++) {
			System.out.println(depth);
			if(!state.addQueen()) {
				state.removeQueen();
				depth-=2;
			}
		}
		return state;
	}
	
	public static void main(String[] args) {
		BacktrackingSearch search = new BacktrackingSearch();
		State solution = search.solve(31);
		solution.paint();
		System.out.println(solution);
	}
}
	