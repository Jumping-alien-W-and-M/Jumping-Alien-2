package jumpingalien.program;

import be.kuleuven.cs.som.annotate.Basic;
import jumpingalien.model.GameObject;

public class ProgramExecutor {
	
	@Basic
	public static GameObject getExecutingObject() {
		return executingObject;
	}
	
	private static GameObject executingObject = null;
	
	@Basic
	public static int getStatementsLeft() {
		return statements_left;
	}
	
	public static void setStatementsLeft(int statements_left) {
		ProgramExecutor.statements_left = statements_left;
	}
	
	private static int statements_left;
	
}
