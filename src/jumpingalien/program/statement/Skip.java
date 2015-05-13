package jumpingalien.program.statement;

import jumpingalien.model.GameObject;
import jumpingalien.part3.programs.SourceLocation;

public class Skip extends ActionStatement {
	
	public Skip(SourceLocation sourceLocation) {
		super(sourceLocation);
	}
	
	@Override
	public ExecutionState execute(GameObject executingObject) {
		return ExecutionState.DONE;
	}
	
}
