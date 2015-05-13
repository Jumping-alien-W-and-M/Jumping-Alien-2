package jumpingalien.program.statement;

import jumpingalien.model.GameObject;
import jumpingalien.model.Program;
import jumpingalien.part3.programs.IProgramFactory.Direction;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.ProgramExecutor;
import jumpingalien.program.Type;
import jumpingalien.program.expression.Expression;

public class StartRun extends ActionStatement {

	public StartRun(Expression direction, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.direction = direction;
		
		if(direction.getType() != Type.DIRECTION) Program.printTypeCheckError(sourceLocation);
	}
	
	public Expression getDirection() {
		return this.direction;
	}
	private final Expression direction;
	
	@Override
	public ExecutionState execute() {
		Object self = ProgramExecutor.getExecutingObject();
		
		if (self instanceof GameObject) {
			if (direction.getValue() == Direction.LEFT) ((GameObject) self).startMove("left");
			if (direction.getValue() == Direction.RIGHT) ((GameObject) self).startMove("right");
		}
		
		return ExecutionState.DONE;
	}
}