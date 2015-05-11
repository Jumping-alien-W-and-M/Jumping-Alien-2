package jumpingalien.program.statement;

import be.kuleuven.cs.som.annotate.Basic;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.ProgramExecutor;
import jumpingalien.program.expression.Expression;

public class While extends Statement {
	
	public While(Expression condition, Statement body, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.condition = condition;
		this.body = body;
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
	public ExecutionState execute() {
		
		ProgramExecutor.setStatementsLeft(ProgramExecutor.getStatementsLeft() + 1);
		
		while(ProgramExecutor.getStatementsLeft() > 0) {
			
			if (!getInBody()) {
				setInBody((boolean) getCondition().getValue());
				ProgramExecutor.setStatementsLeft(ProgramExecutor.getStatementsLeft() - 1);
			}
			
			if (!getInBody()) return ExecutionState.DONE;
			if (ProgramExecutor.getStatementsLeft() <= 0) return ExecutionState.NOTDONE;
			
			if (!(getBody() instanceof Sequence)) ProgramExecutor.setStatementsLeft(ProgramExecutor.getStatementsLeft() - 1);
			
			ExecutionState state = getBody().execute();
			if (state == ExecutionState.DONE) setInBody(false);
			if (state == ExecutionState.BREAK) {
				setInBody(false);
				return ExecutionState.DONE;
			}
		}
		
		return ExecutionState.NOTDONE;
	}
}
