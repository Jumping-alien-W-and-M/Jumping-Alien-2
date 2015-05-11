package jumpingalien.program.statement;

import java.util.ArrayList;
import java.util.List;

import be.kuleuven.cs.som.annotate.Basic;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.ProgramExecutor;

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
	public boolean execute() {
		while (ProgramExecutor.getStatementsLeft() > 0) {
			ProgramExecutor.setStatementsLeft(ProgramExecutor.getStatementsLeft() - 1);
			boolean done = getStatements().get(getCurrentStatement()).execute();
			if (done) {
				setCurrentStatement(getCurrentStatement() + 1);
				if (getCurrentStatement() == getStatements().size()) {
					setCurrentStatement(0);
					return true;
				}
			}
		}
		
		return false;
	}
	
}
