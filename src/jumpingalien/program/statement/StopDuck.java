package jumpingalien.program.statement;

import jumpingalien.model.GameObject;
import jumpingalien.model.Mazub;
import jumpingalien.part3.programs.SourceLocation;

public class StopDuck extends ActionStatement {
	
	public StopDuck(SourceLocation sourceLocation) {
		super(sourceLocation);
	}
	
	@Override
	public ExecutionState execute(GameObject executingObject) {
		if(executingObject instanceof Mazub)
			((Mazub) executingObject).endDuck();
		else {
			executingObject.getProgram().setStatementsLeft(0);
			executingObject.getProgram().setRunTimeError(false);
			return ExecutionState.NOTDONE;
		}
		return ExecutionState.DONE;
	}		
	
}
