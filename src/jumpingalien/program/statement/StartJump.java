package jumpingalien.program.statement;

import jumpingalien.model.GameObject;
import jumpingalien.model.Mazub;
import jumpingalien.model.Shark;
import jumpingalien.part3.programs.SourceLocation;

public class StartJump extends ActionStatement {
	public StartJump(SourceLocation sourceLocation){
		super(sourceLocation);
	}
	
	@Override
	public ExecutionState execute(GameObject executingObject) {
		if (executingObject instanceof Mazub || executingObject instanceof Shark)
			executingObject.startJump();
		else {
			executingObject.getProgram().setStatementsLeft(0);
			executingObject.getProgram().setRunTimeError(true);
			return ExecutionState.NOTDONE;
		}
		return ExecutionState.DONE;
	}
}
