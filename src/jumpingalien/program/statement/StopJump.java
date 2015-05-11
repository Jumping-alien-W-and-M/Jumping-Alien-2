package jumpingalien.program.statement;

import jumpingalien.model.GameObject;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.ProgramExecutor;

public class StopJump extends Statement{
	public StopJump(SourceLocation sourceLocation){
		super(sourceLocation);
	}
	
	@Override
	public ExecutionState execute() {
		Object self = ProgramExecutor.getExecutingObject();
		if (self instanceof GameObject) ((GameObject) self).endJump();
		
		return ExecutionState.DONE;
	}
}
