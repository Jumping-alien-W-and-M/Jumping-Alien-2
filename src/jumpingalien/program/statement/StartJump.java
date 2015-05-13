package jumpingalien.program.statement;

import jumpingalien.model.GameObject;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.ProgramExecutor;

public class StartJump extends ActionStatement {
	public StartJump(SourceLocation sourceLocation){
		super(sourceLocation);
	}
	
	@Override
	public ExecutionState execute() {
		Object self = ProgramExecutor.getExecutingObject();
		if (self instanceof GameObject) ((GameObject) self).startJump();
		
		return ExecutionState.DONE;
	}
}
