package jumpingalien.program.statement;

import jumpingalien.model.GameObject;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.ProgramExecutor;

public class EndDuck extends Statement {
	
	public EndDuck(SourceLocation sourceLocation) {
		super(sourceLocation);
	}
	
	public void execute() {
		Object executing_object =  ProgramExecutor.getExecutingObject();
		if(executing_object instanceof GameObject)
			((GameObject) executing_object).endDuck();
	}		
	
}
