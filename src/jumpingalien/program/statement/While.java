package jumpingalien.program.statement;

import be.kuleuven.cs.som.annotate.Basic;
import jumpingalien.model.GameObject;
import jumpingalien.model.Program;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.Type;
import jumpingalien.program.expression.Expression;

public class While extends Statement {
	
	public While(Expression condition, Statement body, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.condition = condition;
		this.body = body;
		
		if(condition.getType() != Type.BOOL) Program.printTypeCheckError(sourceLocation);
	}

	@Basic
	public Expression getCondition() {
		return this.condition;
	}
	
	private final Expression condition;

	@Basic
	public Statement getBody() {
		return this.body;
	}
	
	private final Statement body;
	
	@Basic
	public boolean getInBody()  {
		return this.in_body;
	}
	
	private void setInBody(boolean in_body) {
		this.in_body = in_body;
	}
	
	private boolean in_body = false;
	
	@Override
	public ExecutionState execute(GameObject executingObject) {
		
		executingObject.getProgram().setStatementsLeft(executingObject.getProgram().getStatementsLeft() + 1);
		
		while(executingObject.getProgram().getStatementsLeft() > 0) {
			
			if (!getInBody()) {
				setInBody((boolean) getCondition().getValue(executingObject));
				executingObject.getProgram().setStatementsLeft(executingObject.getProgram().getStatementsLeft() - 1);
			}
			
			if (!getInBody()) return ExecutionState.DONE;
			if (executingObject.getProgram().getStatementsLeft() <= 0) return ExecutionState.NOTDONE;
			
			if (!(getBody() instanceof Sequence))
				executingObject.getProgram().setStatementsLeft(executingObject.getProgram().getStatementsLeft() - 1);
			
			ExecutionState state = getBody().execute(executingObject);
			if (state == ExecutionState.DONE) setInBody(false);
			if (state == ExecutionState.BREAK) {
				setInBody(false);
				return ExecutionState.DONE;
			}
		}
		
		return ExecutionState.NOTDONE;
	}
}
