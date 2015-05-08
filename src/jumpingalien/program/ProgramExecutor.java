package jumpingalien.program;

import jumpingalien.model.GameObject;

public class ProgramExecutor {
	
	public static GameObject getExecutingObject() {
		return executingObject;
	}
	
	private static GameObject executingObject = null;
	
}
