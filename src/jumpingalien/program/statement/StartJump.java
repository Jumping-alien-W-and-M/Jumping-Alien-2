package jumpingalien.program.statement;

import jumpingalien.model.GameObject;
import jumpingalien.part3.programs.SourceLocation;

public class StartJump extends ActionStatement {
	public StartJump(SourceLocation sourceLocation){
		super(sourceLocation);
	}
	
	@Override
	public ExecutionState execute(GameObject executingObject) {
		executingObject.startJump();
		return ExecutionState.DONE;
	}
}
