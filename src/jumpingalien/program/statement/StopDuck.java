package jumpingalien.program.statement;

import jumpingalien.model.GameObject;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.ProgramExecutor;

public class StopDuck extends ActionStatement {
	
	public StopDuck(SourceLocation sourceLocation) {
		super(sourceLocation);
	}
	
	@Override
	public ExecutionState execute() {
		Object executing_object =  ProgramExecutor.getExecutingObject();
		if(executing_object instanceof GameObject)
			((GameObject) executing_object).endDuck();
		return ExecutionState.DONE;
	}		
	
}
