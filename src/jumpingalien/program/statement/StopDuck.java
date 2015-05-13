package jumpingalien.program.statement;

import jumpingalien.model.GameObject;
import jumpingalien.part3.programs.SourceLocation;

public class StopDuck extends ActionStatement {
	
	public StopDuck(SourceLocation sourceLocation) {
		super(sourceLocation);
	}
	
	@Override
	public ExecutionState execute(GameObject executingObject) {
		Object executing_object =  executingObject;
		if(executing_object instanceof GameObject)
			((GameObject) executing_object).endDuck();
		return ExecutionState.DONE;
	}		
	
}
