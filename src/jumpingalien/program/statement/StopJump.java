package jumpingalien.program.statement;

import jumpingalien.model.GameObject;
import jumpingalien.part3.programs.SourceLocation;

public class StopJump extends ActionStatement {
	public StopJump(SourceLocation sourceLocation){
		super(sourceLocation);
	}
	
	@Override
	public ExecutionState execute(GameObject executingObject) {
		Object self = executingObject;
		if (self instanceof GameObject) ((GameObject) self).endJump();
		
		return ExecutionState.DONE;
	}
}
