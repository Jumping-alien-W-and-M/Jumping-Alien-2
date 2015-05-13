package jumpingalien.program.statement;

import jumpingalien.model.GameObject;
import jumpingalien.model.Program;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.part3.programs.IProgramFactory.Direction;
import jumpingalien.program.Type;
import jumpingalien.program.expression.Expression;

public class StopRun extends ActionStatement {

	public StopRun(Expression direction, SourceLocation sourceLocation){
		super(sourceLocation);
		this.direction = direction;
		
		if(direction.getType() != Type.DIRECTION) Program.printTypeCheckError(sourceLocation);
	}
	
	public Expression getDirection(){
		return this.direction;
	}
	private final Expression direction;
	
	@Override
	public ExecutionState execute(GameObject executingObject) {
		Object self = executingObject;
		
		if (self instanceof GameObject) {
			if (direction.getValue(executingObject) == Direction.LEFT) ((GameObject) self).endMove("left");
			if (direction.getValue(executingObject) == Direction.RIGHT) ((GameObject) self).endMove("right");
		}
		
		return ExecutionState.DONE;
	}
}
