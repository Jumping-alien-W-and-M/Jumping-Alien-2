package jumpingalien.program.statement;

import jumpingalien.model.GameObject;
import jumpingalien.model.Mazub;
import jumpingalien.part3.programs.SourceLocation;

public class StartDuck extends ActionStatement {
	
	public StartDuck(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	@Override
	public ExecutionState execute(GameObject executingObject) {
		if (executingObject instanceof Mazub)
			((Mazub) executingObject).startDuck();
		return ExecutionState.DONE;
	}
}
