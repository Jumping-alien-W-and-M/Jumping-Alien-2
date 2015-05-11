package jumpingalien.program.statement;

import jumpingalien.part3.programs.SourceLocation;

public class Break extends Statement {
	public Break(SourceLocation sourcelocation){
		super(sourcelocation);
	}
	
	@Override
	public ExecutionState execute() {
		return ExecutionState.BREAK;
	}
}
