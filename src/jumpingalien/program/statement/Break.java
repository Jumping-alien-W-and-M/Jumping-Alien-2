package jumpingalien.program.statement;

import jumpingalien.model.GameObject;
import jumpingalien.part3.programs.SourceLocation;

public class Break extends Statement {
	public Break(SourceLocation sourcelocation){
		super(sourcelocation);
	}
	
	@Override
	public ExecutionState execute(GameObject executingObject) {
		return ExecutionState.BREAK;
	}
}
