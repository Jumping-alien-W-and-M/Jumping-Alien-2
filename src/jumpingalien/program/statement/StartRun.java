package jumpingalien.program.statement;

import jumpingalien.model.GameObject;
import jumpingalien.model.Program;
import jumpingalien.part3.programs.IProgramFactory.Direction;
import jumpingalien.part3.programs.SourceLocation;
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
	public ExecutionState execute(GameObject executingObject) {
		
		switch((Direction) direction.getValue(executingObject)) {
		case LEFT: executingObject.startMove("left"); break;
		case RIGHT: executingObject.startMove("right"); break;
		default: 
			executingObject.getProgram().setStatementsLeft(0);
			executingObject.getProgram().setRunTimeError(true);
		}
		
		return ExecutionState.DONE;
	}
}