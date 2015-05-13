package jumpingalien.program.statement;

import jumpingalien.model.GameObject;
import jumpingalien.part3.programs.SourceLocation;

public class StartDuck extends ActionStatement {
	
	public StartDuck(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	@Override
	public ExecutionState execute(GameObject executingObject) {
		executingObject.startDuck();
		return ExecutionState.DONE;
	}
}
