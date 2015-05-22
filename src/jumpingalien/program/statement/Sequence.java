package jumpingalien.program.statement;

import java.util.ArrayList;
import java.util.List;

import be.kuleuven.cs.som.annotate.Basic;
import jumpingalien.model.GameObject;
import jumpingalien.part3.programs.SourceLocation;

public class Sequence extends Statement {
	
	public Sequence(List<Statement> statements, SourceLocation sourceLocation) {
		super(sourceLocation);
		
		this.statements = statements;
	}
	
	@Basic
	public List<Statement> getStatements() {
		return new ArrayList<Statement>(statements);
	}
	
	private final List<Statement> statements;
	
	@Basic
	public int getCurrentStatement() {
		return this.current_statement;
	}
	
	private void setCurrentStatement(int current_statement) {
		this.current_statement = current_statement;
	}
	
	private int current_statement = 0;

	@Override
	public ExecutionState execute(GameObject executingObject) {
		while (executingObject.getProgram().getStatementsLeft() > 0) {
			if (!(getStatements().get(getCurrentStatement()) instanceof Sequence))
				executingObject.getProgram().setStatementsLeft(executingObject.getProgram().getStatementsLeft() - 1);
			ExecutionState state = getStatements().get(getCurrentStatement()).execute(executingObject);
			if (state == ExecutionState.BREAK) {
				setCurrentStatement(0);
				return ExecutionState.BREAK;
			}
			if (state == ExecutionState.DONE) {
				setCurrentStatement(getCurrentStatement() + 1);
				if (getCurrentStatement() == getStatements().size()) {
					setCurrentStatement(0);
					return ExecutionState.DONE;
				}
			}
		}
		
		return ExecutionState.NOTDONE;
	}
	
}
