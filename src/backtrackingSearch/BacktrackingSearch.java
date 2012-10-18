package backtrackingSearch;

public class BacktrackingSearch {
	public static void main(String[] args) {
		State state = new State(8);
		System.out.println(state);
		System.out.println("");
		state.addQueen();
		System.out.println(state);
		System.out.println("");
		state.addQueen();
		System.out.println(state);
		System.out.println("");
		state.removeQueen();
		System.out.println(state);
	}
}
